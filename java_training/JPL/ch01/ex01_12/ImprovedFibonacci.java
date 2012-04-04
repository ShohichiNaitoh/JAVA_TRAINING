package ch01.ex01_12;

public class ImprovedFibonacci {

	static final int MAX_INDEX = 10;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String text;
		int lo = 1;
		int hi = 1;
		
		text = "1: " + lo;
		System.out.println(text);
		for(int i=2 ; i<MAX_INDEX ; i++){
			text = i + ": " + hi;
			if(hi % 2 == 0){
				text += " *";
			}
			
			System.out.println(text);
			hi = lo + hi;
			lo = hi -lo;
		}
	}

}
