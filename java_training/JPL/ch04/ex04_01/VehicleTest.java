package ch04.ex04_01;

import java.lang.reflect.Field;

import org.junit.Test;
import junit.framework.TestCase;

public class VehicleTest extends TestCase {

	@Test
	public void testConstructor() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException{
		EnergySource expected = new GasTank(3.0);
		Vehicle vehicle = new Vehicle( expected );

		Class<Vehicle> c = Vehicle.class;
		Field field = c.getDeclaredField("energySource");
		field.setAccessible(true);
		assertSame(expected, field.get(vehicle));
	}

	@Test
	public void testCounstructorException(){
		try{
			Vehicle vehicle = new Vehicle(null);
			fail();
		}catch(Exception e){
			assertTrue(e instanceof IllegalArgumentException);
			assertEquals("EnergySource is null." , e.getMessage());
		}
	}

	@Test
	public void testStart(){
		Vehicle vehicle1 = new Vehicle(new GasTank(2.0));
		assertTrue(vehicle1.start());

		Vehicle vehicle2 = new Vehicle(new Battery(0.0));
		assertFalse(vehicle2.start());
	}

}
