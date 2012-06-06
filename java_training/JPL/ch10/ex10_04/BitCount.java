package ch10.ex10_04;

public class BitCount {

	public int countWithForLoop(int target){
		int count = 0;
		for(int i=0 ; i<Integer.SIZE ; i++){
			if( (target & 0x0001) != 0x0000 ){
				count++;
			}
			target = target >>> 1;
		}
		return count;
	}

	public int countWithWhileLoop(int target){
		int bitCount = 0;
		int loopCount = 0;
		while(loopCount < Integer.SIZE){
			if( (target & 0x0001) != 0x0000 ){
				bitCount++;
			}
			target = target >>> 1;
			loopCount++;
		}
		return bitCount;
	}

	public int countWithDoWhile(int target){
		int bitCount = 0;
		int loopCount = 0;
		do{
			if( (target & 0x0001) != 0x0000 ){
				bitCount++;
			}
			target = target >>> 1;
			loopCount++;
		}while(loopCount < Integer.SIZE);
		return bitCount;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BitCount bitCount = new BitCount();
		System.out.println( "for = " + bitCount.countWithForLoop(10) );
		System.out.println( "while = " + bitCount.countWithWhileLoop(10) );
		System.out.println( "do while = " + bitCount.countWithDoWhile(10) );
	}

}
