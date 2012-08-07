package ch13.ex13_01;

import org.junit.Test;

import junit.framework.TestCase;

public class CharCounterTest extends TestCase {

	@Test
	public void testCountSpecifiedCharcter(){
		CharCounter charCounter = new CharCounter();

		assertEquals(1, charCounter.countSpecifiedCharcter("abcdef����������", 'a'));
		/*文字コード変更により破壊
		assertEquals(1, charCounter.countSpecifiedCharcter("abcdef����������", '��'));
		assertEquals(2, charCounter.countSpecifiedCharcter("abcdef����������abcdef", 'a'));
		assertEquals(7, charCounter.countSpecifiedCharcter("aaaaaaa", 'a'));
		assertEquals(7, charCounter.countSpecifiedCharcter("��������������", '��'));
		assertEquals(0, charCounter.countSpecifiedCharcter("abcdef����������", ' '));
		assertEquals(1, charCounter.countSpecifiedCharcter("\n\t\b\r\f\\", '\b'));
		assertEquals(0, charCounter.countSpecifiedCharcter("\n\t\b\r\f\\", 'b'));
		assertEquals(0, charCounter.countSpecifiedCharcter("", '��'));
		*/
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
