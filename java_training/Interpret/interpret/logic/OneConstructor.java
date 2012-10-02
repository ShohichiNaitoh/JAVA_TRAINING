package interpret.logic;

import interpret.dispatcher.RequestDispatcher;
import interpret.util.ReflectionUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;


public class OneConstructor {
	private Constructor constructor = null;

	public static int NUMBER_OF_INFO_ELEMENT = 6;
	private int id = -1;
	private String modifier = "";
	private String returnValue = "";
	private String constructorName = "";
	private String[] constructorArgs = null;
	private String[] exceptions = null;

	public OneConstructor(int id , Constructor constructor){
		this.id = id;
		if(constructor == null){
			constructorName = "null";
		}else{
			this.constructor = constructor;
			modifier = ReflectionUtil.convertModifiers(constructor.getModifiers());
			constructorName = constructor.getName();
			Type[] argtypes =  constructor.getParameterTypes();
			constructorArgs = new String[argtypes.length];
			for(int i=0 ; i<argtypes.length ; i++){
				constructorArgs[i] = argtypes[i].toString();
			}
			Type[] exceptiontypes = constructor.getGenericExceptionTypes();
			exceptions = new String[exceptiontypes.length];
			for(int i=0 ; i<exceptiontypes.length ; i++){
				exceptions[i] = exceptiontypes[i].toString();
			}
		}
	}

	public int getId(){
		return id;
	}

	public String getModifier(){
		return modifier;
	}

	public String getName(){
		return constructorName + ReflectionUtil.adjustArgsFormatBySimpleName(ReflectionUtil.joinArgs(constructorArgs));
	}

	public Object execute(Object reflectObject , String[] argsValue) throws Throwable{
		if(constructorName.equals("null")){
			return null;
		}

		Object[] args = new Object[argsValue.length];
		for(int i=0 ; i<argsValue.length ; i++){
			if(constructorArgs[i].equals("boolean")){
				args[i] = Boolean.parseBoolean(argsValue[i]);
			}else if(constructorArgs[i].equals("byte")){
				args[i] = Byte.parseByte(argsValue[i]);
			}else if(constructorArgs[i].equals("char")){
				args[i] = (char) argsValue[i].charAt(0);
			}else if(constructorArgs[i].equals("int")){
				args[i] = Integer.parseInt(argsValue[i]);
			}else if(constructorArgs[i].equals("long")){
				args[i] = Long.parseLong(argsValue[i]);
			}else if(constructorArgs[i].equals("short")){
				args[i] = Short.parseShort(argsValue[i]);
			}else if(constructorArgs[i].equals("double")){
				args[i] = Double.parseDouble(argsValue[i]);
			}else if(constructorArgs[i].equals("float")){
				args[i] = Float.parseFloat(argsValue[i]);
			}else if(constructorArgs[i].equals("class java.lang.String")){
				args[i] = argsValue[i].toString();
			}else{
				if(argsValue[i].equals("null")){
					args[i] = null;
				}
				args[i] = RequestDispatcher.getInstanceByVariableName(argsValue[i]);
			}
		}

		try {
			return constructor.newInstance(args);
		} catch (InvocationTargetException e) {
			throw e.getCause();
		}
	}

	public String[] getInfo(){
		String[] str = new String[NUMBER_OF_INFO_ELEMENT];
		str[0] = String.valueOf(id);
		str[1] = modifier;
		str[2] = returnValue;
		str[3] = constructorName;
		str[4] = ReflectionUtil.joinArgs(constructorArgs);
		str[5] = ReflectionUtil.joinExceptions(exceptions);
		/*
		if(constructorArgs != null){
			str[2] = "";
			for(int i=0 ; i<constructorArgs.length ; i++){
				if(i != constructorArgs.length-1){
					str[2] += constructorArgs[i].toString() +  ",";
				}else{
					str[2] += constructorArgs[i].toString();
				}
			}
		}else{
			str[2] = null;
		}
		*/
		return str;
	}


}
