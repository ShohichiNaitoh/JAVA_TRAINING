package ch01.ex01_08;
import org.junit.Test;

import junit.framework.TestCase;


public class PointTest extends TestCase {

	@Test
	public void testSetPoint(){
		Point point1 = new Point();
		point1.x = 1.0;
		point1.y = 2.0;
		
		Point point2 = new Point();
		point2.x = 10.0;
		point2.y = 20.0;
		
		point1.setPoint(point2);
		assertEquals(point2.x, point1.x);
		assertEquals(point2.y, point1.y);
	}
	
	@Test
	public void testSetPointException(){
		Point point1 = new Point();
		point1.x = 1.0;
		point1.y = 2.0;
		try{
			point1.setPoint(null);
			fail();
		}catch(Exception e){
			assertTrue(e instanceof NullPointerException);
		}
	}
}
