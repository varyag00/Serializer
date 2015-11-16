package objects;

/*
 * An object that contains an array of object references.
 * The other objects must also be created at the same time.
 */
public class Object3 {
	private Object[] objArr = new Object[10];
	private int ctr = 0;
	
	public Object3(){
		
	}
	
	public void addObj(Object obj){
		try {
			objArr[ctr] = obj;
			ctr++;
		}
		catch(Exception e){
			System.out.println("No room to add Objects to objArr");
		}
	}
	
	public void clear(){
		objArr = new Object[10];
	}
}
