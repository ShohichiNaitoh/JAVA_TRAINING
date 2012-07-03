package ch13.ex13_03;

import java.util.ArrayList;

import org.junit.Test;

import junit.framework.TestCase;

public class DelimiterTest extends TestCase {

	@Test
	public void testDelimitedString(){
		ArrayList<String> result = new ArrayList<String>();

		result = Delimiter.delimitedString("aaa -abc+ ", '-', '+');
		assertEquals(1, result.size());
		assertEquals("-abc+", result.get(0));

		result = Delimiter.delimitedString("aaa -abc+ ‚ ‚¢‚¤-‚¦‚¨+‚©", '-', '+');
		assertEquals(2, result.size());
		assertEquals("-abc+", result.get(0));
		assertEquals("-‚¦‚¨+", result.get(1));

		result = Delimiter.delimitedString("a-aa -a‚ bc+ ‚ ‚¢‚¤-‚¦‚¨‚©", '-', '+');
		assertEquals(1, result.size());
		assertEquals("-aa -a‚ bc+", result.get(0));

		result = Delimiter.delimitedString("a+aa -a‚ bc+ ‚ ‚¢+‚¤-‚¦‚¨‚©", '-', '+');
		assertEquals(1, result.size());
		assertEquals("-a‚ bc+", result.get(0));

		result = Delimiter.delimitedString("a+aa -a‚ bc+ ‚ ‚¢+‚¤-‚¦‚¨‚©++-+", '-', '+');
		assertEquals(3, result.size());
		assertEquals("-a‚ bc+", result.get(0));
		assertEquals("-‚¦‚¨‚©+", result.get(1));
		assertEquals("-+", result.get(2));

		result = Delimiter.delimitedString("a+aa -a‚ bc+ ‚ ‚¢+‚¤-‚¦‚¨‚©++-+", '-', '-');
		assertEquals(1, result.size());
		assertEquals("-a‚ bc+ ‚ ‚¢+‚¤-", result.get(0));

		result = Delimiter.delimitedString("------", '-', '-');
		assertEquals(3, result.size());
		assertEquals("--", result.get(0));
		assertEquals("--", result.get(1));
		assertEquals("--", result.get(2));

		result = Delimiter.delimitedString("", '-', '+');
		assertEquals(0, result.size());
	}

	@Test
	public void testDelimitedStringException(){
		try{
			Delimiter.delimitedString(null, '-', '+');
			fail();
		}catch(Exception e){
			assertTrue(e instanceof IllegalArgumentException);
			assertEquals("parameter from is invalid." , e.getMessage());
		}
	}
}
