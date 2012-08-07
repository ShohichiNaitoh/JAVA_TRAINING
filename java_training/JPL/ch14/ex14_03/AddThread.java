package ch14.ex14_03;

import java.util.Random;

public class AddThread implements Runnable {
	private Calculator calc = null;

	public AddThread(Calculator calc){
		if(calc == null){
			throw new IllegalArgumentException("calc must not be null.");
		}
		this.calc = calc;
	}

	@Override
	public void run() {
		Random rand = new Random();
		for(int i=0 ; i<100 ; i++){
			calc.addValue(rand.nextInt(10));
		}
	}

}
