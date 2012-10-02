package ch21.ex21_01;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import ch20.ex20_04.BufferedLineReader;

public class ComparedList {

	public List<String> convertFileToList(String filePath){
		if(filePath == null){
			throw new IllegalArgumentException("arg filepath must not be null.");
		}

		List<String> list = new ArrayList<String>();
		try {
			InputStreamReader isr = new InputStreamReader(new FileInputStream(new File(filePath)));
			BufferedLineReader blr = new BufferedLineReader(isr);
			String s;
			while((s = blr.readLine()) != null){
				addSortedList(s , list);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	private void addSortedList(String str , List<String> list){
		int size = list.size();
		if(size == 0){
			list.add(str);
			return;
		}
		for(int i=0 ; i<size ; i++){
			if(str.compareTo(list.get(i)) <= 0){
				list.add(i, str);
				return;
			}
		}
		list.add(str);
	}
}
