package ch20.ex20_03;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class EncryptOutputStream extends FilterOutputStream {

	public static final int ADD_CONSTANT = 2;

	public EncryptOutputStream(OutputStream out){
		super(out);
	}

	@Override
	public void write(int b) throws IOException {
		super.write(decrypt(b));
	}

	public void write(byte[] buf , int offset , int count){

	}

	private int decrypt(int c){
		return c - ADD_CONSTANT;
	}
}
