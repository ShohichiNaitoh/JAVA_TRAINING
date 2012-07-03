package ch13.ex13_05;

import org.junit.Test;

import junit.framework.TestCase;

public class NumberSeparatorTest extends TestCase {

	@Test
	public void testSeparateThreeDigits(){
		assertEquals("123", NumberSeparator.separateThreeDigits("123"));
		assertEquals("1,234", NumberSeparator.separateThreeDigits("1234"));
		assertEquals("12,345", NumberSeparator.separateThreeDigits("12345"));
		assertEquals("123,456", NumberSeparator.separateThreeDigits("123456"));
		assertEquals("1,234,567", NumberSeparator.separateThreeDigits("1234567"));
		assertEquals("12,345,678", NumberSeparator.separateThreeDigits("12345678"));
		assertEquals("123,456,789", NumberSeparator.separateThreeDigits("123456789"));
		assertEquals("1,234,567,890", NumberSeparator.separateThreeDigits("1234567890"));

		assertEquals("‚P‚Q‚R", NumberSeparator.separateThreeDigits("‚P‚Q‚R"));
		assertEquals("‚P,‚Q‚R‚S", NumberSeparator.separateThreeDigits("‚P‚Q‚R‚S"));
		assertEquals("‚P‚Q,‚R‚S‚T", NumberSeparator.separateThreeDigits("‚P‚Q‚R‚S‚T"));
		assertEquals("‚P‚Q‚R,‚S‚T‚U", NumberSeparator.separateThreeDigits("‚P‚Q‚R‚S‚T‚U"));
		assertEquals("‚P,‚Q‚R‚S,‚T‚U‚V", NumberSeparator.separateThreeDigits("‚P‚Q‚R‚S‚T‚U‚V"));
		assertEquals("‚P‚Q,‚R‚S‚T,‚U‚V‚W", NumberSeparator.separateThreeDigits("‚P‚Q‚R‚S‚T‚U‚V‚W"));
		assertEquals("‚P‚Q‚R,‚S‚T‚U,‚V‚W‚X", NumberSeparator.separateThreeDigits("‚P‚Q‚R‚S‚T‚U‚V‚W‚X"));
		assertEquals("‚P,‚Q‚R‚S,‚T‚U‚V,‚W‚X‚O", NumberSeparator.separateThreeDigits("‚P‚Q‚R‚S‚T‚U‚V‚W‚X‚O"));

		assertEquals("1,23‚S,‚T‚U7,8‚X0", NumberSeparator.separateThreeDigits("123‚S‚T‚U78‚X0"));
		assertEquals("1,23‚S,‚T‚U7,8‚X0", NumberSeparator.separateThreeDigits("1‚ 2‚¢3a‚Sb‚Tc‚U‚¦7d8‚X\n0"));

		assertEquals("", NumberSeparator.separateThreeDigits(""));
		assertEquals("", NumberSeparator.separateThreeDigits("        "));
	}

	@Test
	public void testSeparateThreeDigitsException(){
		try{
			NumberSeparator.separateThreeDigits(null);
			fail();
		}catch(Exception e){
			assertTrue(e instanceof IllegalArgumentException);
			assertEquals("parameter number is invalid." , e.getMessage());
		}
	}
}
