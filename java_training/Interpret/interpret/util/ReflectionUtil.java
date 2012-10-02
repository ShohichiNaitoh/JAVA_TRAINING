package interpret.util;

import java.lang.reflect.Modifier;

public class ReflectionUtil {

	public static String getSimpleName(String canonicalName){
		int index = canonicalName.lastIndexOf(".");
		if(index != -1){
			return  canonicalName.substring(index + 1);
		}
		return canonicalName;
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
