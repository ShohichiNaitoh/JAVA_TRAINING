package ch11.ex11_01;

import org.junit.Test;

import junit.framework.TestCase;

public class LinkedListTest extends TestCase {

	@Test
	public void testGetElement(){
		LinkedList<String> strLinkedList2 = new LinkedList<String>("two");
		LinkedList<String> strLinkedList1 = new LinkedList<String>("one" , strLinkedList2);
		assertTrue(strLinkedList1.getElement() instanceof String);
		assertEquals("one", strLinkedList1.getElement());
		assertTrue(strLinkedList2.getElement() instanceof String);
		assertEquals("two", strLinkedList2.getElement());

		LinkedList<Integer> intLinkedList2 = new LinkedList<Integer>(2);
		LinkedList<Integer> intLinkedList1 = new LinkedList<Integer>(1 , intLinkedList2);
		assertTrue(intLinkedList1.getElement() instanceof Integer);
		assertEquals(1, (int)intLinkedList1.getElement());
		assertTrue(intLinkedList2.getElement() instanceof Integer);
		assertEquals(2, (int)intLinkedList2.getElement());

		LinkedList<Double> doubleLinkedList2 = new LinkedList<Double>(2.0);
		LinkedList<Double> doubleLinkedList1 = new LinkedList<Double>(1.0 , doubleLinkedList2);
		assertTrue(doubleLinkedList1.getElement() instanceof Double);
		assertEquals(1.0, doubleLinkedList1.getElement());
		assertTrue(doubleLinkedList2.getElement() instanceof Double);
		assertEquals(2.0, doubleLinkedList2.getElement());
	}

	@Test
	public void testGetNext(){
		LinkedList<String> strLinkedList2 = new LinkedList<String>("two");
		LinkedList<String> strLinkedList1 = new LinkedList<String>("one" , strLinkedList2);
		assertSame(strLinkedList2 , strLinkedList1.getNext());

		LinkedList<Integer> intLinkedList2 = new LinkedList<Integer>(2);
		LinkedList<Integer> intLinkedList1 = new LinkedList<Integer>(1 , intLinkedList2);
		assertSame(intLinkedList2 , intLinkedList1.getNext());

		LinkedList<Double> doubleLinkedList2 = new LinkedList<Double>(2.0);
		LinkedList<Double> doubleLinkedList1 = new LinkedList<Double>(1.0 , doubleLinkedList2);
		assertSame(doubleLinkedList2 , doubleLinkedList1.getNext());
	}
}
