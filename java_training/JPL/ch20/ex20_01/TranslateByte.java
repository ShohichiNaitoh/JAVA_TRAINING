package ch20.ex20_01;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TranslateByte {

	public OutputStream translate(InputStream in , OutputStream out , char from , char to) throws IOException{
		if(in == null || out == null){
			new IllegalArgumentException("args must not be null.");
		}

		int b;
		while((b = in.read()) != -1){
			out.write((b == from) ? to : b);
		}
		return out;
	}

}
