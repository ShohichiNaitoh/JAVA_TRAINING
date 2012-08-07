package ch14.ex14_06;

public class PassedTime {
	private long startTime = 0;

	public PassedTime(){
		startTime = System.currentTimeMillis();
	}

	public synchronized long getPassedTime(){
		return System.currentTimeMillis() - startTime;
	}
}
