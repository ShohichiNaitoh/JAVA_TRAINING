package ch20.ex20_11;

import java.io.File;
import java.io.FilenameFilter;

public class FileSuffixFilter implements FilenameFilter {
	private String suffix = null;

	public FileSuffixFilter(String suffix){
		this.suffix = suffix;
	}

	@Override
	public boolean accept(File dir, String name) {
		return name.endsWith(suffix);
	}

	public static void main(String[] args){
		File dir = new File("./JPL/ch20/ex20_11");
		String[] files = dir.list(new FileSuffixFilter("txt"));
		for(String file : files){
			System.out.println(file);
		}
	}
}
