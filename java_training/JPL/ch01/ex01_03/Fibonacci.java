package ch01.ex01_03;

public class Fibonacci {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int lo = 1;
		int hi = 1;
		System.out.println("�t�B�{�i�b�`����");
		System.out.println(lo);
		while(hi < 50){
			System.out.println(hi);
			hi = lo + hi;
			lo = hi - lo;
		}
	}

}
