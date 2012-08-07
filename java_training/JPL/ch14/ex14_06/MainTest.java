package ch14.ex14_06;

public class MainTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Thread(new TimeCountThread(15)).start();
		//new Thread(new TimeCountThread(7)).start();
	}

}
