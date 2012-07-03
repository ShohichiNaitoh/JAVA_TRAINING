package ch11.ex11_02;

import org.junit.Test;

import junit.framework.TestCase;

public class AttrTest extends TestCase {

	@Test
	public void testAttrInteger(){
		Attr<Integer> intAttr = new Attr<Integer>("key1", 1);
		assertTrue(intAttr.getValue() instanceof Integer);
		assertEquals(1, (int)intAttr.getValue());

		intAttr.setValue(2);
		assertTrue(intAttr.getValue() instanceof Integer);
		assertEquals(2, (int)intAttr.getValue());
	}


	@Test
	public void testAttrString(){
		Attr<String> strAttr = new Attr<String>("key1" , "one");
		assertTrue(strAttr.getValue() instanceof String);
		assertEquals("one", strAttr.getValue());

		strAttr.setValue("two");
		assertTrue(strAttr.getValue() instanceof String);
		assertEquals("two", strAttr.getValue());
	}
}
