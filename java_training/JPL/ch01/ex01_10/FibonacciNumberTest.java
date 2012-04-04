package ch01.ex01_10;
import org.junit.Test;

import junit.framework.TestCase;


public class FibonacciNumberTest extends TestCase {

	@Test
	public void testFibonacciNumber(){
		FibonacciNumber num1 = new FibonacciNumber(1);
		assertEquals(1, num1.getNumber());
		assertEquals(false, num1.isEven());
		
		FibonacciNumber num2 = new FibonacciNumber(4);
		assertEquals(4, num2.getNumber());
		assertEquals(true, num2.isEven());
	}
}
