package ch16.ex16_09.Interpret.interpret.logic;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;

import ch16.ex16_09.Interpret.interpret.dispatcher.RequestDispatcher;
import ch16.ex16_09.Interpret.interpret.util.ReflectionUtil;

public class OneMethod {
	private Method method = null;
	public static int NUMBER_OF_INFO_ELEMENT = 6;
	private int id = -1;
	private String modifier = "";
	private String returnValue = "";
	private String methodName = "";
	private String[] methodArgs = null;
	private String[] exceptions = null;

	public OneMethod(int id , Method method) throws IllegalAccessException{
		this.method = method;
		this.id = id;
		modifier = ReflectionUtil.convertModifiers(method.getModifiers());
		returnValue = method.getGenericReturnType().toString();
		methodName = method.getName();
		Type[] types =  method.getParameterTypes();
		methodArgs = new String[types.length];
		for(int i=0 ; i<types.length ; i++){
			methodArgs[i] = types[i].toString();
		}
		Type[] exceptiontypes = method.getGenericExceptionTypes();
		exceptions = new String[exceptiontypes.length];
		for(int i=0 ; i<exceptiontypes.length ; i++){
			exceptions[i] = exceptiontypes[i].toString();
		}
	}

	public int getId(){
		return id;
	}

	public String getModifier(){
		return modifier;
	}

	public String getReturnValue(){
		return returnValue;
	}

	public String getName(){
		return methodName + ReflectionUtil.adjustArgsFormatBySimpleName(ReflectionUtil.joinArgs(methodArgs));
	}

	public String[] getInfo(){
		String[] str = new String[NUMBER_OF_INFO_ELEMENT];
		str[0] = String.valueOf(id);
		str[1] = modifier;
		str[2] = returnValue;
		str[3] = methodName;
		str[4] = ReflectionUtil.joinArgs(methodArgs);
		str[5] = ReflectionUtil.joinExceptions(exceptions);
		/*
		if(methodArgs != null){
			str[3] = "";
			for(int i=0 ; i<methodArgs.length ; i++){
				if(i != methodArgs.length-1){
					str[3] += methodArgs[i].toString() +  ",";
				}else{
					str[3] += methodArgs[i].toString();
				}
			}
		}else{
			str[3] = null;
		}
		*/
		return str;
	}

	public Object execute(Object reflectObject , String[] argsValue) throws Throwable{
		method.setAccessible(true);
		Object[] args = new Object[argsValue.length];
		for(int i=0 ; i<argsValue.length ; i++){
			if(methodArgs[i].equals("boolean")){
				args[i] = Boolean.parseBoolean(argsValue[i]);
			}else if(methodArgs[i].equals("byte")){
				args[i] = Byte.parseByte(argsValue[i]);
			}else if(methodArgs[i].equals("char")){
				args[i] = (char) argsValue[i].charAt(0);
			}else if(methodArgs[i].equals("int")){
				args[i] = Integer.parseInt(argsValue[i]);
			}else if(methodArgs[i].equals("long")){
				args[i] = Long.parseLong(argsValue[i]);
			}else if(methodArgs[i].equals("short")){
				args[i] = Short.parseShort(argsValue[i]);
			}else if(methodArgs[i].equals("double")){
				args[i] = Double.parseDouble(argsValue[i]);
			}else if(methodArgs[i].equals("float")){
				args[i] = Float.parseFloat(argsValue[i]);
			}else if(methodArgs[i].equals("class java.lang.String")){
				args[i] = argsValue[i].toString();
			}else{
				if(argsValue[i].equals("null")){
					args[i] = null;
				}else{
					args[i] = RequestDispatcher.getInstanceByVariableName(argsValue[i]);
				}
			}
		}

		try {
			return method.invoke(reflectObject , args);
		} catch (InvocationTargetException e) {
			throw e.getCause();
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
