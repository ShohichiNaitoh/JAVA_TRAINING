package ch02.ex02_15;
import java.lang.reflect.Field;

import org.junit.Test;

import junit.framework.TestCase;


public class VehicleTest extends TestCase {

	@Test
	public void testChangeSpeed() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException{
		Vehicle vehicle = new Vehicle("yamadda");
		vehicle.changeSpeed(50.5);

		Class<Vehicle> c = Vehicle.class;
		Field field = c.getDeclaredField("speed");
		field.setAccessible(true);
		assertEquals(50.5 , field.get(vehicle));
	}

	@Test
	public void testChangeSpeedException() {
		Vehicle vehicle = new Vehicle("satoh");
		try{
			vehicle.changeSpeed(-30.0);
			fail();
		}catch(Exception e){
			assertTrue(e instanceof IllegalArgumentException);
		}
	}

	@Test
	public void testStop() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException{
		Vehicle vehicle = new Vehicle("sasaki");
		vehicle.stop();

		Class<Vehicle> c = Vehicle.class;
		Field field = c.getDeclaredField("speed");
		field.setAccessible(true);
		assertEquals(0.0 , field.get(vehicle));
	}
}
