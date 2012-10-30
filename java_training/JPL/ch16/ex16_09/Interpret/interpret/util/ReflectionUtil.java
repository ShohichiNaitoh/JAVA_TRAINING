package ch16.ex16_09.Interpret.interpret.util;

import java.lang.reflect.Modifier;

public class ReflectionUtil {

	public static String getSimpleName(String canonicalName){
		String simpleName = null;
		int index = canonicalName.indexOf("[");
		if(index != -1){
			String s = canonicalName.substring(index);
			if(s.equals("[B")){
				return "byte[ ]";
			}else if(s.equals("[C")){
				return "char[ ]";
			}else if(s.equals("[D")){
				return "double[ ]";
			}else if(s.equals("[F")){
				return "float[ ]";
			}else if(s.equals("[I")){
				return "int[ ]";
			}else if(s.equals("[J")){
				return "long[ ]";
			}else if(s.equals("[S")){
				return "short[ ]";
			}else if(s.equals("[Z")){
				return "boolean[ ]";
			}else{
				if(s.contains("[L")){
					int j = canonicalName.lastIndexOf(".");
					if(j != -1){
						return canonicalName.substring(j + 1 , canonicalName.length()-1) + "[ ]";
					}
				}
			}
		}
		if(canonicalName.lastIndexOf(".") != -1){
			simpleName = canonicalName.substring(canonicalName.lastIndexOf(".") + 1);
		}else{
			simpleName = canonicalName;
		}
		return simpleName;
	}

	public static String replacePremitive(String className){
		int index = className.indexOf("[");
		if(index != -1){
			String s = className.substring(index);
			if(s.equals("[B")){
				return "byte[ ]";
			}else if(s.equals("[C")){
				return "char[ ]";
			}else if(s.equals("[D")){
				return "double[ ]";
			}else if(s.equals("[F")){
				return "float[ ]";
			}else if(s.equals("[I")){
				return "int[ ]";
			}else if(s.equals("[J")){
				return "long[ ]";
			}else if(s.equals("[S")){
				return "short[ ]";
			}else if(s.equals("[Z")){
				return "boolean[ ]";
			}
		}
		return className;
	}

	public static String joinArgs(String[] args){
		StringBuffer sb = new StringBuffer();
		if(args != null){
			for(int i=0 ; i<args.length ; i++){
				if(i != args.length-1){
					sb.append(args[i].toString() +  ",");
				}else{
					sb.append(args[i].toString());
				}
			}
			return sb.toString();
		}else{
			return null;
		}
	}

	public static String adjustArgsFormatBySimpleName(String joinedArg){
		StringBuffer sb = new StringBuffer();
		if(joinedArg != null){
			sb.append(" ( ");
			if(joinedArg.length() != 0){
				String[] args = joinedArg.split(",");
				for(int i=0 ; i<args.length ; i++){
					sb.append(ReflectionUtil.getSimpleName(args[i]));
					if(i != args.length-1){
						sb.append(" arg" + (i+1) + " , ");
					}else{
						sb.append(" arg" + (i+1));
					}
				}
			}
			sb.append(" )");
		}
		return sb.toString();
	}

	public static String joinExceptions(String[] exceptions){
		StringBuffer sb = new StringBuffer();
		if(exceptions != null){
			for(int i=0 ; i<exceptions.length ; i++){
				if(i != exceptions.length-1){
					sb.append(exceptions[i].toString() +  ",");
				}else{
					sb.append(exceptions[i].toString());
				}
			}
			return sb.toString();
		}else{
			return null;
		}
	}

	public static String convertModifiers(int mod){
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
