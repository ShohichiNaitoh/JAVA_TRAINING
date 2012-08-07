package ch14.ex14_05;

public class CalcTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Thread(new CalcThread()).start();
		new Thread(new CalcThread()).start();
		new Thread(new CalcThread()).start();
		new Thread(new CalcThread()).start();
		new Thread(new CalcThread()).start();
	}

}
