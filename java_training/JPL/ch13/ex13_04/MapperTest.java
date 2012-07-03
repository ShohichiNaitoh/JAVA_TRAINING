package ch13.ex13_04;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import junit.framework.TestCase;

public class MapperTest extends TestCase {

	@Test
	public void testConvertToObjectFromString() throws IOException{
		String str = "Integer 1\n" + "Short 2\n" + "Long 3\n"
					+ "Byte 4\n" + "Float 5.1\n" + "Double 6.1\n" + "Boolean true\n" + "Character a\n";
		ArrayList<Object> objList = Mapper.convertToObjectFromString(str);

		assertEquals(8, objList.size());

		assertTrue(objList.get(0) instanceof Integer);
		assertEquals(1, objList.get(0));

		assertTrue(objList.get(1) instanceof Short);
		assertEquals((short)2, objList.get(1));

		assertTrue(objList.get(2) instanceof Long);
		assertEquals((long)3, objList.get(2));

		assertTrue(objList.get(3) instanceof Byte);
		assertEquals((byte)4, objList.get(3));

		assertTrue(objList.get(4) instanceof Float);
		assertEquals((float)5.1, objList.get(4));

		assertTrue(objList.get(5) instanceof Double);
		assertEquals((double)6.1, objList.get(5));

		assertTrue(objList.get(6) instanceof Boolean);
		assertEquals(true, objList.get(6));

		assertTrue(objList.get(7) instanceof Character);
		assertEquals('a', objList.get(7));
	}

	@Test
	public void testConvertToObjectFromStringException(){
		try{
			Mapper.convertToObjectFromString(null);
			fail();
		}catch(Exception e){
			assertTrue(e instanceof IllegalArgumentException);
			assertEquals("parameter fullTxt must not be null." , e.getMessage());
		}

		String str = "Integer 1 3\n";
		try{
			Mapper.convertToObjectFromString(str);
		}catch(Exception e){
			assertTrue(e instanceof IllegalArgumentException);
			assertEquals("\"type value\" format is invalid." , e.getMessage());
		}

		String str2 = "Character abc\n";
		try{
			Mapper.convertToObjectFromString(str2);
		}catch(Exception e){
			assertTrue(e instanceof IllegalArgumentException);
			assertEquals("type Character's value is invalid." , e.getMessage());
		}

		String str3 = "Unsupported 3\n";
		try{
			Mapper.convertToObjectFromString(str3);
		}catch(Exception e){
			assertTrue(e instanceof IllegalArgumentException);
			assertEquals("specified type is unsupported." , e.getMessage());
		}
	}
}
