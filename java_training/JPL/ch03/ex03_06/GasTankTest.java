package ch03.ex03_06;

import java.lang.reflect.Field;
import org.junit.Test;
import junit.framework.TestCase;

public class GasTankTest extends TestCase {

	@Test
	public void testConstructor() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException{
		GasTank gasTank = new GasTank(4.0);

		Class<EnergySource> c = EnergySource.class;
		Field field = c.getDeclaredField("quantity");
		field.setAccessible(true);
		assertEquals(4.0, field.get(gasTank));
	}

	@Test
	public void testConstructorException(){
		try{
			GasTank gasTank = new GasTank(-4.0);
			fail();
		}catch(Exception e){
			assertTrue(e instanceof IllegalArgumentException);
			assertEquals("quantity is smaller than 0.", e.getMessage());
		}
	}

	@Test
	public void testEmpty(){
		GasTank gasTank1 = new GasTank(3.0);
		assertFalse(gasTank1.empty());

		GasTank gasTank2 = new GasTank(0.0);
		assertTrue(gasTank2.empty());
	}
}
