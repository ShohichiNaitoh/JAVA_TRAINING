package ch07.ex07_03;

import java.util.Arrays;
import org.junit.Test;
import junit.framework.TestCase;

public class PascalTriangleTest extends TestCase{

	int[][] pascalTriangleDepth1 = {
			{1},
	};

	int[][] pascalTriangleDepth2 = {
			{1},
			{1 , 1},
	};

	int[][] pascalTriangleDepth3= {
			{1},
			{1 , 1},
			{1 , 2 , 1},
	};

	int[][] pascalTriangleDepth4= {
			{1},
			{1 , 1},
			{1 , 2 , 1},
			{1 , 3 , 3 , 1},
	};

	int[][] pascalTriangleDepth5= {
			{1},
			{1 , 1},
			{1 , 2 , 1},
			{1 , 3 , 3 , 1},
			{1 , 4 , 6 , 4 , 1},
	};

	@Test
	public void testPascalTriangle(){
		PascalTriangle pt1 = new PascalTriangle(1);
		assertTrue(Arrays.deepEquals(pascalTriangleDepth1 , pt1.getPascalsTriangleElements()));

		PascalTriangle pt2 = new PascalTriangle(2);
		assertTrue(Arrays.deepEquals(pascalTriangleDepth2 , pt2.getPascalsTriangleElements()));

		PascalTriangle pt3 = new PascalTriangle(3);
		assertTrue(Arrays.deepEquals(pascalTriangleDepth3 , pt3.getPascalsTriangleElements()));

		PascalTriangle pt4 = new PascalTriangle(4);
		assertTrue(Arrays.deepEquals(pascalTriangleDepth4 , pt4.getPascalsTriangleElements()));

		PascalTriangle pt5 = new PascalTriangle(5);
		assertTrue(Arrays.deepEquals(pascalTriangleDepth5 , pt5.getPascalsTriangleElements()));
	}

	@Test
	public void testPascalTriangleException(){
		try{
			PascalTriangle pt = new PascalTriangle(0);
		}catch(Exception e){
			assertTrue(e instanceof IllegalArgumentException);
			assertEquals("depth must be lager than 0.", e.getMessage());
		}
	}
}
