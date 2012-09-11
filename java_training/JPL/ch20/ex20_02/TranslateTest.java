package ch20.ex20_02;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

import junit.framework.TestCase;


public class TranslateTest extends TestCase{

	@Test
	public void testRead() throws IOException{
		InputStream in;
		in = new FileInputStream(new File("./JPL/ch20/ex20_02/in.txt"));

		TranslateByteInputStream tbi = new TranslateByteInputStream(in, 'A', 'a');
		StringBuffer sb = new StringBuffer();
		int c;
		byte[] b;
		while((c = tbi.read()) != -1){
			sb.append((char)c);
		}
		assertEquals("aaaaBCDEaaaa", sb.toString());
	}

	public void testRead2() throws IOException{
		InputStream in;
		in = new FileInputStream(new File("./JPL/ch20/ex20_02/in.txt"));

		TranslateByteInputStream tbi = new TranslateByteInputStream(in, 'A', 'a');
		int expectedElementNumber = 12;
		byte[] b = new byte[expectedElementNumber];
		int c;
		while((c = tbi.read(b)) != -1){
		}
		assertEquals("aaaaBCDEaaaa", new String(b , "UTF-8").toString());
	}

}
