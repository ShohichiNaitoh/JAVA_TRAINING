package ch03.ex03_09;

import java.lang.reflect.Field;

import org.junit.Test;
import junit.framework.TestCase;

public class GarageTest extends TestCase {

	@Test
	public void testConstructor() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException{
		Garage garage = new Garage(10);

		Class<Garage> c = Garage.class;
		Field field = c.getDeclaredField("capacity");
		field.setAccessible(true);
		assertEquals(10, field.get(garage));

		assertEquals(10, garage.getVehicles().length);
	}

	@Test
	public void testConstructorException(){
		try{
			Garage garage = new Garage(-5);
			fail();
		}catch(Exception e){
			assertTrue(e instanceof IllegalArgumentException);
			assertEquals("capacity is smaller than 0." , e.getMessage());
		}
	}

	@Test
	public void testPark(){
		Garage garage = new Garage(10);
		Vehicle vehicle = new Vehicle("Satoh");

		assertTrue(garage.park(9, vehicle));
		assertSame(vehicle, garage.getVehicle(9));
	}

	@Test
	public void testParkException1(){
		Garage garage = new Garage(10);

		Vehicle vehicle = new Vehicle("Satoh");
		try{
			garage.park(10, vehicle);
			fail();
		}catch(Exception e){
			assertTrue(e instanceof IllegalArgumentException);
			assertEquals("number is out of range." , e.getMessage());
		}
	}

	@Test
	public void testParkException2(){
		Garage garage = new Garage(10);

		try{
			garage.park(5, null);
			fail();
		}catch(Exception e){
			assertTrue(e instanceof IllegalArgumentException);
			assertEquals("vehicle is null." , e.getMessage());
		}
	}

	@Test
	public void testDepature(){
		Garage garage = new Garage(10);
		Vehicle vehicle = new Vehicle("Satoh");

		assertFalse(garage.departure(5));

		garage.park(5, vehicle);
		assertTrue(garage.departure(5));
		assertEquals(null, garage.getVehicle(5));
	}

	@Test
	public void testDepatureException(){
		Garage garage = new Garage(10);
		try{
			assertFalse(garage.departure(12));
		}catch(Exception e){
			assertTrue(e instanceof IllegalArgumentException);
			assertEquals("number is out of range." , e.getMessage());
		}
	}

	@Test
	public void testClone(){
		Garage garage = new Garage(10);

		Vehicle vehicle1 = new Vehicle("Satoh");
		vehicle1.changeSpeed(20.0);
		vehicle1.turn(20);

		Vehicle vehicle2 = new Vehicle("Tanaka");
		vehicle2.changeSpeed(40.0);
		vehicle2.turn(40);

		Vehicle vehicle3 = new Vehicle("Sasaki");
		vehicle3.changeSpeed(60.0);
		vehicle3.turn(60);

		garage.park(0, vehicle1);
		garage.park(5, vehicle2);
		garage.park(9, vehicle3);

		Garage cloneObj = garage.clone();
		assertEquals(cloneObj.toString(), garage.toString());
	}

	@Test
	public void testClone2(){
		Garage garage = new Garage(10);

		Vehicle vehicle1 = new Vehicle("Satoh");
		vehicle1.changeSpeed(20.0);
		vehicle1.turn(20);

		Vehicle vehicle2 = new Vehicle("Tanaka");
		vehicle2.changeSpeed(40.0);
		vehicle2.turn(40);

		Vehicle vehicle3 = new Vehicle("Sasaki");
		vehicle3.changeSpeed(60.0);
		vehicle3.turn(60);

		garage.park(0, vehicle1);
		garage.park(5, vehicle2);
		garage.park(9, vehicle3);

		Garage cloneObj = garage.clone();
		cloneObj.departure(5);
		Vehicle vehicle = cloneObj.getVehicle(9);
		vehicle.setOwner("Yamada");
		vehicle.changeSpeed(120);
		vehicle.turn(120);

		assertFalse(cloneObj.toString().equals(garage.toString()));
	}
}
