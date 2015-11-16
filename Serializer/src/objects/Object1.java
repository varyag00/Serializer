package objects;


/*
 * An object that contains references to other objects. 
 * Of course, these other objects must also be created at the same time, 
 * and their primitive instance variables must be settable by the user. 
 * Your program must also be able to deal with circular references (i.e. objects connected in a graph).
 */
public class Object1 {

	private int int42 = 42;
	private Object0 obj0;
	
	//constructors
	public Object1(){
		
	}
	
	//allowing user to set fields
	public Object1(int int0){
		this.int42 = int0;
	}
	public void setObj0(Object0 obj0){
		this.obj0 = obj0;
	}
	
	//setting circular references
	public Object1 obj1;
	public void setCircularRef(){
		obj1 = new Object1();
		obj1.obj1 = this;
	}
}
