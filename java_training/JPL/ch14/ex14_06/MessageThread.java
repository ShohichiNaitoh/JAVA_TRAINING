package ch14.ex14_06;

public class MessageThread implements Runnable {
	private PassedTime passedTime = null;
	private int interval = 0;

	public MessageThread(PassedTime passedTime , int interval){
		if(passedTime == null){
			new IllegalArgumentException("passedTime must not be null.");
		}
		if(interval <= 0){
			new IllegalArgumentException("interval must be larger than 0.");
		}
		this.passedTime = passedTime;
		this.interval = interval;
	}

	@Override
	public void run() {
		while(true){
			try {
				showMessage();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public synchronized void notifyMessageThread(){
		notifyAll();
	}

	private synchronized void showMessage() throws InterruptedException{
		while( (passedTime.getPassedTime() <= 1000) || ((passedTime.getPassedTime() / 1000) % interval) != 0 ){
			wait();
		}
		System.out.println("***** interval " + interval + " seconds Message *****");
		wait();
	}

}
