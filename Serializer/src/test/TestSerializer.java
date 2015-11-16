package test;

import static org.junit.Assert.*;

import org.jdom2.Document;
import org.jdom2.output.XMLOutputter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.ObjectCreator;
import main.Serializer;

public class TestSerializer {
	
	ObjectCreator objCreator;
	Serializer ser;
	Object o;
	
	@Before
	public void setUp() throws Exception {
		objCreator = new ObjectCreator();
		o = objCreator.createObject(0); 
		ser = new Serializer();	
	}

	@After
	public void tearDown() throws Exception {
		objCreator = null;
		o = null;
		ser = null;
	}

	@Test
	public void testSerialize() {
		String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<serialized><object class=\"objects.Object0\" id=\"0\"><field name=\"int0\" declaringClass=\"objects.Object0\"><value>0</value></field><field name=\"int1\" declaringClass=\"objects.Object0\"><value>0</value></field><field name=\"doub42\" declaringClass=\"objects.Object0\"><value>42.0</value></field></object></serialized>\n";
		Document doc = ser.serialize(o);
		
		XMLOutputter outputter = new XMLOutputter();
		String actual = outputter.outputString(doc);
		
		assertEquals(expected, actual);
	}

}
