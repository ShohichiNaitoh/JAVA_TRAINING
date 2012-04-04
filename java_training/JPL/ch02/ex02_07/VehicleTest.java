package ch02.ex02_07;
import org.junit.Test;

import junit.framework.TestCase;


public class VehicleTest extends TestCase{

	@Test
	public void testVehicleTest(){
		Vehicle vehicle1 = new Vehicle("yamada");
		vehicle1.speed = 100.0;
		vehicle1.angle = 30.0;

		assertEquals(0, vehicle1.id);
		assertEquals("yamada", vehicle1.owner);


		Vehicle vehicle2 = new Vehicle("satoh");
		vehicle2.speed = 50.0;
		vehicle2.angle = 60.0;

		assertEquals(1, vehicle2.id);
		assertEquals("satoh", vehicle2.owner);


		Vehicle vehicle3 = new Vehicle();
		vehicle3.speed = 0.0;
		vehicle3.angle = 120.0;

		assertEquals(2, vehicle3.id);
		assertEquals("", vehicle3.owner);
	}
}
