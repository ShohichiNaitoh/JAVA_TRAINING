package interpret.logic;

import java.util.ArrayList;

public class Variable {

	public enum VariableType {
		NOT_ARRAY ,
		ARRAY,
	}

	private VariableType variableType = null;
	private String variableName = null;
	private String className = null;

	private ObjectSet objectSet = null;
	private OneObject oneObject = null;

	public Variable(String variableName , String className) throws IllegalAccessException, InstantiationException, ClassNotFoundException{
		this.variableType = VariableType.NOT_ARRAY;
		this.variableName = variableName;
		this.className = className;
		oneObject = new OneObject(className);
	}

	public Variable(int size , String variableName , String className) throws IllegalAccessException, InstantiationException, ClassNotFoundException{
		this.variableType = VariableType.ARRAY;
		this.variableName = variableName;
		this.className = className;
		objectSet = new ObjectSet(className , size);
	}

	public ArrayList<String> getVariableInfo(){
		ArrayList<String> variableInfo = new ArrayList<String>();

		String simpleClassName = null;
		int index = className.lastIndexOf(".");
		if(index != -1){
			simpleClassName = className.substring(index + 1);
		}

		if(variableType == VariableType.NOT_ARRAY){
			variableInfo.add(simpleClassName + "  " + variableName + "  =  " + oneObject.getInstanceInfo());
		}else if(variableType == VariableType.ARRAY){
			for(int i=0 ; i<objectSet.getSize() ; i++){
				variableInfo.add(simpleClassName + "  " + variableName + "[" + i + "]" + "  =  " + objectSet.getObjectByIndex(i).getInstanceInfo());
			}
		}
		return variableInfo;
	}

	public void setCurrentObject(int index){
		if(variableType == VariableType.NOT_ARRAY){

		}else if(variableType == VariableType.ARRAY){
			objectSet.setCurrentObject(index);
		}
	}

	public OneObject getCurrentObject(){
		if(variableType == VariableType.NOT_ARRAY){
			return oneObject;
		}else if(variableType == VariableType.ARRAY){
			return objectSet.getCurrentObject();
		}
		return null;
	}

}