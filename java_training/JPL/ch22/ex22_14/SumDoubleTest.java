package ch22.ex22_14;

import junit.framework.TestCase;

public class SumDoubleTest extends TestCase {

	public void testSumDoubleNumbers(){
		assertEquals(8.1, SumDouble.sumDoubleNumbers("2.4 -1.2 5.7 -9.0 10.2"));
	}
}
