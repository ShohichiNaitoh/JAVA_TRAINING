package ch14.ex14_09;

class SampleThread extends Thread{

	public SampleThread(ThreadGroup group , String name){
		super(group , name);
	}

	@Override
	public void run(){
		try {
			sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}