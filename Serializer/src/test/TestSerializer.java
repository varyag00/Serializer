package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.ObjectCreator;
import main.Serializer;

public class TestSerializer {
	
	ObjectCreator objCreator;
	Serializer ser;
	Object o;
	
	@Before
	public void setUp() throws Exception {
		objCreator = new ObjectCreator();
		o = objCreator.createObject(0); 
		ser = new Serializer();	
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
	}

}
