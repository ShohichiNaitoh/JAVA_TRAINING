package ch13.ex13_03;

import java.util.ArrayList;

public class Delimiter {

	public static ArrayList<String> delimitedString(String from , char start , char end){
		if(from == null){
			throw new IllegalArgumentException("parameter from is invalid.");
		}

		ArrayList<String> strList = new ArrayList<String>();
		int startIndex = 0;
		int endIndex = 0;

		startIndex = from.indexOf(start , startIndex);
		while(startIndex != -1){
			endIndex = from.indexOf(end , startIndex + 1);
			if(endIndex != -1){
				strList.add(from.substring(startIndex, endIndex + 1));
			}else{
				break;
			}
			startIndex = from.indexOf(start , endIndex + 1);
		}

		return strList;
	}
}
