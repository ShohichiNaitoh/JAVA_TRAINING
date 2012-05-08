package ch03.ex03_01;

import java.lang.reflect.Field;

import org.junit.Test;

import ch02.ex02_15.Vehicle;
import junit.framework.TestCase;

public class PassengerVehicleTest extends TestCase {

	@Test
	public void testSetNumberOfSeat() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException{
		PassengerVehicle vehicle = new PassengerVehicle("Yamada");
		vehicle.setNumberOfSeat(5);

		Class<PassengerVehicle> c = PassengerVehicle.class;
		Field field = c.getDeclaredField("numberOfSeat");
		field.setAccessible(true);
		assertEquals(5 , field.get(vehicle));
	}

	@Test
	public void testSetNumberOfSeatException(){
		PassengerVehicle vehicle = new PassengerVehicle("Yamada");
		try{
			vehicle.setNumberOfSeat(-5);
			fail();
		}catch(Exception e){
			assertTrue(e instanceof IllegalArgumentException);
			assertEquals(e.getMessage(), "numberOfSeat is smaller than 0.");
		}
	}

	@Test
	public void testSetNumberOfSittingPerson() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException{
		PassengerVehicle vehicle = new PassengerVehicle("Yamada" , 10);
		vehicle.setNumberOfSittingPerson(7);

		Class<PassengerVehicle> c = PassengerVehicle.class;
		Field field = c.getDeclaredField("numberOfSittingPerson");
		field.setAccessible(true);
		assertEquals(7 , field.get(vehicle));
	}

	@Test
	public void testSetNumberOfSittingPersonException1(){
		PassengerVehicle vehicle = new PassengerVehicle("Yamada" , 10);

		try{
			vehicle.setNumberOfSittingPerson(-5);
		}catch(Exception e){
			assertTrue(e instanceof IllegalArgumentException);
			assertEquals(e.getMessage(), "numberOfSittingPerson is smaller than 0.");
		}
	}

	@Test
	public void testSetNumberOfSittingPersonException2(){
		PassengerVehicle vehicle = new PassengerVehicle("Yamada" , 10);

		try{
			vehicle.setNumberOfSittingPerson(25);
		}catch(Exception e){
			assertTrue(e instanceof IllegalArgumentException);
			assertEquals(e.getMessage(), "numberOfSittingPerson is over nummberOfSeat.");
		}
	}

}
