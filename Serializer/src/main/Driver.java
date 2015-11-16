package main;

import org.jdom2.Document;

public class Driver {

	public static void main(String[] args) {
		ObjectCreator objCreator = new ObjectCreator();
		Object obj = objCreator.createObject(0);
		
		Serializer ser = new Serializer();
		Document doc = ser.serialize(obj);
		
		
		
		System.out.println("Done!");
	}

}
