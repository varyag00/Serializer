/* Name:		J. Daniel Gonzalez
 * UCID:		10058656
 * Class:		CPSC 501
 * Ass:			3
 */

package main;

import objects.*;

public class ObjectCreator {

	
	
		//creates a new object based on input type and prompts user for instance variable values
	public Object createObject(int selection) throws IllegalArgumentException{
		Object obj;
		
		switch (selection){
			case 0: 
				obj = new Object0();
				break;
			case 1:
				obj = new Object1();
				break;
			case 2:
				obj = new Object2();
				break;
			case 3:
				obj = new Object3();
				break;
			case 4:
				obj = new Object4();
				break;
			default:
				System.out.println("Invalid selection. Selection must be an integer between 0 and 4");
				throw new IllegalArgumentException();
		}

		//TODO: allow user set instance variables !!!!!!!
		
		return obj; 
		
		
	}
}
