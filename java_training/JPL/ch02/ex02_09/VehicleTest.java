package ch02.ex02_09;
import org.junit.Test;

import junit.framework.TestCase;


public class VehicleTest extends TestCase {

	@Test
	public void testGetMaxID(){

		assertEquals(0, Vehicle.getMaxID());

		Vehicle vehicle1 = new Vehicle();

		assertEquals(0, Vehicle.getMaxID());

		Vehicle vehicle2 = new Vehicle();
		Vehicle vehicle3 = new Vehicle("yamada");
		Vehicle vehicle4 = new Vehicle("satoh");

		assertEquals(3, Vehicle.getMaxID());
	}
}
