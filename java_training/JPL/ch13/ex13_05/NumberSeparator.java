package ch13.ex13_05;

public class NumberSeparator {

	public static String separateThreeDigits(String number){
		if(number == null){
			throw new IllegalArgumentException("parameter number is invalid.");
		}

		StringBuilder separatedNumber = new StringBuilder();
		int digit = 0;

		for(int i=number.length()-1 ; 0<=i ; i--){
			char ch = number.charAt(i);
			if((('\u0030' <= ch) && (ch <= '\u0039')) || (('\uFF10' <= ch) && (ch <= '\uFF19'))){
				digit++;
				separatedNumber.insert(0, ch);
				if((digit % 3 == 0) && i != 0){
						separatedNumber.insert(0, ',');
				}
			}
		}

		return separatedNumber.toString();
	}
}
