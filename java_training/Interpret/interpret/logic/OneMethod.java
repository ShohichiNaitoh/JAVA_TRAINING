package interpret.logic;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class OneMethod {
	private Method method = null;
	private String modifier = "";
	private String methodReturn = "";
	private String methodName = "";
	private String[] methodArgs = null;

	public OneMethod(Method method) throws IllegalAccessException{
		this.method = method;
		modifier = convertModifiers(method.getModifiers());
		methodReturn = method.getReturnType().toString();
		methodName = method.getName();
		Type[] types =  method.getGenericParameterTypes();
		methodArgs = new String[types.length];
		for(int i=0 ; i<types.length ; i++){
			methodArgs[i] = types[i].toString();
		}
	}

	public String[] getMethodInformation(){
		String[] str = new String[2];
		str[0] = modifier;
		str[1] = methodReturn + " " + methodName + "(";
		for(int i=0 ; i<methodArgs.length ; i++){
			if(i != methodArgs.length-1){
				str[1] += methodArgs[i].toString() + " arg" + i + ", ";
			}else{
				str[1] += methodArgs[i].toString() + " arg" + i;
			}
		}
		str[1] += ")";
		return str;
	}

	public String[] getMethodArgs(){
		return methodArgs;
	}

	public Object execute(Object reflectObject) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		Type[] types =  method.getGenericParameterTypes();
		return method.invoke(reflectObject);
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
