package ch20.ex20_07;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataOutputMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Attr attrOut = new Attr("test", 3.0);
		try {
			attrOut.write(new DataOutputStream(new FileOutputStream(new File("./JPL/ch20/ex20_07/data.txt"))));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			Attr attrIn = new Attr(new DataInputStream(new FileInputStream(new File("./JPL/ch20/ex20_07/data.txt"))));
			System.out.println(attrIn);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
