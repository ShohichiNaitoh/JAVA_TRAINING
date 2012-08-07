package ch14.ex14_03;

public class AddTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Calculator calc = new Calculator();

		new Thread(new AddThread(calc)).start();
		new Thread(new AddThread(calc)).start();
		new Thread(new AddThread(calc)).start();
		new Thread(new AddThread(calc)).start();
		new Thread(new AddThread(calc)).start();
	}

}
