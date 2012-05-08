package ch03.ex03_08;

import junit.framework.TestCase;
import org.junit.Test;

public class PassengerVehicleTest extends TestCase {

	@Test
	public void testClone(){
		PassengerVehicle vehicle = new PassengerVehicle("Satoh" , 5);
		vehicle.changeSpeed(30.0);
		vehicle.turn(120);
		vehicle.setNumberOfSittingPerson(3);

		PassengerVehicle cloneObj = null;
		try {
			cloneObj = vehicle.clone();
		} catch (CloneNotSupportedException e) {
			fail();
		}

		assertEquals(vehicle.toString(), cloneObj.toString());
	}

}
