package ch02.ex02_06;
import org.junit.Test;

import junit.framework.TestCase;


public class LinkedListTest extends TestCase {

	@Test
	public void testLinkedList(){
		Vehicle vehicle1 = new Vehicle();
		vehicle1.id = Vehicle.nextID++;
		vehicle1.speed = 100.0;
		vehicle1.angle = 30.0;
		vehicle1.owner = "yamada";

		Vehicle vehicle2 = new Vehicle();
		vehicle2.id = Vehicle.nextID++;
		vehicle2.speed = 50.0;
		vehicle2.angle = 60.0;
		vehicle2.owner = "satoh";

		Vehicle vehicle3 = new Vehicle();
		vehicle3.id = Vehicle.nextID++;
		vehicle3.speed = 0.0;
		vehicle3.angle = 120.0;
		vehicle3.owner = "sasaki";

		LinkedList linkedList1 = new LinkedList();
		LinkedList linkedList2 = new LinkedList();
		LinkedList linkedList3 = new LinkedList();

		linkedList1.object = vehicle1;
		linkedList1.next = linkedList2;

		linkedList2.object = vehicle2;
		linkedList2.next = linkedList3;

		linkedList3.object = vehicle3;
		linkedList3.next = null;

		assertSame(vehicle1, linkedList1.object);
		assertSame(linkedList2, linkedList1.next);

		assertSame(vehicle2, linkedList2.object);
		assertSame(linkedList3, linkedList2.next);

		assertSame(vehicle3, linkedList3.object);
		assertNull(linkedList3.next);
	}
}
