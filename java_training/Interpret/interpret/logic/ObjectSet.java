package interpret.logic;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ObjectSet {

	private ArrayList<OneObject> objects = new ArrayList<OneObject>();
	private Object[] reflectObjects = null;
	private OneObject currentObject = null;

	public ObjectSet(String className , int size) throws IllegalAccessException, InstantiationException, ClassNotFoundException{
		try {
			Class<?> classFor = Class.forName(className);
			reflectObjects = (Object[]) Array.newInstance(classFor , size);
			for(int i=0 ; i<reflectObjects.length ; i++){
				objects.add(new OneObject(className , reflectObjects[i]));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw e;
		}
	}

	public int getSize(){
		return reflectObjects.length;
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

	public OneObject getObjectByIndex(int index){
		return objects.get(index);
	}
}
