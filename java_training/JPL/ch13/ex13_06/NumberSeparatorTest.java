package ch13.ex13_06;

import org.junit.Test;

import ch13.ex13_06.NumberSeparator;

import junit.framework.TestCase;

public class NumberSeparatorTest extends TestCase {

	@Test
	public void testSeparateSpecifiedDigits(){
		assertEquals("1,234,567,890", NumberSeparator.separateSpecifiedDigits("1234567890" , ',' , 3));
		assertEquals("12,34,56,78,90", NumberSeparator.separateSpecifiedDigits("1234567890" , ',' , 2));
		assertEquals("1,2,3,4,5,6,7,8,9,0", NumberSeparator.separateSpecifiedDigits("1234567890" , ',' , 1));
		assertEquals("12,3456,7890", NumberSeparator.separateSpecifiedDigits("1234567890" , ',' , 4));
		assertEquals("12345,67890", NumberSeparator.separateSpecifiedDigits("1234567890" , ',' , 5));
		assertEquals("1234567890", NumberSeparator.separateSpecifiedDigits("1234567890" , ',' , 10));
		assertEquals("1234567890", NumberSeparator.separateSpecifiedDigits("1234567890" , ',' , 100));

		assertEquals("1a234a567a890", NumberSeparator.separateSpecifiedDigits("1234567890" , 'a' , 3));
		assertEquals("1‚ 234‚ 567‚ 890", NumberSeparator.separateSpecifiedDigits("1234567890" , '‚ ' , 3));

		assertEquals("‚P,‚Q‚R‚S,‚T‚U‚V,‚W‚X‚O", NumberSeparator.separateSpecifiedDigits("‚P‚Q‚R‚S‚T‚U‚V‚W‚X‚O" , ',' , 3));
		assertEquals("‚P‚Q,‚R‚S,‚T‚U,‚V‚W,‚X‚O", NumberSeparator.separateSpecifiedDigits("‚P‚Q‚R‚S‚T‚U‚V‚W‚X‚O" , ',' , 2));
		assertEquals("‚P,‚Q,‚R,‚S,‚T,‚U,‚V,‚W,‚X,‚O", NumberSeparator.separateSpecifiedDigits("‚P‚Q‚R‚S‚T‚U‚V‚W‚X‚O" , ',' , 1));
		assertEquals("‚P‚Q‚R‚S‚T‚U‚V‚W‚X‚O", NumberSeparator.separateSpecifiedDigits("‚P‚Q‚R‚S‚T‚U‚V‚W‚X‚O" , ',' , 100));

		assertEquals("‚Pa‚Q‚R‚Sa‚T‚U‚Va‚W‚X‚O", NumberSeparator.separateSpecifiedDigits("‚P‚Q‚R‚S‚T‚U‚V‚W‚X‚O" , 'a' , 3));
		assertEquals("‚P‚ ‚Q‚R‚S‚ ‚T‚U‚V‚ ‚W‚X‚O", NumberSeparator.separateSpecifiedDigits("‚P‚Q‚R‚S‚T‚U‚V‚W‚X‚O" , '‚ ' , 3));

		assertEquals("1,23‚S,‚T‚U7,8‚X0", NumberSeparator.separateSpecifiedDigits("123‚S‚T‚U78‚X0" , ',' , 3));
		assertEquals("1,23‚S,‚T‚U7,8‚X0", NumberSeparator.separateSpecifiedDigits("1‚ 2‚¢3a‚Sb‚Tc‚U‚¦7d8‚X\n0" , ',' , 3));
	}

	@Test
	public void testSeparateSpecifiedDigitsException(){
		try{
			NumberSeparator.separateSpecifiedDigits(null , ',' , 3);
			fail();
		}catch(Exception e){
			assertTrue(e instanceof IllegalArgumentException);
			assertEquals("parameter number is invalid." , e.getMessage());
		}


		try{
			NumberSeparator.separateSpecifiedDigits("1234567" , ',' , 0);
			fail();
		}catch(Exception e){
			assertTrue(e instanceof IllegalArgumentException);
			assertEquals("parameter intervalDigit is invalid." , e.getMessage());
		}

	}
}
