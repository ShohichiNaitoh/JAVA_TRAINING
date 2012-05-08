package ch06.ex06_04;

import org.junit.Test;
import ch06.ex06_04.SignalEnum.Signal;
import junit.framework.TestCase;

public class SignalEnumTest extends TestCase {

	@Test
	public void testEnum(){
		assertSame(SignalEnum.blue , Signal.BLUE.getColor());
		assertSame(SignalEnum.yellow , Signal.YELLOW.getColor());
		assertSame(SignalEnum.red , Signal.RED.getColor());
	}
}
