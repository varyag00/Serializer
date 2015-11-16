package objects;

/*
 * A simple object with only primitives for instance variables. 
 * The user of your program must also be able to set the values for these fields. 	---IMPORTANT
 */
public class Object0 {
	public int int0;
	private int int1;
	public double doub42 = 42.0;
	
	public Object0(){
		
	}
	
	//allowing user to set fields
	public void setFields(int int0, int int1, double doub42){
		this.int0 = int0;
		this.int1 = int1;
		this.doub42 = doub42;
	}
}
