package ch20.ex20_03;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class DecryptInputStream extends FilterInputStream {

	public static final int SUBTRACT_CONSTANT = 2;

	public DecryptInputStream(InputStream in){
		super(in);
	}

	@Override
	public int read() throws IOException {
		int c = super.read();
		return (c == -1 ? c : decrypt(c));
	}

	public int read(byte[] buf , int offset , int count) throws IOException{
		int nread = super.read(buf , offset , count);
		int last = offset + nread;
		for(int i=offset ; i<last ; i++){
			buf[i] = (byte) decrypt(buf[i]);
		}
		return nread;
	}

	private int decrypt(int c){
		return c - SUBTRACT_CONSTANT;
	}


}
