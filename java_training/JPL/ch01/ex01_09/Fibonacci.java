package ch01.ex01_09;

public class Fibonacci {

	public static final int MAX_INDEX = 10;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] numbers = new int[MAX_INDEX];
		int lo = 1;
		int hi = 1;
		
		numbers[0] = lo;
		for(int i=0 ; i<MAX_INDEX-1 ; i++){
			numbers[i+1] = hi;
			hi = lo + hi;
			lo = hi - lo;
		}
		
		System.out.println("フィボナッチ数列");
		for(int number : numbers){
			System.out.println(number);
		}
	}

}
