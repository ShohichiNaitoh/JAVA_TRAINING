package ch14.ex14_04;

import java.util.Random;

public class AddThread implements Runnable {

	@Override
	public void run() {
		Random rand = new Random();
		for(int i=0 ; i<100 ; i++){
			Calculator.addValue(rand.nextInt(10));
		}
	}

}
