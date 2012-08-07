package interpret.logic;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;


public class OneObject {

	private Object reflectObject = null;
	private ArrayList<OneField> fieldList = new ArrayList<OneField>();
	private ArrayList<OneMethod> methodList = new ArrayList<OneMethod>();
	private int selectedFieldIndex = -1;
	private int selectedMethodIndex = -1;


	public OneObject(Object reflectObject) throws IllegalAccessException{
		this.reflectObject = reflectObject;
		searchField();
		searchMethod();
	}

	private void searchField() throws IllegalAccessException{
		fieldList.clear();
		searchFieldRecursive(reflectObject.getClass());
	}

	private void searchMethod() throws IllegalAccessException{
		methodList.clear();
		searchMethodRecursive(reflectObject.getClass());
	}

	public String getObjectTypeName(){
		return (reflectObject.getClass()).getName();
	}

	public void setSelectedFieldIndex(int fieldIndex){
		this.selectedFieldIndex = fieldIndex;
	}

	public void setSelectedMethodIndex(int methodIndex){
		this.selectedMethodIndex = methodIndex;
	}

	public String[] getSelectedField(){
		return getFieldByIndex(selectedFieldIndex);
	}

	public String[] getSelectedMethod(){
		return getMethodByIndex(selectedMethodIndex);
	}

	public String[][] getAllField(){
		String[][] fieldsInfo = new String[fieldList.size()][4];
		for(int i=0 ; i<fieldList.size() ; i++){
			fieldsInfo[i] = fieldList.get(i).getFieldInformation();
		}
		return fieldsInfo;
	}

	public String[][] getAllMethod(){
		String[][] methodsInfo = new String[methodList.size()][2];
		for(int i=0 ; i<methodList.size() ; i++){
			methodsInfo[i] = methodList.get(i).getMethodInformation();
		}
		return methodsInfo;
	}

	public String[] getFieldByIndex(int index){
		return fieldList.get(index).getFieldInformation();
	}

	public String[] getMethodByIndex(int index){
		return methodList.get(index).getMethodInformation();
	}

	public void rewriteSelectedField(String value) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException{
		fieldList.get(selectedFieldIndex).rewriteValue(reflectObject , value);
		searchField();
	}

	public Object executeSelectedMethod() throws IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		return methodList.get(selectedMethodIndex).execute(reflectObject);
	}

	private void searchFieldRecursive(Type type) throws IllegalAccessException{
		Class<?> cls = null;
		if(type instanceof Class<?>){
			cls = (Class<?>) type;
			Field[] declaredFields = cls.getDeclaredFields();
			for(Field field : declaredFields){
				fieldList.add(new OneField(reflectObject, field));
			}
			/*
			Field[] fields = cls.getFields();
			for(Field field : fields){
				fieldList.add(new OneField(reflectObject, field));
			}
			*/

			Type superType = cls.getGenericSuperclass();
			if(superType == null){
				return;
			}
			searchFieldRecursive(superType);
		}
	}

	private void searchMethodRecursive(Type type) throws IllegalAccessException{
		Class<?> cls = null;
		if(type instanceof Class<?>){
			cls = (Class<?>) type;
			Method[] declaredMethods = cls.getDeclaredMethods();
			for(Method method : declaredMethods){
				methodList.add(new OneMethod(method));
			}
			/*
			Method[] methods = cls.getMethods();
			for(Method method : methods){
				methodList(new OneMethod(method));
			}
			*/

			Type superType = cls.getGenericSuperclass();
			if(superType == null){
				return;
			}
			searchMethodRecursive(superType);
		}
	}


}
