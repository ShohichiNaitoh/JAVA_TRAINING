package ch03.ex03_10;

import org.junit.Test;
import junit.framework.TestCase;

public class LinkedListTest extends TestCase {

	@Test
	public void testClone(){
		Vehicle vehicle1 = new Vehicle("Owner1");
		vehicle1.changeSpeed(10);
		vehicle1.turn(10);

		Vehicle vehicle2 = new Vehicle("Owner2");
		vehicle2.changeSpeed(20);
		vehicle2.turn(20);

		Vehicle vehicle3 = new Vehicle("Owner3");
		vehicle3.changeSpeed(30);
		vehicle3.turn(30);

		Vehicle vehicle4 = new Vehicle("Owner4");
		vehicle4.changeSpeed(40);
		vehicle4.turn(40);

		Vehicle vehicle5 = new Vehicle("Owner5");
		vehicle5.changeSpeed(50);
		vehicle5.turn(50);

		LinkedList linkedList5 = new LinkedList(vehicle5 , null);
		LinkedList linkedList4 = new LinkedList(vehicle4 , linkedList5);
		LinkedList linkedList3 = new LinkedList(vehicle3 , linkedList4);
		LinkedList linkedList2 = new LinkedList(vehicle2 , linkedList3);
		LinkedList linkedList1 = new LinkedList(vehicle1 , linkedList2);

		LinkedList cloneObj = linkedList1.clone();

		assertEquals(5, cloneObj.getCount());

		assertNotSame(cloneObj, linkedList1);
		assertSame(cloneObj.getObject() , linkedList1.getObject());

		LinkedList cloneList2 = cloneObj.getNext();
		assertNotSame(cloneList2, linkedList2);
		assertSame(cloneList2.getObject() , linkedList2.getObject());

		LinkedList cloneList3 = cloneList2.getNext();
		assertNotSame(cloneList3, linkedList3);
		assertSame(cloneList3.getObject() , linkedList3.getObject());

		LinkedList cloneList4 = cloneList3.getNext();
		assertNotSame(cloneList4, linkedList4);
		assertSame(cloneList4.getObject() , linkedList4.getObject());

		LinkedList cloneList5 = cloneList4.getNext();
		assertNotSame(cloneList5, linkedList5);
		assertSame(cloneList5.getObject() , linkedList5.getObject());
	}

}
