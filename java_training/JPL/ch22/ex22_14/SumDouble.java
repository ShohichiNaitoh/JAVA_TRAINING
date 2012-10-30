package ch22.ex22_14;

import java.util.StringTokenizer;

public class SumDouble {

	public static double sumDoubleNumbers(String str){
		StringTokenizer st = new StringTokenizer(str, " 　");
		double sum = 0.0;
		while(st.hasMoreTokens()){
			sum += Double.parseDouble(st.nextToken());
		}
		return sum;
	}

}
