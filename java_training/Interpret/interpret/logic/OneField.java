package interpret.logic;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class OneField {
	private Field field = null;
	private String modifier = "";
	private String type = "";
	private String name = "";
	private Object value = "";


	public OneField(Object reflectObject , Field field) throws IllegalAccessException{
		this.field = field;
		modifier = convertModifiers(field.getModifiers());
		type = field.getType().toString();
		name = field.getName();
		try {
			field.setAccessible(true);
			value = field.get(reflectObject);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw e;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw e;
		}
	}

	public String[] getFieldInformation(){
		String[] fieldInfo = new String[4];
		fieldInfo[0] = modifier;
		fieldInfo[1] = type;
		fieldInfo[2] = name;
		if(value == null){
			fieldInfo[3] = "null";
		}else{
			fieldInfo[3] = value.toString();
		}
		return fieldInfo;
	}

	public void rewriteValue(Object reflectObject , String value) throws IllegalArgumentException, IllegalAccessException{
		field.setAccessible(true);
		if(type.equals("boolean")){
			field.set(reflectObject, Boolean.parseBoolean(value));
		}else if(type.equals("byte")){
			field.set(reflectObject, Byte.parseByte(value));
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
		}else{
			field.set(reflectObject, value);
		}
	}

	private String convertModifiers(int mod){
		StringBuilder sb = new StringBuilder();

		if(Modifier.isPublic(mod)){
			sb.append("public ");
		}else if(Modifier.isProtected(mod)){
			sb.append("protected ");
		}else if(Modifier.isPrivate(mod)){
			sb.append("private ");
		}

		if(Modifier.isStatic(mod)){
			sb.append("static ");
		}

		if(Modifier.isFinal(mod)){
			sb.append("final ");
		}

		if(Modifier.isAbstract(mod)){
			sb.append("abstract ");
		}

		if(Modifier.isInterface(mod)){
			sb.append("interface ");
		}

		if(Modifier.isSynchronized(mod)){
			sb.append("synchronized ");
		}

		if(Modifier.isVolatile(mod)){
			sb.append("volatile ");
		}

		return sb.toString();
	}
}
