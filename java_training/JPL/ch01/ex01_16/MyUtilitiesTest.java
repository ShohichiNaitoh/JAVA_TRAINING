package ch01.ex01_16;
import org.junit.Test;

import junit.framework.TestCase;


public class MyUtilitiesTest extends TestCase {

	@Test
	public void testGetDataSet(){
		MyUtilities myUtil = new MyUtilities();
		try {
			myUtil.getDataSet("data\\test");
		} catch (BadDataSetException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testGetDataSetException(){
		MyUtilities myUtil = new MyUtilities();
		try {
			myUtil.getDataSet("data\\aaa");
			fail();
		} catch (BadDataSetException e) {
			e.printStackTrace();
		}
	}
}
