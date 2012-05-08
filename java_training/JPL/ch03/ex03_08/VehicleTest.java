package ch03.ex03_08;

import org.junit.Test;
import junit.framework.TestCase;

public class VehicleTest extends TestCase {

	@Test
	public void testClone(){
		Vehicle vehicle = new Vehicle("Satoh");
		vehicle.changeSpeed(50.0);
		vehicle.turn(270);

		Vehicle cloneObj = null;
		try {
			cloneObj = vehicle.clone();
		} catch (CloneNotSupportedException e) {
			fail();
		}

		assertEquals(vehicle.toString(), cloneObj.toString());
	}
}
