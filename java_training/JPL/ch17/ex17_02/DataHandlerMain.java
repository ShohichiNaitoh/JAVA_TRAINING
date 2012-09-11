package ch17.ex17_02;

import java.io.File;
import java.io.IOException;

public class DataHandlerMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DataHandler dh = new DataHandler();
		byte[] data = null;
		try {
			data = dh.readFile(new File("./JPL/ch17/ex17_02/data.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println(new String(data));
	}

}
