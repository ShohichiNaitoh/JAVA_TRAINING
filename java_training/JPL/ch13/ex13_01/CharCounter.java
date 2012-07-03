package ch13.ex13_01;

public class CharCounter {

	public int countSpecifiedCharcter(String originalStr , char targetChar){
		if(originalStr == null ){
			throw new IllegalArgumentException("originalStr must not be null.");
		}

		int count = 0;
		for(int i=0 ; i<originalStr.length() ; i++){
			if(originalStr.charAt(i) == targetChar){
				count++;
			}
		}
		return count;
	}
}
