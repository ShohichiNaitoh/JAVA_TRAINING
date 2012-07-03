package ch13.ex13_02;

public class StringCounter {

	public int countSpecifiedString(String originalStr , String targetStr){
		if(originalStr == null || originalStr.length() == 0){
			throw new IllegalArgumentException("parameter originalStr is invalid.");
		}
		if(targetStr == null || targetStr.length() == 0){
			throw new IllegalArgumentException("parameter targetStr is invalid.");
		}

		int count = 0;
		int index = 0;
		index = originalStr.indexOf(targetStr, index);
		while(index != -1){
			count++;
			index += targetStr.length();
			index = originalStr.indexOf(targetStr, index);
		}
		return count;
	}
}
