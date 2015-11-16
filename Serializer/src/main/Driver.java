package main;

import org.jdom2.Document;

public class Driver {

	public static void main(String[] args) throws Exception{
		ObjectCreator objCreator = new ObjectCreator();
		Object obj = objCreator.createObject(4);
		
		Serializer ser = new Serializer();
		Document doc = ser.serialize(obj);
		ser.testSerializer(doc);
		
		System.out.println("Done!");
	}

}
