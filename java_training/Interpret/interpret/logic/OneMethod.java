package interpret.logic;

import interpret.gui.util.GuiUtility;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;

public class OneMethod {
	private Method method = null;
	public static int NUMBER_OF_INFO_ELEMENT = 5;
	private int id = -1;
	private String modifier = "";
	private String returnValue = "";
	private String methodName = "";
	private String[] methodArgs = null;
	private boolean flagStatic = false;

	public OneMethod(int id , Method method) throws IllegalAccessException{
		this.method = method;
		this.id = id;
		modifier = GuiUtility.convertModifiers(method.getModifiers());
		returnValue = method.getGenericReturnType().toString();
		methodName = method.getName();
		Type[] types =  method.getGenericParameterTypes();
		methodArgs = new String[types.length];
		for(int i=0 ; i<types.length ; i++){
			methodArgs[i] = types[i].toString();
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
		return methodName + GuiUtility.adjustArgsFormat(GuiUtility.joinArgs(methodArgs));
	}

	public String[] getInfo(){
		String[] str = new String[NUMBER_OF_INFO_ELEMENT];
		str[0] = String.valueOf(id);
		str[1] = modifier;
		str[2] = returnValue;
		str[3] = methodName;
		str[4] = GuiUtility.joinArgs(methodArgs);
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

	public Object execute(Object reflectObject) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		Type[] types =  method.getGenericParameterTypes();
		return method.invoke(reflectObject);
	}

	public boolean isStatic(){
		if(modifier.contains("static")){
			return true;
		}else{
			return false;
		}
	}

}
