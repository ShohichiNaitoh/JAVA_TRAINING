package ch16.ex16_04;

import java.lang.annotation.Annotation;

@Hoge(value="foo")
public class AnnotationInterpretor {

	public static void main(String[] args){
		AnnotationInterpretor annotationInterpretor = new AnnotationInterpretor();
		for(String name : args){
			try{
				Class<?> cls = Class.forName(name);
				annotationInterpretor.showAnnotation(cls);
			}catch(ClassNotFoundException e){
				System.err.println(e);
			}
		}
	}

	private void showAnnotation(Class<?> cls){
		System.out.println(cls.getName());
		Annotation[] annotations = cls.getAnnotations();
		for(Annotation annotation : annotations){
			System.out.println("\t" + annotation.toString());
		}
		System.out.println();
	}
}
