package ch13.ex13_06;

public class NumberSeparator {

	public static String separateSpecifiedDigits(String number , char separator , int intervalDigit){
		if(number == null){
			throw new IllegalArgumentException("parameter number is invalid.");
		}
		if(intervalDigit <= 0){
			throw new IllegalArgumentException("parameter intervalDigit is invalid.");
		}

		StringBuilder separatedNumber = new StringBuilder();
		int digit = 0;

		for(int i=number.length()-1 ; 0<=i ; i--){
			char ch = number.charAt(i);
			if((('\u0030' <= ch) && (ch <= '\u0039')) || (('\uFF10' <= ch) && (ch <= '\uFF19'))){
				digit++;
				separatedNumber.insert(0, ch);
				if((digit % intervalDigit == 0) && i != 0){
						separatedNumber.insert(0, separator);
				}
			}
		}

		return separatedNumber.toString();
	}
}
