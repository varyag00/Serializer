/* Name:		J. Daniel Gonzalez
 * UCID:		10058656
 * Class:		CPSC 501
 * Ass:			3
 * 
 * Sources: 	1. Used code provided by Waliur Rahman and Lee Ringham as templates for Serializer, Sender and Receiver
 * 				2. Used my own CPSC 501 Assignment 2 Object Inspect as a template for the Object Visualizer
 */

package receiver;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.Enumeration;
import java.util.Vector;

public class ObjectVisualizer {

	int objVisualized = 0;
	String spacing = "    ";
	
	public ObjectVisualizer(){
		
	}
	
	
	//TODO: for object references, should print object ID instead of reference
	
		//does introspection on a object and displays its contents
	public void inspect(Object obj){
		
		if (objVisualized == 0){
			System.out.println("Visualizer: Starting Object Visualizer on object: " + obj.getClass().getName() + "\n");
			objVisualized++;
		}
		
			//objects to inspect within out 
		Vector objectsToInspect = new Vector();
		Class objectClass = obj.getClass();
		
		System.out.println("\n\n" + spacing + "Object to visualize: " + obj);

		inspectFields(obj, objectClass, objectsToInspect);
		
			//inspect objects within this object
		if (objectsToInspect.size() > 0)
			inspectFieldRecursive(obj, objectClass, objectsToInspect);
	}
		
	public void inspectArray(Field field, Object obj){
		try{ 
			
			Object array = field.get(obj);
		
			for (int i = 0; i < Array.getLength(array); i ++){
				System.out.println(spacing + field.getName() + "[" + i + "] = " + Array.get(array, i));
				
			}
		}
			//reached end of array
		catch (Exception e){
			//e.printStackTrace();
			System.out.println(spacing + "Array not initialized");
		}
	}
	
	public void printFieldInfo(Field field, Object obj){
		
		try{
				//Name
			System.out.println(spacing + "Field Name: " + field.getName());
				//Type
			System.out.println(spacing + "Type: " + field.getType().toString());
				//Modifiers
			System.out.println(spacing + "Modifiers: " + Modifier.toString(field.getModifiers()));
			
				//For primitive fields, print current value
			if (field.getType().isPrimitive())
				System.out.println(spacing + "Current value: " + field.get(obj));
				//for object fields, print pointer value when recursion is off
			else if (!field.getType().isPrimitive())
				System.out.println(spacing + "Current value (pointer): " + field.get(obj));
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void inspectFields(Object obj, Class ObjectClass, Vector objectsToInspect){
		
			//if there is at least one field to inspect
		if (ObjectClass.getDeclaredFields().length >= 1){
						
			System.out.println("---- Inspecting Declared Fields ---");

			Field[] declaredFields = ObjectClass.getDeclaredFields();
			
				//inspect every field in the class 
			for (Field field : declaredFields){

				//Field field = ObjectClass.getDeclaredFields()[0];
				field.setAccessible(true);
									
				try{
					System.out.println(spacing + "Field Declaration: " + field.toString() + " = " + field.get(obj));
					System.out.println(spacing + "Declaring class: " + ObjectClass.getName()); 	
				}
				catch(Exception e){
					e.printStackTrace();
				}
					
					//if field is an array, inspect each element of the array
				if (field.getType().isArray()){
					inspectArray(field, obj);
				}
				//field is not an array
				else{
					
					printFieldInfo(field, obj);
					
					//if an object's field is not primitive and recursive is true, then that field is an object that must be inspected
					if (!field.getType().isPrimitive())
						objectsToInspect.addElement(field);
				}
				
				System.out.println("");
			}
		}
	}
	
	public void inspectFieldRecursive(Object obj, Class ObjectClass, Vector objectsToInspect){
		
		if (objectsToInspect.size() > 0)
		    System.out.println("---- Inspecting Field Objects ----");

		Enumeration elements = objectsToInspect.elements();

		//loop until every element has been inspected
		while(elements.hasMoreElements()){
			
			Field field = (Field) elements.nextElement();
			field.setAccessible(true);
			
			try{
				System.out.println("******************");
				inspect(field.get(obj));
				System.out.println("******************");		
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}	
}
