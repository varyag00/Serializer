import java.lang.reflect.*;

public class Test {
	
	public static void main(String[] arg) {
		Object object = null;
		Class classObject = null;
		
		try {
			classObject = Class.forName(arg[0]);
			object = classObject.newInstance();
		} catch(InstantiationException e) {
			System.out.println(e);
			return;
		} catch(IllegalAccessException e) {
			System.out.println(e);
			return;
		} catch(ClassNotFoundException e) {
			System.out.println(e);
			return;
		}
		
		try {
			Method method = classObject.getMethod(arg[1], null);
			method.invoke(object, null);
		} catch(NoSuchMethodException e) {
			System.out.println(e);
			return;
		} catch (IllegalAccessException e) {
			System.out.println(e);
			return;
		} catch (IllegalArgumentException e) {
			System.out.println(e);
			return;
		} catch (InvocationTargetException e) {
			System.out.println(e);
			return;
		}
	}
}
