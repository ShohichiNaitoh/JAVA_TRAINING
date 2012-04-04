package ch02.ex02_13;
import java.lang.reflect.Field;

import org.junit.Test;

import junit.framework.TestCase;


public class VehicleTest extends TestCase{

	@Test
	public void testGetSpeed() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException{
		Vehicle vehicle = new Vehicle();

		Class<Vehicle> c = Vehicle.class;
		Field field = c.getDeclaredField("speed");
		field.setAccessible(true);
		field.set(vehicle, 50.0);

		assertEquals(50.0 , vehicle.getSpeed());
	}

	@Test
	public void testGetAngle() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException{
		Vehicle vehicle = new Vehicle();

		Class<Vehicle> c = Vehicle.class;
		Field field = c.getDeclaredField("angle");
		field.setAccessible(true);
		field.set(vehicle, 360.0);

		assertEquals(360.0 , vehicle.getAngle());
	}

	@Test
	public void testGetOwner() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException{
		Vehicle vehicle = new Vehicle();

		Class<Vehicle> c = Vehicle.class;
		Field field = c.getDeclaredField("owner");
		field.setAccessible(true);
		field.set(vehicle, "test");

		assertEquals("test" , vehicle.getOwner());
	}

	@Test
	public void testSetOwner() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException{
		Vehicle vehicle = new Vehicle();
		vehicle.setOwner("sasaki");

		Class<Vehicle> c = Vehicle.class;
		Field field = c.getDeclaredField("owner");
		field.setAccessible(true);

		assertEquals("sasaki" , field.get(vehicle));
	}
}
