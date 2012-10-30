package ch16.ex16_09.Interpret.interpret.logic;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;


public class OneObject {

	private Class<?> classFor = null;
	private Object reflectObject = null;

	private TreeMap<String,OneField> fieldMap = new TreeMap<String,OneField>();
	private TreeMap<String,OneConstructor> constructorMap = new TreeMap<String,OneConstructor>();
	private TreeMap<String,OneMethod> methodMap = new TreeMap<String,OneMethod>();
	private int selectedFieldId = -1;
	private int selectedMethodId = -1;
	private String calledConstructorName = null;

	private int memberId = 0;

	public OneObject(String className) throws IllegalAccessException, ClassNotFoundException{
		memberId = 0;
		classFor = Class.forName(className);
		listupStaticMember();
	}

	public OneObject(String className , Object reflectObject) throws IllegalAccessException, ClassNotFoundException{
		memberId = 0;
		classFor = Class.forName(className);
		this.reflectObject = reflectObject;
		listupStaticMember();
	}

	public void setSelectedFieldId(int id){
		this.selectedFieldId = id;
	}

	public void setSelectedMethodId(int id){
		this.selectedMethodId = id;
	}

	public String[] getSelectedFieldInfo(){
		return getFieldInfoById(selectedFieldId);
	}

	public String[] getSelectedMethodInfo(){
		return getMethodInfoById(selectedMethodId);
	}

	public String[][] getConstructorInfo(){
		String[][] constructorInfo = new String[constructorMap.size()][OneConstructor.NUMBER_OF_INFO_ELEMENT];
		int i=0;
		for(Map.Entry<String, OneConstructor> e : constructorMap.entrySet()){
			constructorInfo[i++] = e.getValue().getInfo();
		}
		return constructorInfo;
	}

	public String[][] getAllFieldInfo(){
		String[][] fieldInfo = new String[fieldMap.size()][OneField.NUMBER_OF_INFO_ELEMENT];
		int i=0;
		for(Map.Entry<String, OneField> e : fieldMap.entrySet()){
			fieldInfo[i++] = e.getValue().getInfo();
		}
		return fieldInfo;
	}

	public String[][] getAllMethodInfo(){
		String[][] methodInfo = new String[methodMap.size()][OneMethod.NUMBER_OF_INFO_ELEMENT];
		int i=0;
		for(Map.Entry<String, OneMethod> e : methodMap.entrySet()){
			methodInfo[i++] = e.getValue().getInfo();
		}
		return methodInfo;
	}

	public String[][] getConstructorContainedKeywords(String[] keywords){
		int numberHit = 0;
 		for(Map.Entry<String, OneConstructor> e : constructorMap.entrySet()){
 			OneConstructor targetConstructor = e.getValue();
 			String target = targetConstructor.getModifier() + " " + targetConstructor.getName();
 			if(containKeywords(target , keywords)){
 	 			numberHit++;
 			}
 		}
 		int i=0;
 		String[][] hitConstructorInfo = new String[numberHit][OneConstructor.NUMBER_OF_INFO_ELEMENT];
 		for(Map.Entry<String, OneConstructor> e : constructorMap.entrySet()){
 			OneConstructor targetConstructor = e.getValue();
 			String target = targetConstructor.getModifier() + " " + targetConstructor.getName();
 			if(containKeywords(target , keywords)){
 				hitConstructorInfo[i++] = e.getValue().getInfo();
 			}
 		}
 		return hitConstructorInfo;
	}

	public String[][] getFieldInfoContainedKeywords(String[] keywords){
		int numberHit = 0;
 		for(Map.Entry<String, OneField> e : fieldMap.entrySet()){
 			OneField targetField = e.getValue();
 			String target = targetField.getModifier() + " " + targetField.getType() + " " + targetField.getName();
 			if(containKeywords(target , keywords)){
 	 			numberHit++;
 			}
 		}
 		int i=0;
 		String[][] hitFieldInfo = new String[numberHit][OneField.NUMBER_OF_INFO_ELEMENT];
 		for(Map.Entry<String, OneField> e : fieldMap.entrySet()){
 			OneField targetField = e.getValue();
 			String target = targetField.getModifier() + " " + targetField.getType() + " " + targetField.getName();
 			if(containKeywords(target , keywords)){
		 		hitFieldInfo[i++] = e.getValue().getInfo();
 			}
 		}
 		return hitFieldInfo;
	}

	public String[][] getMethodInfoContainedKeywords(String[] keywords){
		int numberHit = 0;
 		for(Map.Entry<String, OneMethod> e : methodMap.entrySet()){
 			OneMethod targetMethod = e.getValue();
 			String target = targetMethod.getModifier() + " " + targetMethod.getReturnValue() + " " + targetMethod.getName();
 			if(containKeywords(target , keywords)){
 				numberHit++;
 			}
 		}
 		int i=0;
 		String[][] hitMethodInfo = new String[numberHit][OneMethod.NUMBER_OF_INFO_ELEMENT];
 		for(Map.Entry<String, OneMethod> e : methodMap.entrySet()){
 			OneMethod targetMethod = e.getValue();
 			String target = targetMethod.getModifier() + " " + targetMethod.getReturnValue() + " " + targetMethod.getName();
 			if(containKeywords(target , keywords)){
 				hitMethodInfo[i++] = e.getValue().getInfo();
 			}
 		}
 		return hitMethodInfo;
	}

	private boolean containKeywords(String target , String[] keywords){
		for(int i=0 ; i<keywords.length ; i++){
			if(keywords[i].length() == 0){
				continue;
			}
 			if(!target.toLowerCase().contains(keywords[i].toLowerCase())){
 	 			return false;
 			}
		}
		return true;
	}

	public String getInstanceInfo(){
		if(reflectObject == null){
			return "null";
		}else{
			return calledConstructorName;
		}
	}

	private String[] getFieldInfoById(int id){
		for(Map.Entry<String, OneField> e : fieldMap.entrySet()){
			if(e.getValue().getId() == id){
				return e.getValue().getInfo();
			}
		}
		return null;
	}

	private String[] getMethodInfoById(int id){
		for(Map.Entry<String, OneConstructor> e : constructorMap.entrySet()){
			if(e.getValue().getId() == id){
				return e.getValue().getInfo();
			}
		}
		for(Map.Entry<String, OneMethod> e : methodMap.entrySet()){
			if(e.getValue().getId() == id){
				return e.getValue().getInfo();
			}
		}
		return null;
	}

	public void rewriteSelectedField(String value) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException{
		for(Map.Entry<String, OneField> e : fieldMap.entrySet()){
			if(e.getValue().getId() == selectedFieldId){
				e.getValue().rewriteValue(reflectObject , value);
			}
		}
		if(reflectObject == null){
			listupStaticFields();
			listupStaticMethods();
		}else{
			listupAllFields();
			listupAllMethods();
		}
	}

	public Object executeSelectedMethod(String[] argsValue) throws Throwable{
		if(selectedMethodId < constructorMap.size()){
			for(Entry<String, OneConstructor> e : constructorMap.entrySet()){
				if(e.getValue().getId() == selectedMethodId){
					reflectObject = e.getValue().execute(reflectObject , argsValue);
					if(reflectObject == null){
						listupStaticFields();
						listupStaticMethods();
					}else{
						listupAllFields();
						listupAllMethods();
						calledConstructorName = e.getValue().getName();
						return "Instance created.";
					}
				}
			}
		}else{
			for(Entry<String, OneMethod> e : methodMap.entrySet()){
				if(e.getValue().getId() == selectedMethodId){
					Object returnObj = e.getValue().execute(reflectObject , argsValue);
					if(reflectObject == null){
						listupStaticFields();
						listupStaticMethods();
					}else{
						listupAllFields();
						listupAllMethods();
					}
					return returnObj;
				}
			}
		}

		return null;
	}

	public Object getReflectObject(){
		return reflectObject;
	}

	private void listupConstructors() throws IllegalAccessException{
		constructorMap.clear();
		constructorMap.put("" , new OneConstructor(memberId++ , null));
		Constructor[] declaredConstructors = classFor.getDeclaredConstructors();
		for(Constructor constructor : declaredConstructors){
			OneConstructor oneConstructor = new OneConstructor(memberId++ , constructor);
			if(constructorMap.get(oneConstructor.getName()) == null){
				constructorMap.put(oneConstructor.getName() , oneConstructor);
			}
		}
	}

	private void listupStaticMember() throws IllegalAccessException{
		memberId = 0;
		listupConstructors();
		listupStaticFields();
		listupStaticMethods();
	}

	private void listupAllMember() throws IllegalAccessException{
		memberId = 0;
		listupConstructors();
		listupAllFields();
		listupAllMethods();
	}

	private void listupStaticFields() throws IllegalAccessException{
		fieldMap.clear();
		listupFieldsRecursive(classFor , true , true);
	}

	private void listupStaticMethods() throws IllegalAccessException{
		methodMap.clear();
		listupMethodsRecursive(classFor , true , true);
	}

	private void listupAllFields() throws IllegalAccessException{
		fieldMap.clear();
		listupFieldsRecursive(classFor , false , true);
	}

	private void listupAllMethods() throws IllegalAccessException{
		methodMap.clear();
		listupMethodsRecursive(classFor , false , true);
	}

	private void listupFieldsRecursive(Type type , boolean restrictStaticField , boolean firstFlag) throws IllegalAccessException{
		Class<?> cls = null;
		if(type instanceof Class<?>){
			cls = (Class<?>) type;
			Field[] fields = null;
			if(firstFlag){
				fields = cls.getDeclaredFields();
			}else{
				fields = cls.getFields();
			}
			for(Field field : fields){
				OneField oneField = new OneField(memberId++ , reflectObject, field);
				if(restrictStaticField){
					if(!oneField.isStatic()){
						continue;
					}
				}
				if(fieldMap.get(oneField.getName().toLowerCase()) == null){
					fieldMap.put(oneField.getName().toLowerCase() , oneField);
				}
			}

			Type superType = cls.getGenericSuperclass();
			if(superType == null){
				return;
			}
			listupFieldsRecursive(superType , restrictStaticField , false);
		}
	}

	private void listupMethodsRecursive(Type type , boolean restrictStaticMethod , boolean firstFlag) throws IllegalAccessException{
		Class<?> cls = null;
		if(type instanceof Class<?>){
			cls = (Class<?>) type;
			Method[] methods = null;
			if(firstFlag){
				methods = cls.getDeclaredMethods();
			}else{
				methods = cls.getMethods();
			}
			for(Method method : methods){
				OneMethod oneMethod = new OneMethod(memberId++ , method);
				if(restrictStaticMethod){
					if(!oneMethod.isStatic()){
						continue;
					}
				}
				if(methodMap.get(oneMethod.getName()) == null){
					methodMap.put(/*String.valueOf(ggg++)*/oneMethod.getName() , oneMethod);
				}
			}

			Type superType = cls.getGenericSuperclass();
			if(superType == null){
				return;
			}
			listupMethodsRecursive(superType , restrictStaticMethod , false);
		}
	}


}
