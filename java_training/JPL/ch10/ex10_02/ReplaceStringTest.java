package ch10.ex10_02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import org.junit.Test;

import junit.framework.TestCase;

public class ReplaceStringTest extends TestCase{

	@Test
	public void testReplace(){
		ReplaceStringMain rs = new ReplaceStringMain();
		assertEquals("\\\n", rs.replace("\n"));
		assertEquals("\\\n\\\t", rs.replace("\n\t"));
		assertEquals("\\\naaa\\\t", rs.replace("\naaa\t"));
		assertEquals("\\\n\\\t\\\b\\\r\\\f\\\\\\\'\\\"", rs.replace("\n\t\b\r\f\\\'\""));
	}

	@Test
	public void testReplaceException(){
		ReplaceStringMain rs = new ReplaceStringMain();
		try{
			rs.replace(null);
		}catch(Exception e){
			assertTrue(e instanceof IllegalArgumentException);
			assertEquals("str must not be null.", e.getMessage());
		}
	}
}
