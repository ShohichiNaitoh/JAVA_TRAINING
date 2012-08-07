package ch14.ex14_07;

/*
 * doYieldをtrueとした場合、10/10で以下の結果となった。
 *
 *  Did
 *  DidNot
 *  Did
 *  DidNot
 *
 */

public class Babble extends Thread {
	static boolean doYield;
	static int howOften;
	private String word;

	Babble(String whatToSay){
		word = whatToSay;
	}

	public void run(){
		for(int i=0 ; i<howOften ; i++){
			System.out.println(word);
			if(doYield){
				Thread.yield();
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		doYield = new Boolean(args[0]).booleanValue();
		howOften = Integer.parseInt(args[1]);

		for(int i=2 ; i<args.length ; i++){
			new Babble(args[i]).start();
		}
	}

}
