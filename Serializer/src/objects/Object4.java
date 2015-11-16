package objects;

import java.util.ArrayList;

/*
 * An object that uses an instance of one of Java’s collection classes to refer to several other objects. 
 * These objects, too, must be created at the same time.
 */
public class Object4 {
	ArrayList<Object> listObj = new ArrayList<Object>();
	
	public Object4(){
		
	}
	
	public void addObj(Object obj){
		listObj.add(obj);
	}
	
	public void clear(){
		listObj.clear();
	}
}
