package ch17.ex17_02;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.ref.WeakReference;

public class DataHandler {
	private WeakReference<File> lastFile;
	private WeakReference<byte[]> lastData;

	byte[] readFile(File file) throws IOException{
		File savedFile = null;
		byte[] data;

		if(lastFile != null){
			savedFile = lastFile.get();
			if(savedFile != null){
				if(file.equals(savedFile)){
					data = lastData.get();
					if(data != null){
						return data;
					}
				}
			}
		}

		try {
			data = readBytesFromFile(file);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}

		lastFile = new WeakReference<File>(file);
		lastData = new WeakReference<byte[]>(data);
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
