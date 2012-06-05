package ch09.ex09_02;

import org.junit.Test;

import junit.framework.TestCase;

public class BitCountTest extends TestCase {

	@Test
	public void testCount(){
		BitCount bitCount = new BitCount();
		assertEquals(bitCount.count(0) , 0);
		assertEquals(bitCount.count(1) , 1);
		assertEquals(bitCount.count(2) , 1);
		assertEquals(bitCount.count(10) , 2);
		assertEquals(bitCount.count(100) , 3);
		assertEquals(bitCount.count(1000) , 6);
		assertEquals(bitCount.count(10000) , 5);
		assertEquals(bitCount.count(-1) , 32);
		assertEquals(bitCount.count(-2) , 31);
		assertEquals(bitCount.count(-10) , 30);
		assertEquals(bitCount.count(-100) , 28);
		assertEquals(bitCount.count(-1000) , 24);
		assertEquals(bitCount.count(-10000) , 24);
	}
}
