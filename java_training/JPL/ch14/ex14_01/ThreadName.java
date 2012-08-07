package ch14.ex14_01;

public class ThreadName {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Thread currentThread = Thread.currentThread();
		System.out.println("main thread's name : " + currentThread.getName());
	}

}
