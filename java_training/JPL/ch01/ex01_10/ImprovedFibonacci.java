package ch01.ex01_10;

public class ImprovedFibonacci {

	public static final int MAX_INDEX = 10;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FibonacciNumber[] fibonacciNumbers = new FibonacciNumber[MAX_INDEX];
		int lo = 1;
		int hi = 1;

		fibonacciNumbers[0] = new FibonacciNumber(lo);
		for(int i=0 ; i<MAX_INDEX-1 ; i++){
			fibonacciNumbers[i+1] = new FibonacciNumber(hi);
			hi = lo + hi;
			lo = hi - lo;
		}
		
		System.out.println("フィボナッチ数列");
		int i=0;
		for(FibonacciNumber num : fibonacciNumbers){
			System.out.println(i+1 + ": " + num);
			i++;
		}
	}

}
