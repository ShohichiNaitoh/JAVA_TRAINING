package ch02.ex02_14;
import java.lang.reflect.Field;

import junit.framework.TestCase;

import org.junit.Test;


public class LinkedListTest extends TestCase {

	@Test
	public void testGetObject(){
		LinkedList linkedList1 = new LinkedList(1,null);
		assertEquals(1 , linkedList1.getObject());

		LinkedList linkedList2 = new LinkedList(2.0,null);
		assertEquals(2.0 , linkedList2.getObject());

		LinkedList linkedList3 = new LinkedList("aaa",null);
		assertEquals("aaa" , linkedList3.getObject());
	}

	@Test
	public void testGetNext(){
		LinkedList linkedList3 = new LinkedList(1 , null);
		LinkedList linkedList2 = new LinkedList(1 , linkedList3);
		LinkedList linkedList1 = new LinkedList(1 , linkedList2);

		assertSame(linkedList2, linkedList1.getNext());
		assertSame(linkedList3, linkedList2.getNext());
		assertNull(linkedList3.getNext());
	}

	@Test
	public void testSetObject() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException{
		LinkedList linkedList = new LinkedList("before" , null);
		linkedList.setObject("after");

		Class<LinkedList> c = LinkedList.class;
		Field field = c.getDeclaredField("object");
		field.setAccessible(true);

		assertEquals("after" , field.get(linkedList));


		linkedList.setObject(3);

		Class<LinkedList> c1 = LinkedList.class;
		Field field1 = c1.getDeclaredField("object");
		field1.setAccessible(true);

		assertEquals(3 , field1.get(linkedList));
	}

	@Test
	public void testSetNext() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException{
		LinkedList linkedListC = new LinkedList(1 , null);
		LinkedList linkedListB = new LinkedList(1 , linkedListC);
		LinkedList linkedListA = new LinkedList(1 , linkedListB);

		LinkedList linkedList3 = new LinkedList(1 , null);
		LinkedList linkedList2 = new LinkedList(1 , linkedList3);
		LinkedList linkedList1 = new LinkedList(1 , linkedList2);

		linkedList3.setNext(linkedListA);
		assertSame(linkedListA, linkedList3.getNext());
	}
}
