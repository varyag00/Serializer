/* Name:		J. Daniel Gonzalez
 * UCID:		10058656
 * Class:		CPSC 501
 * Ass:			3
 * 
 * Sources: 	1. Used code provided by Waliur Rahman and Lee Ringham as templates for Serializer, Sender and Receiver
 * 				2. Used my own CPSC 501 Assignment 2 Object Inspect as a template for the Object Visualizer
 */

package receiver;

public class Deserializer {
	
	private Integer referenceID = 0;

	//TODO: get shit done homie
	
	private int getID(Object object) {
		Integer id = referenceID;

//		if(referenceMap.containsKey(object))
//			id = referenceMap.get(object);
//		else {
//			referenceMap.put(object, id);
//			referenceID++;
//		}
		return id;
	}
}
