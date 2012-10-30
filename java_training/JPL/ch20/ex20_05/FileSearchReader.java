package ch20.ex20_05;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

public class FileSearchReader {

	public void showLineContainsTargetWord(String filePath , String targetWord) throws IOException{
		if(filePath == null){
			throw new IllegalArgumentException("filePath must not be null.");
		}

		FileReader fileIn = new FileReader(filePath);
		LineNumberReader in = new LineNumberReader(fileIn);
		String line = in.readLine();
		while(line != null){
			if(line.contains(targetWord)){
				System.out.println(in.getLineNumber() + " " + line);
			}
			line = in.readLine();
		}
	}
}
