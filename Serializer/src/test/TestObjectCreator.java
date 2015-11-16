package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import main.ObjectCreator;
import objects.*;

public class TestObjectCreator {

	ObjectCreator objCreator;
	
	@Before
	public void setUp() throws Exception {
		objCreator = new ObjectCreator();
	}

	@After
	public void tearDown() throws Exception {
		objCreator = null;
	}

	@Test
	public void testObjCreator0() {
		Object0 obj0 = new Object0();
		
		Object actual = objCreator.createObject(0);
		assertEquals(obj0.getClass(), actual.getClass());
	}
	
	@Test
	public void testObjCreator1() {
		Object1 obj1 = new Object1();
		
		Object actual = objCreator.createObject(1);
		assertEquals(obj1.getClass(), actual.getClass());
	}
	
	@Test
	public void testObjCreator2() {
		Object2 obj2 = new Object2();
		
		Object actual = objCreator.createObject(2);
		assertEquals(obj2.getClass(), actual.getClass());
	}
	
	@Test
	public void testObjCreator3() {
		Object3 obj3 = new Object3();
		
		Object actual = objCreator.createObject(3);
		assertEquals(obj3.getClass(), actual.getClass());
	}
	
	@Test
	public void testObjCreator4() {
		Object4 obj4 = new Object4();
		
		Object actual = objCreator.createObject(4);
		assertEquals(obj4.getClass(), actual.getClass());
	}
	
	

}
