package ch21.ex21_01;

import java.util.List;

import org.junit.Test;

import junit.framework.TestCase;

public class ComparedListTest extends TestCase {

	@Test
	public void testConvertFileToList(){
		ComparedList cl = new ComparedList();
		List<String> list = cl.convertFileToList("./JPL/ch21/ex21_01/in.txt");

		for(int i=0 ; i<list.size() ; i++){
			//System.out.println(list.get(i));
		}
	}

}
