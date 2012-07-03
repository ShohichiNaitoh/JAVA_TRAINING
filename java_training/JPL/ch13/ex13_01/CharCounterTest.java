package ch13.ex13_01;

import org.junit.Test;

import junit.framework.TestCase;

public class CharCounterTest extends TestCase {

	@Test
	public void testCountSpecifiedCharcter(){
		CharCounter charCounter = new CharCounter();

		assertEquals(1, charCounter.countSpecifiedCharcter("abcdefあいうえお", 'a'));
		assertEquals(1, charCounter.countSpecifiedCharcter("abcdefあいうえお", 'あ'));
		assertEquals(2, charCounter.countSpecifiedCharcter("abcdefあいうえおabcdef", 'a'));
		assertEquals(7, charCounter.countSpecifiedCharcter("aaaaaaa", 'a'));
		assertEquals(7, charCounter.countSpecifiedCharcter("あああああああ", 'あ'));
		assertEquals(0, charCounter.countSpecifiedCharcter("abcdefあいうえお", ' '));
		assertEquals(1, charCounter.countSpecifiedCharcter("\n\t\b\r\f\\", '\b'));
		assertEquals(0, charCounter.countSpecifiedCharcter("\n\t\b\r\f\\", 'b'));
		assertEquals(0, charCounter.countSpecifiedCharcter("", 'あ'));
	}

	@Test
	public void testCountSpecifiedCharacterException(){
		CharCounter charCounter = new CharCounter();

		try{
			charCounter.countSpecifiedCharcter(null, 'a');
			fail();
		}catch(Exception e){
			assertTrue(e instanceof IllegalArgumentException);
			assertEquals("originalStr must not be null.", e.getMessage());
		}

	}
}
