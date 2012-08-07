package ch14.ex14_04;

public class AddTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Thread(new AddThread()).start();
		new Thread(new AddThread()).start();
		new Thread(new AddThread()).start();
		new Thread(new AddThread()).start();
		new Thread(new AddThread()).start();
	}

}
