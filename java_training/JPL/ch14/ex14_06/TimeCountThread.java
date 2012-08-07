package ch14.ex14_06;

public class TimeCountThread implements Runnable {
	private MessageThread messageThread = null;
	private PassedTime passedTime = null;
	private long lastTime = 0;

	public TimeCountThread(int messageInterval){
		passedTime = new PassedTime();
		new Thread(this).start();
		messageThread = new MessageThread(passedTime , messageInterval);
		new Thread(messageThread).start();
	}

	@Override
	public void run() {
		while(passedTime.getPassedTime() <= 60000){
			showTime();
		}
	}

	private synchronized void showTime(){
		while(passedTime.getPassedTime() - lastTime >= 1000){
			System.out.println( (int)(passedTime.getPassedTime() / 1000) + " senconds passed.");
			lastTime = passedTime.getPassedTime();
			messageThread.notifyMessageThread();
		}
	}

}
