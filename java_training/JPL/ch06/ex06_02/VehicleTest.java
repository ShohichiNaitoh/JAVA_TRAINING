package ch06.ex06_02;
import org.junit.Test;

import ch06.ex06_02.Vehicle.DIRECTION;

import junit.framework.TestCase;


public class VehicleTest extends TestCase {

	@Test
	public void testTurn(){
		Vehicle vehicle = new Vehicle("sasaki");
		vehicle.turn(DIRECTION.RIGHT);
		assertEquals(90.0, vehicle.getAngle());

		vehicle.turn(DIRECTION.RIGHT);
		assertEquals(180.0, vehicle.getAngle());

		vehicle.turn(DIRECTION.RIGHT);
		assertEquals(270.0, vehicle.getAngle());

		vehicle.turn(DIRECTION.RIGHT);
		assertEquals(360.0, vehicle.getAngle());

		vehicle.turn(DIRECTION.RIGHT);
		assertEquals(90.0, vehicle.getAngle());

		vehicle.turn(DIRECTION.LEFT);
		assertEquals(0.0, vehicle.getAngle());

		vehicle.turn(DIRECTION.LEFT);
		assertEquals(270.0, vehicle.getAngle());

		vehicle.turn(DIRECTION.LEFT);
		assertEquals(180.0, vehicle.getAngle());

		vehicle.turn(DIRECTION.LEFT);
		assertEquals(90.0, vehicle.getAngle());

		vehicle.turn(DIRECTION.LEFT);
		assertEquals(0.0, vehicle.getAngle());
	}

	@Test
	public void testTurnException(){
		Vehicle vehicle = new Vehicle("sasaki");

		try{
			vehicle.turn(null);
			fail();
		}catch(Exception e){
			assertTrue(e instanceof IllegalArgumentException);
			assertEquals("the value of direction is null.", e.getMessage());
		}
	}
}
