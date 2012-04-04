package ch01.ex01_06;
public class Fibonacci {

	static final String TITLE = "フィボナッチ数列";
	static final int MAX = 50;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int lo = 1;
		int hi = 1;
		System.out.println(TITLE);
		System.out.println(lo);
		while(hi < MAX){
			System.out.println(hi);
			hi = lo + hi;
			lo = hi - lo;
		}
	}
}

