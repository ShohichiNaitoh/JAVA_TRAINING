package ch22.ex22_08;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

public class CSVReaderTest extends TestCase {

	public void testReadCSVTable(){
		String[] strArray = {"a" , "b" , "c" , "d"};
		StringBuffer sb = new StringBuffer();
		for(int i=0 ; i<strArray.length ; i++){
			if(i == strArray.length-1){
				sb.append(strArray[i] + "\n");
			}else{
				sb.append(strArray[i] + ",");
			}
		}

		List<String[]> actual = null;
		try {
			actual = CSVReader.readCSVTable(new StringReader(sb.toString()), 4);
		} catch (IOException e) {
			fail();
			e.printStackTrace();
		}

		for(String[] line : actual){
			for(int i=0 ; i<line.length ; i++){
				assertEquals(strArray[i], line[i]);
			}
		}
	}

	public void testReadCSVTable2(){
		String[] strArray = {"a" , "b" , "c" , "d" , "e"};
		StringBuffer sb = new StringBuffer();
		for(int i=0 ; i<strArray.length ; i++){
			if(i == strArray.length-1){
				sb.append(strArray[i] + "\n");
			}else{
				sb.append(strArray[i] + ",");
			}
		}

		List<String[]> actual = null;
		try {
			actual = CSVReader.readCSVTable(new StringReader(sb.toString()), 7);
			fail();
		} catch (Exception e) {
			assertTrue(e instanceof IOException);
		}
	}
}
