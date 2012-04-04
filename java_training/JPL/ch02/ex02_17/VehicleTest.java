package ch02.ex02_17;
import org.junit.Test;

import junit.framework.TestCase;


public class VehicleTest extends TestCase {

	@Test
	public void testTurn(){
		Vehicle vehicle = new Vehicle("yamada");
		vehicle.turn(90.0);
		assertEquals(90.0, vehicle.getAngle());

		vehicle.turn(185.5);
		assertEquals(275.5, vehicle.getAngle());

		vehicle.turn(100.0);
		assertEquals(15.5, vehicle.getAngle());

		vehicle.turn(1100);
		assertEquals(35.5, vehicle.getAngle());

		vehicle.turn(-35.5);
		assertEquals(0.0, vehicle.getAngle());

		vehicle.turn(-90.0);
		assertEquals(270.0, vehicle.getAngle());

		vehicle.turn(-20.0);
		assertEquals(250.0, vehicle.getAngle());

		vehicle.turn(-1100.0);
		assertEquals(230.0, vehicle.getAngle());
	}

	@Test
	public void testTurn2(){
		Vehicle vehicle = new Vehicle("sasaki");
		vehicle.turn(Vehicle.TURN_RIGHT);
		assertEquals(90.0, vehicle.getAngle());

		vehicle.turn(Vehicle.TURN_RIGHT);
		assertEquals(180.0, vehicle.getAngle());

		vehicle.turn(Vehicle.TURN_RIGHT);
		assertEquals(270.0, vehicle.getAngle());

		vehicle.turn(Vehicle.TURN_RIGHT);
		assertEquals(360.0, vehicle.getAngle());

		vehicle.turn(Vehicle.TURN_RIGHT);
		assertEquals(90.0, vehicle.getAngle());

		vehicle.turn(Vehicle.TURN_LEFT);
		assertEquals(0.0, vehicle.getAngle());

		vehicle.turn(Vehicle.TURN_LEFT);
		assertEquals(270.0, vehicle.getAngle());

		vehicle.turn(Vehicle.TURN_LEFT);
		assertEquals(180.0, vehicle.getAngle());

		vehicle.turn(Vehicle.TURN_LEFT);
		assertEquals(90.0, vehicle.getAngle());

		vehicle.turn(Vehicle.TURN_LEFT);
		assertEquals(0.0, vehicle.getAngle());
	}

	@Test
	public void testTurnException(){
		Vehicle vehicle = new Vehicle("sasaki");
		try{
			vehicle.turn("test");
			fail();
		}catch(Exception e){
			assertTrue(e instanceof IllegalArgumentException);
		}
	}
}
