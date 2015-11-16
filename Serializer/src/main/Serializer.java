/* Name:		J. Daniel Gonzalez
 * UCID:		10058656
 * Class:		CPSC 501
 * Ass:			3
 * 
 * Sources: 	1. Used code provided by Waliur Rahman and Lee Ringham as templates for Serializer, Sender and Receiver
 * 				2. Used my own CPSC 501 Assignment 2 Object Inspect as a template for the Object Visualizer
 */

package main;

import java.beans.XMLDecoder;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.XMLOutputter;

public class Serializer {
	private Document doc = null;
	private Element root; 
	private Integer referenceID = 0;
	private HashMap<Object, Integer> referenceMap = new HashMap<Object, Integer>();

	private int currentElement = -1;
	private ArrayList<Object> serializedObjects = new ArrayList<Object>();

	public Serializer(){
		
	}

	public void testSerializer(Document doc) throws Exception{
		XMLOutputter outputter = new XMLOutputter();
		
		String output = outputter.outputString(doc);
		
		//System.out.println(output);
		
		PrintWriter out = new PrintWriter("test.xml");
		out.println(output);
		out.close();
	}
	
	public Document serialize(Object object) {

			// check if object == null		
		if(serializedObjects.contains(object) && object != null) 
			throw new IllegalArgumentException();
		
			//if object has not yet been serialized
		serializedObjects.add(object);

		if(++currentElement == 0) {
			doc = new Document();
			root = new Element("serialized");
			doc.setRootElement(root);
		}

		int id = getID(object);

			//try to get class of object
		Class<?> c;
		
			//object has been instantiated
		if(object != null)
			c = object.getClass();
		
			//object has not been instantiated
		else
			c = new Object().getClass();
		
			
		Element objectElement = new Element("object");
		objectElement.setAttribute( new Attribute("class", c.getName()) );
		objectElement.setAttribute(new Attribute("id", Integer.toString(id)) );
		doc.getRootElement().addContent(objectElement);

			//if object is an array
		if(c.isArray()) {
			Object array = object;
			objectElement.setAttribute(new Attribute("length", Integer.toString(Array.getLength(array))) );

				//if object is an array of primitives
			if(c.getComponentType().isPrimitive()) {
				for (int i = 0; i < Array.getLength(array); i++){
					Element value = new Element("value"); 
					value.setText(Array.get(array, i).toString()); 		//TODO this may be getting the reference to the object rather than the value
					objectElement.addContent(value);
				}
			}
				//if object is an array of non-primitive objects
			else {
				for(int i = 0; i < Array.getLength(array); i++){
					Element ref = new Element("reference");
					id = getID(Array.get(array, i));
					if(id != -1) {
						ref.setText(Integer.toString(id));
						objectElement.addContent(ref);
					}
				}
				for(int j = 0; j < Array.getLength(array); j++) 		//TODO: this may need to get outside loop
					serialize(Array.get(array, j));
			}
		}
			//Object is not an array
		else {
			Class<?> tmpClass = c;
			while(tmpClass != null) {
				Field[] fields = tmpClass.getDeclaredFields();
				ArrayList<Element> fieldXML = serializeFields(fields, object);
				
				for(Element element: fieldXML)
					objectElement.addContent(element);
				
				tmpClass = tmpClass.getSuperclass();
			}
		}
		
		if(currentElement == 0) {
			serializedObjects.clear();
			referenceID = 0;
		}
		
		return doc;	
	}
	
	private ArrayList<Element> serializeFields(Field[] fields, Object object) {
		ArrayList<Element> elements = new ArrayList<Element>();

		for(int i = 0; i < fields.length; i++) {
			
			// skip static fields
			if(Modifier.isStatic(fields[i].getModifiers()))
				continue;
			
			try {
				Field field = fields[i];
				if(!field.isAccessible())
					field.setAccessible(true);
				
				Element element = new Element("field");
				element.setAttribute(new Attribute("name", field.getName()));
				element.setAttribute(new Attribute("declaringClass", object.getClass().getName()));

				if(field.getType().isPrimitive()) {
					Element value = new Element("value");
					value.setText(field.get(object).toString());
					element.addContent(value);
				}
				else {
					int id = getID(field.get(object));
					Element reference = new Element("reference");
					reference.setText(Integer.toString(id));
					element.addContent(reference);

					if (field.get(object) != null)
						serialize(field.get(object));		// recursively serialize object
				}

				elements.add(element);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return elements;
	}

	private int getID(Object object) {
		Integer id = referenceID;

		if(referenceMap.containsKey(object))
			id = referenceMap.get(object);
		else {
			referenceMap.put(object, id);
			referenceID++;
		}
		return id;
	}
}

