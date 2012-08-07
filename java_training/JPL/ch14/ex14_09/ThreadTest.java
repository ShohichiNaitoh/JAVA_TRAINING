package ch14.ex14_09;

public class ThreadTest {

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {

		ThreadGroup threadGroup1 = new ThreadGroup("ThreadGroup1");
		ThreadGroup threadGroup2 = new ThreadGroup(threadGroup1 , "ThreadGroup2");

		SampleThread thread1 = new SampleThread(threadGroup1 , "Thread1");
		SampleThread thread2 = new SampleThread(threadGroup1 , "Thread2");
		SampleThread thread3 = new SampleThread(threadGroup1 , "Thread3");
		SampleThread thread4 = new SampleThread(threadGroup2 , "Thread4");
		SampleThread thread5 = new SampleThread(threadGroup2 , "Thread5");
		SampleThread thread6 = new SampleThread(threadGroup2 , "Thread6");

		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
		thread5.start();
		thread6.start();

		ThreadGroupDisplay threadGroupDisplay = new ThreadGroupDisplay();
		threadGroupDisplay.displayThreadLayerPeriodic(threadGroup2);
	}
}
