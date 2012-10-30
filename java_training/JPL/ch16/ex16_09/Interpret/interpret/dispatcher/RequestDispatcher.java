package ch16.ex16_09.Interpret.interpret.dispatcher;

import java.util.ArrayList;

import ch16.ex16_09.Interpret.interpret.gui.section.FieldInfoPanel;
import ch16.ex16_09.Interpret.interpret.gui.section.MethodInfoPanel;
import ch16.ex16_09.Interpret.interpret.gui.section.ResultOutputPanel;
import ch16.ex16_09.Interpret.interpret.gui.section.UserInputPanel;
import ch16.ex16_09.Interpret.interpret.gui.section.VariableInfoPanel;
import ch16.ex16_09.Interpret.interpret.gui.section.dialog.FieldOperationDialog;
import ch16.ex16_09.Interpret.interpret.gui.section.dialog.MethodOperationDialog;
import ch16.ex16_09.Interpret.interpret.logic.Variable;
import ch16.ex16_09.Interpret.interpret.logic.Variable.VariableType;


public class RequestDispatcher {

	private static ArrayList<RequestDispatcher> dispatcherList = new ArrayList<RequestDispatcher>();
	private Variable variable = null;

	private UserInputPanel userInputPanel = null;
	private VariableInfoPanel variableInfoPanel = null;
	private FieldInfoPanel fieldInfoPanel = null;
	private MethodInfoPanel methodInfoPanel = null;
	private ResultOutputPanel resultOutputPanel = null;

	public RequestDispatcher(){
		dispatcherList.add(this);
	}

	public void setPanel(UserInputPanel userInputPanel, VariableInfoPanel variableInfoPanel, FieldInfoPanel fieldInfoPanel, MethodInfoPanel methodInfoPanel, ResultOutputPanel resultOutputPanel){
		this.userInputPanel = userInputPanel;
		this.variableInfoPanel = variableInfoPanel;
		this.fieldInfoPanel = fieldInfoPanel;
		this.methodInfoPanel = methodInfoPanel;
		this.resultOutputPanel = resultOutputPanel;
	}

	public void pushedExecuteButton(VariableType variableType , int size , String variableName , String className){
		try {
			if(variableType == VariableType.NOT_ARRAY){
				variable = new Variable(variableName , className);
			}else if(variableType == variableType.ARRAY){
				variable = new Variable(size , variableName , className);
			}
			if(!validateVariableName(variableName)){
				throw new IllegalArgumentException("The name of variable is already used.");
			}
			variableInfoPanel.updateVariableInfo(variable.getVariableInfo() , 0);
			pushedVariableInfoList(0);
		} catch (Exception e) {
			e.printStackTrace();
			allReset();
			resultOutputPanel.outputException(convertStackTraceToString(e));
		}
	}

	public void pushedVariableInfoList(int index){
		try{
			variable.setCurrentObject(index);
			fieldInfoPanel.updateFieldInfo(variable.getCurrentObject().getAllFieldInfo());
			fieldInfoPanel.resetSearchResult();
			methodInfoPanel.updateMethodInfo(variable.getCurrentObject().getConstructorInfo() , variable.getCurrentObject().getAllMethodInfo());
			methodInfoPanel.resetSearchResult();
			resultOutputPanel.reset();
		} catch (Exception e){
			e.printStackTrace();
			allReset();
			resultOutputPanel.outputException(convertStackTraceToString(e));
		}
	}

	public void clickedFieldInfoList(){
		methodInfoPanel.selectExclusive();
		resultOutputPanel.reset();
	}

	public void clickedMethodInfoList(){
		fieldInfoPanel.selectExclusive();
		resultOutputPanel.reset();
	}

	public void doubleClickedFieldInfoList(int id){
		try{
			variable.getCurrentObject().setSelectedFieldId(id);
			String[] fieldInfo = variable.getCurrentObject().getSelectedFieldInfo();
			FieldOperationDialog dialog = new FieldOperationDialog(null , fieldInfo , this);
			dialog.setVisible(true);
		} catch (Exception e){
			e.printStackTrace();
			allReset();
			resultOutputPanel.outputException(convertStackTraceToString(e));
		}
	}

	public void doubleClickedMethodInfoList(int id){
		try{
			variable.getCurrentObject().setSelectedMethodId(id);
			String[] methodInfo = variable.getCurrentObject().getSelectedMethodInfo();
			MethodOperationDialog dialog = new MethodOperationDialog(null , methodInfo , this);
			dialog.setVisible(true);
		} catch (Exception e){
			e.printStackTrace();
			allReset();
			resultOutputPanel.outputException(convertStackTraceToString(e));
		}
	}

	public void searchFieldInfoList(String[] keywords){
		fieldInfoPanel.updateFieldInfo(variable.getCurrentObject().getFieldInfoContainedKeywords(keywords));
	}

	public void searchMethodInfoList(String[] keywords){
		methodInfoPanel.updateMethodInfo(variable.getCurrentObject().getConstructorContainedKeywords(keywords),
										 variable.getCurrentObject().getMethodInfoContainedKeywords(keywords));
	}

	public void pushedFieldInfoDialog(String value){
		try {
			variable.getCurrentObject().rewriteSelectedField(value);
			searchFieldInfoList(fieldInfoPanel.getKeywords());
			searchMethodInfoList(methodInfoPanel.getKeywords());
		} catch (Exception e) {
			e.printStackTrace();
			allReset();
			resultOutputPanel.outputException(convertStackTraceToString(e));
		}
	}

	public void pushedMethodInfoDialog(String[] argsValue){
		try{
			Object result = variable.getCurrentObject().executeSelectedMethod(argsValue);
			variableInfoPanel.updateVariableInfo(variable.getVariableInfo());
			searchFieldInfoList(fieldInfoPanel.getKeywords());
			searchMethodInfoList(methodInfoPanel.getKeywords());
			if(result != null){
				resultOutputPanel.ouputMethodResult(result.toString());
			}
		} catch (Throwable e) {
			e.printStackTrace();
			allReset();
			resultOutputPanel.outputException(convertStackTraceToString(e));
		}
	}

	public int getNumberOfField(){
		if(variable == null){
			return 0;
		}
		return variable.getCurrentObject().getAllFieldInfo().length;
	}

	public int getNumberOfMethod(){
		if(variable == null){
			return 0;
		}
		return variable.getCurrentObject().getConstructorInfo().length + variable.getCurrentObject().getAllMethodInfo().length;
	}

	public Variable getVariable(){
		return variable;
	}

	public void removeRequestDispatcerFromList(){
		dispatcherList.remove(this);
		if(variable != null){
			variable.terminal();
		}
		variable = null;
	}

	public boolean validateVariableName(String variableName){
		for(RequestDispatcher requestDispatcher : dispatcherList){
			if(requestDispatcher != this){
				if(requestDispatcher.getVariable() == null){
					continue;
				}
				if(variableName.equals(requestDispatcher.getVariable().getVariableName())){
					return false;
				}
			}
		}
		return true;
	}

	public static Object getInstanceByVariableName(String variableName){
		for(RequestDispatcher requestDispatcher : dispatcherList){
			if(requestDispatcher.getVariable() == null){
				continue;
			}
			if(variableName.equals(requestDispatcher.getVariable().getVariableName())){
				return requestDispatcher.getVariable().getReflectObject();
			}
		}
		return null;
	}

	public String[] getVariableNamesMatchClassType(String classType){
		ArrayList<String> variableNames = new ArrayList<String>();
		for(RequestDispatcher requestDispatcher : dispatcherList){
			if(requestDispatcher != this){
				if(requestDispatcher.getVariable() == null){
					continue;
				}
				if(requestDispatcher.getVariable().isClassType(classType)){
					variableNames.add(requestDispatcher.getVariable().getVariableName());
				}
			}
		}
		String[] str = new String[variableNames.size()];
		for(int i=0 ; i<str.length ; i++){
			str[i] = variableNames.get(i);
		}
		return str;
	}

	public void outputReset(){
		resultOutputPanel.reset();
	}

	private void allReset(){
		variable = null;
		variableInfoPanel.reset();
		fieldInfoPanel.reset();
		methodInfoPanel.reset();
		resultOutputPanel.reset();
	}

	private String convertStackTraceToString(Throwable e){
		StringBuilder sb = new StringBuilder();
		sb.append(e + "\n");

		StackTraceElement stackTrace[] = e.getStackTrace();
		for(int i=0 ; i<stackTrace.length ; i++){
			sb.append("\t" + stackTrace[i].toString() + "\n");
		}
		return sb.toString();
	}
}
