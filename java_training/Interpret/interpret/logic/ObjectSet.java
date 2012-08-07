package interpret.logic;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ObjectSet {

	private ArrayList<OneObject> objects = new ArrayList<OneObject>();
	private OneObject currentObject = null;
	private ArrayList<String> objectTypeNames = new ArrayList<String>();

	private String exceptionMessage = null;


	public void CreateObjects(String className , int size) throws IllegalAccessException, InstantiationException, ClassNotFoundException{
		try {
			Class<?> reflectClass = Class.forName(className);
			Object[] reflectObjects = (Object[]) Array.newInstance(reflectClass , size);
			for(Object reflectObject : reflectObjects){
				try {
					reflectObject = reflectClass.newInstance();
					objects.add(new OneObject(reflectObject));
				} catch (InstantiationException e) {
					e.printStackTrace();
					throw e;
				} catch (IllegalAccessException e) {
					e.printStackTrace();
					throw e;
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void setCurrentObject(int index){
		if(index == -1){
			currentObject = null;
		}else{
			currentObject = objects.get(index);
		}
	}

	public OneObject getCurrentObject(){
		return currentObject;
	}

	public ArrayList<String> getObjectTypeNames(){
		for(int i=0 ; i<objects.size() ; i++){
			objectTypeNames.add(objects.get(i).getObjectTypeName() + "[" + i + "]");
		}
		return objectTypeNames;
	}

	public String[][] getCurrentObjectFieldInformation(){
		return currentObject.getAllField();
	}

	public String[][] getCurrentObjectMethodInformation(){
		return currentObject.getAllMethod();
	}

	public String getExceptionMessage(){
		return exceptionMessage;
	}

}
