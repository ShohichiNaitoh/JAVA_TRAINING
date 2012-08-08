package ch16.ex16_05;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Member;
import java.util.ArrayList;

public class ClassContents {
	private static ArrayList<String> memberList = new ArrayList<String>();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try{
			Class<?> c = Class.forName(args[0]);
			System.out.println(c);
			printMembers(c.getFields());
			printMembers(c.getDeclaredFields());
			printMembers(c.getConstructors());
			printMembers(c.getDeclaredConstructors());
			printMembers(c.getMethods());
			printMembers(c.getDeclaredMethods());
		}catch(ClassNotFoundException e){
			System.out.println("unkown class: " + args[0]);
		}
	}

	private static void printMembers(Member[] mems){
		for(Member m : mems){
			if(m.getDeclaringClass() == Object.class){
				continue;
			}
			String dec1 = m.toString();
			boolean doDisplay = true;
			for(String existMember : ClassContents.memberList){
				if(dec1.equals(existMember)){
					doDisplay = false;
				}
			}
			ClassContents.memberList.add(dec1);

			if(!doDisplay){
				System.out.print(" ");
				Annotation[] annotations = ((AnnotatedElement) m).getAnnotations();
				for(Annotation annotation : annotations){
					System.out.print(annotation + ", ");
				}
				System.out.println(strip(dec1, "java.lang."));
			}
		}
	}

	private static String strip(String str , String stripedStr){
		return str.replaceAll(stripedStr, "");
	}
}
