package ch14.ex14_05;

import java.util.Random;

public class CalcThread implements Runnable {

	@Override
	public void run() {
		Random rand = new Random();
		for(int i=0 ; i<100 ; i++){
			if(i % 2 == 0){
				Calculator.addValue(rand.nextInt(10));
			}else{
				Calculator.subtractValue(rand.nextInt(10));
			}
		}
	}

}
