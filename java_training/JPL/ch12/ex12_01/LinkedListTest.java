package ch12.ex12_01;

import org.junit.Test;

import junit.framework.TestCase;

public class LinkedListTest extends TestCase {

	@Test
	public void testFind() throws ObjectNotFoundException{
		LinkedList<String> linkedList4 = new LinkedList<String>("four");
		LinkedList<String> linkedList3 = new LinkedList<String>("three" , linkedList4);
		LinkedList<String> linkedList2 = new LinkedList<String>("two" , linkedList3);
		LinkedList<String> linkedList1 = new LinkedList<String>("one" , linkedList2);

		assertSame(linkedList2, linkedList1.find("two"));
		assertSame(linkedList4, linkedList1.find("four"));
		assertSame(linkedList2, linkedList2.find("two"));
		assertSame(linkedList3, linkedList1.find("three"));

		try{
			linkedList3.find("two");
		}catch(Exception e){
			assertTrue(e instanceof ObjectNotFoundException);
			assertEquals("two is not found.", e.getMessage());
		}

		try{
			linkedList4.find("");
		}catch(Exception e){
			assertTrue(e instanceof ObjectNotFoundException);
			assertEquals(" is not found.", e.getMessage());
		}

		try{
			linkedList3.find(null);
		}catch(Exception e){
			assertTrue(e instanceof IllegalArgumentException);
			assertEquals("parameter target must not be null.", e.getMessage());
		}

	}
}
