package ch22.ex22_10;

import java.io.Reader;
import java.util.Scanner;

public class Delimiter {

	public static void delimite(Reader source){
		Scanner in = new Scanner(source);

		in.useDelimiter("#.*|\n");

		while(in.hasNext()) {
			System.out.println(in.next());
		}
	}
}
