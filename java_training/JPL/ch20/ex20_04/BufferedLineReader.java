package ch20.ex20_04;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;

public class BufferedLineReader extends FilterReader {

	protected BufferedLineReader(Reader in) {
		super(in);
	}

	public int read() throws IOException{
		return super.read();
	}

	public String readLine() throws IOException{
		StringBuffer sb = new StringBuffer();
		int c;
		while((c = super.read()) != -1){
			if(c == '\n' || c == '\r'){
				return sb.toString();
			}else{
				sb.append(c);
			}
		}
		return null;
	}
}
