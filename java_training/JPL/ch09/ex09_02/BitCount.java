package ch09.ex09_02;

public class BitCount {

	public int count(int target){
		int count = 0;
		for(int i=0 ; i<Integer.SIZE ; i++){
			if( (target & 0x0001) != 0x0000 ){
				count++;
			}
			target = target >>> 1;
		}
		return count;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BitCount bitCount = new BitCount();
		System.out.println( "count = " + bitCount.count(10) );
	}

}
