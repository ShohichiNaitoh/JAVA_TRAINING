package ch20.ex20_05;

import java.io.IOException;

public class SearchMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FileSearchReader reader = new FileSearchReader();
		try {
			reader.showLineContainsTargetWord("./JPL/ch20/ex20_05/test.txt", "test");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
