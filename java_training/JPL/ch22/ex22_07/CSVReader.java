package ch22.ex22_07;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class CSVReader {

	public static List<String[]> readCSVTable(Readable source , int cellNumber) throws IOException{
		Scanner in = new Scanner(source);
		List<String[]> vals = new ArrayList<String[]>();
		StringBuffer exp = new StringBuffer("^");
		for(int i=0 ; i<cellNumber ; i++){
			exp.append("(.*),");
		}
		exp.deleteCharAt(exp.length()-1);
		Pattern pat = Pattern.compile(exp.toString() , Pattern.MULTILINE);
		while(in.hasNextLine()){
			String line = in.findInLine(pat);
			if(line != null){
				String[] cells = new String[cellNumber];
				MatchResult match = in.match();
				for(int i=0 ; i<cellNumber ; i++){
					cells[i] = match.group(i+1);
				}
				vals.add(cells);
				in.nextLine();
			}else{
				throw new IOException("input format error");
			}
		}
		IOException ex = in.ioException();
		if(ex != null){
			throw ex;
		}
		return vals;
	}
}
