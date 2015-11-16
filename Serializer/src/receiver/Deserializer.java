/* Name:		J. Daniel Gonzalez
 * UCID:		10058656
 * Class:		CPSC 501
 * Ass:			3
 * 
 * Sources: 	1. Used code provided by Waliur Rahman and Lee Ringham as templates for Serializer, Sender and Receiver
 * 				2. Used my own CPSC 501 Assignment 2 Object Inspect as a template for the Object Visualizer
 */

package receiver;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.*;
import java.util.List;
import objects.*;

import org.jdom2.*;
import org.jdom2.input.SAXBuilder;

public class Deserializer {
	
	private Integer referenceID = 0;
	private Class c = null;
	private Object obj;

		//reads a XML Document representation of an object, creates the object and object
	private Object ReadXMLFile(Document doc){

			//get root node, get list of fields
		Element rootNode = doc.getRootElement();
		List list = rootNode.getChildren("Object");
		
		try 
		{			
				//get id of object
			int id = Integer.parseInt(rootNode.getAttributeValue("id"));

				//dynamically load class
			String className = rootNode.getAttributeValue("class");
			c = Class.forName(className);
			System.out.println("Class Loaded:" + c.getName());
			
				//reflectievly construt an instance of this class
			Class[] arguments = new Class[]{c};
			Constructor constructor = c.getConstructor(arguments); 
			obj = constructor.newInstance();		
			
			for (int i = 0; i < list.size(); i++) {

					//find "field" node
			   Element node = (Element) list.get(i);
			   String fieldName = node.getChildText("name");
			   String fieldValue = node.getChildText("value");
			   
					//get the Field
				Field f = c.getDeclaredField(fieldName);
				
					//override fields accessibility
				if(!f.isAccessible())
					f.setAccessible(true);
				
					//set field value to its value
				f.set(obj, fieldValue);
				f.setAccessible(false);
			}
			
		} 
		catch (Exception e){
			e.printStackTrace();
		}
		
		return obj;
	}
	
	//Return XML Document representation of a String
	private Document stringToDoc(String docString)
	{
		 Document doc = null;	
		 try
		 {			 		 
			 SAXBuilder docBuilder = new SAXBuilder();
			 InputStream docStream = new ByteArrayInputStream(docString.getBytes("UTF-8"));
			 doc = docBuilder.build(docStream);			 
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }		
		 return doc;
	}
}
