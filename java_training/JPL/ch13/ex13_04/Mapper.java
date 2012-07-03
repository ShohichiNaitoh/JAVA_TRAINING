package ch13.ex13_04;

import java.util.ArrayList;

public class Mapper {

	public static ArrayList<Object> convertToObjectFromString(String fullTxt){
		if(fullTxt == null){
			throw new IllegalArgumentException("parameter fullTxt must not be null.");
		}

		ArrayList<Object> objList = new ArrayList<Object>();
		String[] lines = fullTxt.split("\n");

		for(String line : lines){
			String[] str = line.split(" ");
			if(str.length != 2){
				throw new IllegalArgumentException("\"type value\" format is invalid.");
			}

			String type = str[0];
			String value = str[1];

			if(type.equals("Integer")){
				objList.add(Integer.parseInt(value));
			}else if(type.equals("Short")){
				objList.add(Short.parseShort(value));
			}else if(type.equals("Long")){
				objList.add(Long.parseLong(value));
			}else if(type.equals("Byte")){
				objList.add(Byte.parseByte(value));
			}else if(type.equals("Float")){
				objList.add(Float.parseFloat(value));
			}else if(type.equals("Double")){
				objList.add(Double.parseDouble(value));
			}else if(type.equals("Boolean")){
				objList.add(Boolean.parseBoolean(value));
			}else if(type.equals("Character")){
				if(value.length() != 1){
					throw new IllegalArgumentException("type Character's value is invalid.");
				}
				objList.add(value.charAt(0));
			}else{
				throw new IllegalArgumentException("specified type is unsupported.");
			}
		}

		return objList;
	}

}
