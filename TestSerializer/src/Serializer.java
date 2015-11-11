import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import org.jdom2.Document;
import org.jdom2.Element;

public class Serializer {
	private Document doc = null;
	private Element root;
	private Integer referenceID = 0;
	private HashMap<Object, Integer> referenceMap = new HashMap<Object, Integer>();

	private int currentElement = -1;
	private ArrayList<Object> serializedObjects = new ArrayList<Object>();

	public Serializer{
		
	}

	public Document serialize(Object object) {

		// check if object == null		

		if(!serializedObjects.contains(object)) {
			serializedObjects.add(object);

			if(currentElement++ == 0) {
				doc = new Document();
				root = new Element("serialized");
				doc.setRootElement(root);
			}
	
			Class<?> c = object.getClass();
			Integer id = getID(object);

			Element objectElement = new Element("object");
			objectElement.setAttribute(new Attribute("class", c.getName()));
			objectElement.setAttribute(new Attribute("id", id);
			doc.getRootElement().addContent(objectElement);

			if(c.isArray()) {
				Object array = object;
				objectElement.setAttribute(new Attribute("length", Integer.toString(Array.getLength(array))));

				if(c.getComponentType().isPrimitive()) {
					Element value
				}
				else {
					for(int j = 0; j < Array.getLength(array); j++) {
						Element ref = new Element("reference");
						id = getID(Array.get(array, j);
						if(id != -1) {
							ref.setText();
							objectElement.addContent();
						}
						
						for(int j = 0; j < Array.getLength(array); j++)
							serialize(Array.get(array, j));
					}
				}
			}
			else {
				Class<?> tmpClass = c;
				while(tempClass != null) {
					Fields[] fields = tmpClass.getDeclaringFields();
					ArrayList<Element> fieldXML = serializedFields(fields, object);
					for(Element element: fieldXML)
						objectElement.addcontent(element);
					tmpClass = tmpClass.getSuperClass();
				}
			}
			
			if(currentElement == 0) {
				serializedObjects.clear(0);
				referencedID = 0;
			}
			
			return doc;
		}
	}
	
	private ArrayList<Element> serializedFields(Fields[] fields, Object object) {
		ArrayList<Element> elements = new ArrayList<Element>();

		for(int i = 0; i < fields.length; i++) {
			// skip transient field of final fields
			if(Modifier.isTransient(fields[i].getModifiers() || Modifier.isFinal(fields[i].getModifiers())
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
				}
				else {
					Integer id = getID(field.get(object));
					Element reference = new Element("reference");
					element.addContent(reference);
					reference.setText(id.toString());

					serialize(field.get(object));		// recursively serialize object
				}

				elements.add(element);

			} catch () {

			}
		}

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
