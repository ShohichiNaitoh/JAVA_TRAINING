package ch13.ex13_02;

import org.junit.Test;

import junit.framework.TestCase;

public class StringCounterTest extends TestCase {

	@Test
	public void testCountSpecifiedString(){
		StringCounter stringCounter = new StringCounter();

		assertEquals(1, stringCounter.countSpecifiedString("abcdefあいうえお", "abc"));
		assertEquals(4, stringCounter.countSpecifiedString("abcdefあいabcうえabcおかきabc", "abc"));
		assertEquals(4, stringCounter.countSpecifiedString("abあいうcdefあいabcうえあいabcおあいかきabc", "あい"));
		assertEquals(2, stringCounter.countSpecifiedString("\nabc\t\bあいう\r\fabc\\", "ab"));
		assertEquals(3, stringCounter.countSpecifiedString("\n abc\t\bあいう \r\fab c\\", " "));
		assertEquals(5, stringCounter.countSpecifiedString("aaaaaaaaaa", "aa"));
	}

	@Test
	public void testCountSpecifiedStringException(){
		StringCounter stringCounter = new StringCounter();

		try{
			stringCounter.countSpecifiedString(null, "あいう");
			fail();
		}catch(Exception e){
			assertTrue(e instanceof IllegalArgumentException);
			assertEquals("parameter originalStr is invalid.", e.getMessage());
		}

		try{
			stringCounter.countSpecifiedString("", "あいう");
			fail();
		}catch(Exception e){
			assertTrue(e instanceof IllegalArgumentException);
			assertEquals("parameter originalStr is invalid.", e.getMessage());
		}

		try{
			stringCounter.countSpecifiedString("あいう", null);
			fail();
		}catch(Exception e){
			assertTrue(e instanceof IllegalArgumentException);
			assertEquals("parameter targetStr is invalid.", e.getMessage());
		}

		try{
			stringCounter.countSpecifiedString("あいう", "");
			fail();
		}catch(Exception e){
			assertTrue(e instanceof IllegalArgumentException);
			assertEquals("parameter targetStr is invalid.", e.getMessage());
		}
	}
}
