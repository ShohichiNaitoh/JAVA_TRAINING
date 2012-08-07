package ch16.ex16_02;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

public class TypeDesc {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TypeDesc desc = new TypeDesc();
		for(String name : args){
			try{
				Class<?> startClass = Class.forName(name);
				desc.printType(startClass , 0 , basic);
			}catch(ClassNotFoundException e){
				System.err.println(e);
			}
		}
	}

	private java.io.PrintStream out = System.out;

	private static String[]
	       basic	= { "class" , "interface", "enum", "anotation", "nested class"},
	       supercl	= { "extends" , "implements"},
	       iFace	= { null , "extends"};

	private void printType(Type type, int depth, String[] labels){
		if(type == null || type == Object.class){
			return;
		}

		Class<?> cls = null;
		if(type instanceof Class<?>){
			cls = (Class<?>) type;
		}else if(type instanceof ParameterizedType){
			cls = (Class<?>) ((ParameterizedType) type).getRawType();
		}else{
			throw new Error("Unexpected non-class type");
		}

		for(int i=0 ; i<depth ; i++){
			out.print(" ");
		}
		int kind = cls.isAnnotation() ? 3 :
			cls.isEnum() ? 2 :
			cls.isInterface() ? 1 :
			cls.isMemberClass() ? 4 : 0;
		out.print(labels[kind] + " ");
		out.print(cls.getCanonicalName());

		TypeVariable<?>[] params = cls.getTypeParameters();
		if(params.length > 0){
			out.print('<');
			for(TypeVariable<?> param : params){
				out.print(param.getName());
				out.print(", ");
			}
			if(kind == 4){
				out.println("\b\b> enclosing " + cls.getEnclosingClass());
			}else{
				out.println("\b\b>");
			}
		}else{
			out.println();
		}

		Type[] interfaces = cls.getGenericInterfaces();
		for(Type iface : interfaces){
			printType(iface, depth + 1, cls.isInterface() ? iFace : supercl);
		}

		printType(cls.getGenericSuperclass(), depth + 1 , supercl);
	}
}
