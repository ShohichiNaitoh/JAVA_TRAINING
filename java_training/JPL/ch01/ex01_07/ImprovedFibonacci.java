package ch01.ex01_07;

public class ImprovedFibonacci {

	static final int MAX_INDEX = 9;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int lo = 1;
		int hi = 1;
		String mark;
		
		System.out.println(MAX_INDEX + ": " + lo);
		for(int i=MAX_INDEX-1 ; i>0 ; i--){
			if(hi % 2 == 0){
				mark = " *";
			}else{
				mark = "";
			}
			
			System.out.println(i + ": " + hi + mark);
			hi = lo + hi;
			lo = hi - lo;
		}
	}
}
