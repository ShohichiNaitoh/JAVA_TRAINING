package ch02.ex02_16;
import org.junit.Test;

import junit.framework.TestCase;


public class LinkedListTest extends TestCase {

	@Test
	public void testGetCount(){
		LinkedList linkedList6 = new LinkedList(1 , null);
		assertEquals(1, linkedList6.getCount());

		LinkedList linkedList5 = new LinkedList(1 , linkedList6);
		assertEquals(2, linkedList5.getCount());

		LinkedList linkedList4 = new LinkedList(1 , linkedList5);
		assertEquals(3, linkedList4.getCount());

		LinkedList linkedList3 = new LinkedList(1 , linkedList4);
		assertEquals(4, linkedList3.getCount());

		LinkedList linkedList2 = new LinkedList(1 , linkedList3);
		assertEquals(5, linkedList2.getCount());

		LinkedList linkedList1 = new LinkedList(1 , linkedList2);
		assertEquals(6, linkedList1.getCount());

		linkedList3.setNext(null);
		assertEquals(3, linkedList1.getCount());

		assertEquals(3, linkedList4.getCount());
	}
}
