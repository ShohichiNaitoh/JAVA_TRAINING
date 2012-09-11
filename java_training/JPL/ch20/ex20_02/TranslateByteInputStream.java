package ch20.ex20_02;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class TranslateByteInputStream extends FilterInputStream {
	private byte from;
	private byte to;

	public TranslateByteInputStream(InputStream in , char from , char to) {
		super(in);
		this.from = (byte) from;
		this.to = (byte) to;
	}

	public int read() throws IOException{
		int c = super.read();
		return (c == -1 ? c : tlanslate(c));
	}

	public int read(byte[] buf) throws IOException{
		return this.read(buf , 0 , buf.length);
	}

	public int read(byte[] buf , int offset , int count) throws IOException{
		int nread = super.read(buf , offset , count);
		int last = offset + nread;
		for(int i=offset ; i<last ; i++){
			buf[i] = (byte) tlanslate(buf[i]);
		}
		return nread;
	}

	private int tlanslate(int c){
		return (c == from ? to : c);
	}

}
