package ch16.ex16_09.Interpret.interpret.logic;



import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;

import ch16.ex16_09.Interpret.interpret.dispatcher.RequestDispatcher;
import ch16.ex16_09.Interpret.interpret.util.ReflectionUtil;

public class OneField {
	private Field field = null;
	public static int NUMBER_OF_INFO_ELEMENT = 6;
	private int id = -1;
	private String modifier = "";
	private String type = "";
	private String name = "";
	private Object value = "";

	public OneField(int id , Object reflectObject , Field field) throws IllegalAccessException{
		this.field = field;
		this.id = id;
		modifier = ReflectionUtil.convertModifiers(field.getModifiers());
		/*
		if(t instanceof ParameterizedType ){
			Type[] g = ((ParameterizedType) t).getActualTypeArguments();
			if(g[0] instanceof WildcardType){
				(WildcardType g[0]).
			}
		}
		*/
		type = field.getType().toString();
		/*
		TypeVariable[] b = field.getType().getTypeParameters();
		System.out.println(type);
		for(int i=0 ; i<b.length ; i++){
			Type[] c = b[i].getBounds();
			for(int j=0 ; j<c.length ; j++){
				System.out.println(c[j]);
			}
		}
		System.out.println();
		*/
		name = field.getName();
		try {
			field.setAccessible(true);
			if(!isStatic() && (reflectObject == null)){
				return;
			}
			value = field.get(reflectObject);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw e;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw e;
		}
	}

	public int getId(){
		return id;
	}

	public String getModifier(){
		return modifier;
	}

	public String getType(){
		return type;
	}

	public String getName(){
		return name;
	}

	public String[] getInfo(){
		String[] fieldInfo = new String[NUMBER_OF_INFO_ELEMENT];
		fieldInfo[0] = String.valueOf(id);
		fieldInfo[1] = modifier;
		fieldInfo[2] = type;
		fieldInfo[3] = name;
		if(value == null){
			fieldInfo[4] = "null";
		}else{
			fieldInfo[4] = value.toString();
		}
		return fieldInfo;
	}

	public void rewriteValue(Object reflectObject , String value) throws IllegalArgumentException, IllegalAccessException{
		field.setAccessible(true);
		if(type.equals("boolean")){
			field.set(reflectObject, Boolean.parseBoolean(value));
		}else if(type.equals("byte")){
			field.set(reflectObject, Byte.parseByte(value));
		}else if(type.equals("char")){
			field.set(reflectObject, (char) value.charAt(0));
		}else if(type.equals("int")){
			field.set(reflectObject, Integer.parseInt(value));
		}else if(type.equals("long")){
			field.set(reflectObject, Long.parseLong(value));
		}else if(type.equals("short")){
			field.set(reflectObject, Short.parseShort(value));
		}else if(type.equals("double")){
			field.set(reflectObject, Double.parseDouble(value));
		}else if(type.equals("float")){
			field.set(reflectObject, Float.parseFloat(value));
		}else if(type.equals("class java.lang.String")){
			field.set(reflectObject, value.toString());
		}else{
			if(value.equals("null")){
				value = null;
			}
			field.set(reflectObject, RequestDispatcher.getInstanceByVariableName(value));
		}
	}

	public boolean isStatic(){
		if(modifier.contains("static")){
			return true;
		}else{
			return false;
		}
	}

}
