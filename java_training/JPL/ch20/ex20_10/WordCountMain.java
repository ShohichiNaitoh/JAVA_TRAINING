package ch20.ex20_10;

import java.io.IOException;

public class WordCountMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		WordCounter wordCounter = new WordCounter();
		try {
			wordCounter.countWordInFile("./JPL/ch20/ex20_10/test.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
