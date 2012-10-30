package ch21.ex21_02;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.WeakHashMap;

public class DataHandler {

	private WeakHashMap<File, byte[]> dataMap = new WeakHashMap<File, byte[]>();

	byte[] readFile(File file) throws IOException{
		if(file == null){
			throw new IllegalArgumentException("args file must not be null.");
		}

		byte[] data = null;
		if(dataMap.containsKey(file)){
			data = dataMap.get(file);
			if(data != null){
				return data;
			}
		}

		try {
			data = readBytesFromFile(file);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}

		dataMap.put(file, data);
		return data;
	}

	private byte[] readBytesFromFile(File file) throws IOException{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			FileInputStream in = new FileInputStream(file);
			int c;
			try {
				while((c = in.read()) != -1){
					out.write(c);
				}
			} finally {
				in.close();
				out.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return out.toByteArray();
	}
}
