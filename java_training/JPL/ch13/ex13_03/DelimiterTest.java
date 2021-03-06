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

		result = Delimiter.delimitedString("aaa -abc+ あいう-えお+か", '-', '+');
		assertEquals(2, result.size());
		assertEquals("-abc+", result.get(0));
		assertEquals("-えお+", result.get(1));

		result = Delimiter.delimitedString("a-aa -aあbc+ あいう-えおか", '-', '+');
		assertEquals(1, result.size());
		assertEquals("-aa -aあbc+", result.get(0));

		result = Delimiter.delimitedString("a+aa -aあbc+ あい+う-えおか", '-', '+');
		assertEquals(1, result.size());
		assertEquals("-aあbc+", result.get(0));

		result = Delimiter.delimitedString("a+aa -aあbc+ あい+う-えおか++-+", '-', '+');
		assertEquals(3, result.size());
		assertEquals("-aあbc+", result.get(0));
		assertEquals("-えおか+", result.get(1));
		assertEquals("-+", result.get(2));

		result = Delimiter.delimitedString("a+aa -aあbc+ あい+う-えおか++-+", '-', '-');
		assertEquals(1, result.size());
		assertEquals("-aあbc+ あい+う-", result.get(0));

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
