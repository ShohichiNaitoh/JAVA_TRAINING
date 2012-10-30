package ch24.ex24_02;

import java.util.Currency;
import java.util.Locale;

public class GlobalCurrency {

	public static void main(String[] args){
		System.out.println("JAPAN   : " + Currency.getInstance(Locale.JAPAN).getSymbol());
		System.out.println("US      : " + Currency.getInstance(Locale.US).getSymbol());
		System.out.println("UK      : " + Currency.getInstance(Locale.UK).getSymbol());
		System.out.println("FRANCE  : " + Currency.getInstance(Locale.FRANCE).getSymbol());
		System.out.println("CHINA   : " + Currency.getInstance(Locale.CHINA).getSymbol());
		System.out.println("GERMANY : " + Currency.getInstance(Locale.GERMANY).getSymbol());
	}
}
