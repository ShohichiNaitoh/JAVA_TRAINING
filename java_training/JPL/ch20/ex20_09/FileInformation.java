package ch20.ex20_09;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

public class FileInformation {

	public static void showFileInformation(String... filePaths) throws IOException{
		if(filePaths == null){
			throw new IllegalArgumentException("arg filePaths must not be null.");
		}

		for(int i=0 ; i<filePaths.length ; i++){
			File file = new File(filePaths[i]);
			System.out.println("************************************");
			System.out.println("File name is " + file.getName());
			if(file.isAbsolute()){
				System.out.println("File path is " + file.getAbsolutePath());
			}
			System.out.println("File exists : " + file.exists());
			if(!file.exists()){
				continue;
			}
			System.out.println("can read : " + file.canRead());
			System.out.println("can write : " + file.canWrite());
			System.out.println("isFile  : " + file.isFile());
			System.out.println("isDirectory : " + file.isDirectory());
			System.out.println("isHidden : " + file.isHidden());
			System.out.println("lastModifed : " + new Date(file.lastModified()));
			System.out.println("length : " + file.length());
		}
	}

	public static void main(String[] args){
		try {
			FileInformation.showFileInformation("./JPL/ch20/ex20_09/test1.txt" , "./JPL/ch20/ex20_09/test2.txt" , "./JPL/ch20/ex20_09/test3.txt");
		} catch (IOException e) {
		}
	}
}
