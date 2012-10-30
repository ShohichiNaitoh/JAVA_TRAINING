package ch24.ex24_03;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

public class DateParser {

	public static void main(String[] args) throws ParseException{
		Date date = DateFormat.getDateInstance(DateFormat.MEDIUM).parse("2012/2/29");
		System.out.println(date);

		date = DateFormat.getDateInstance(DateFormat.MEDIUM).parse("2/31");	//Exception
		System.out.println(date);
	}
}
