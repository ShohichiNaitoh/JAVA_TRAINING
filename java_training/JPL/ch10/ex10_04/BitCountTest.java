package ch10.ex10_04;

import org.junit.Test;

import junit.framework.TestCase;

public class BitCountTest extends TestCase {

	@Test
	public void testCountWithForLoop(){
		BitCount bitCount = new BitCount();
		assertEquals(bitCount.countWithForLoop(0) , 0);
		assertEquals(bitCount.countWithForLoop(1) , 1);
		assertEquals(bitCount.countWithForLoop(2) , 1);
		assertEquals(bitCount.countWithForLoop(10) , 2);
		assertEquals(bitCount.countWithForLoop(100) , 3);
		assertEquals(bitCount.countWithForLoop(1000) , 6);
		assertEquals(bitCount.countWithForLoop(10000) , 5);
		assertEquals(bitCount.countWithForLoop(-1) , 32);
		assertEquals(bitCount.countWithForLoop(-2) , 31);
		assertEquals(bitCount.countWithForLoop(-10) , 30);
		assertEquals(bitCount.countWithForLoop(-100) , 28);
		assertEquals(bitCount.countWithForLoop(-1000) , 24);
		assertEquals(bitCount.countWithForLoop(-10000) , 24);
	}

	@Test
	public void testCountWithWhileLoop(){
		BitCount bitCount = new BitCount();
		assertEquals(bitCount.countWithWhileLoop(0) , 0);
		assertEquals(bitCount.countWithWhileLoop(1) , 1);
		assertEquals(bitCount.countWithWhileLoop(2) , 1);
		assertEquals(bitCount.countWithWhileLoop(10) , 2);
		assertEquals(bitCount.countWithWhileLoop(100) , 3);
		assertEquals(bitCount.countWithWhileLoop(1000) , 6);
		assertEquals(bitCount.countWithWhileLoop(10000) , 5);
		assertEquals(bitCount.countWithWhileLoop(-1) , 32);
		assertEquals(bitCount.countWithWhileLoop(-2) , 31);
		assertEquals(bitCount.countWithWhileLoop(-10) , 30);
		assertEquals(bitCount.countWithWhileLoop(-100) , 28);
		assertEquals(bitCount.countWithWhileLoop(-1000) , 24);
		assertEquals(bitCount.countWithWhileLoop(-10000) , 24);
	}

	@Test
	public void testCountWithDoWhile(){
		BitCount bitCount = new BitCount();
		assertEquals(bitCount.countWithDoWhile(0) , 0);
		assertEquals(bitCount.countWithDoWhile(1) , 1);
		assertEquals(bitCount.countWithDoWhile(2) , 1);
		assertEquals(bitCount.countWithDoWhile(10) , 2);
		assertEquals(bitCount.countWithDoWhile(100) , 3);
		assertEquals(bitCount.countWithDoWhile(1000) , 6);
		assertEquals(bitCount.countWithDoWhile(10000) , 5);
		assertEquals(bitCount.countWithDoWhile(-1) , 32);
		assertEquals(bitCount.countWithDoWhile(-2) , 31);
		assertEquals(bitCount.countWithDoWhile(-10) , 30);
		assertEquals(bitCount.countWithDoWhile(-100) , 28);
		assertEquals(bitCount.countWithDoWhile(-1000) , 24);
		assertEquals(bitCount.countWithDoWhile(-10000) , 24);
	}

}
