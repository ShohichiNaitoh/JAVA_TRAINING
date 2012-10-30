package ch20.ex20_10;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.HashMap;
import java.util.Iterator;

public class WordCounter {
	private HashMap<String, Integer> countMap = new HashMap<String, Integer>();

	public void countWordInFile(String filePath) throws IOException{
		if(filePath == null){
			throw new IllegalArgumentException("arg filPath must not be null.");
		}

		StreamTokenizer in = new StreamTokenizer(new FileReader(new File(filePath)));
		while(in.nextToken() != StreamTokenizer.TT_EOF){
			if(in.ttype == StreamTokenizer.TT_WORD){
				if(countMap.containsKey(in.sval)){
					int count = countMap.get(in.sval);
					countMap.put(in.sval, ++count);
				}else{
					countMap.put(in.sval, 1);
				}
			}
		}

		for(Iterator<String> ite = countMap.keySet().iterator() ; ite.hasNext() ; ){
			String key = (String)ite.next();
			int value = (Integer)countMap.get(key);
			System.out.println(key + "  " + value);
		}

		countMap.clear();
	}
}
