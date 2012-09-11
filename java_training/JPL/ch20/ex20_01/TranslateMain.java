package ch20.ex20_01;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TranslateMain {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new FileInputStream(new File("./JPL/ch20/ex20_01/in.txt"));
			out = new FileOutputStream(new File("./JPL/ch20/ex20_01/out.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		TranslateByte tb = new TranslateByte();
		try {
			tb.translate(in, out, 'A', 'a');
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			in.close();
			out.close();
		}
	}

}
