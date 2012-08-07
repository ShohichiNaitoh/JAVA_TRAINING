package interpret.logic;

import java.lang.reflect.InvocationTargetException;

import interpret.gui.InterpretFrameMain;
import interpret.gui.dialog.FieldOperationDialog;
import interpret.gui.dialog.MethodOperationDialog;
import interpret.gui.section.ClassInfoPanel;
import interpret.gui.section.FieldInfoPanel;
import interpret.gui.section.MethodInfoPanel;
import interpret.gui.section.ObjectArrayInfoPanel;
import interpret.gui.section.ResultOutputPanel;

public class RequestDispatcher {

	private ObjectSet objectSet = null;

	private InterpretFrameMain interpretFrame = null;
	private ClassInfoPanel classInfoPanel = null;
	private ObjectArrayInfoPanel objectArrayInfoPanel = null;
	private FieldInfoPanel fieldInfoPanel = null;
	private MethodInfoPanel methodInfoPanel = null;
	private ResultOutputPanel resultOutputPanel = null;


	public void setPanel(InterpretFrameMain interpretFrame , ClassInfoPanel classInfoPanel, ObjectArrayInfoPanel objectArrayInfoPanel, FieldInfoPanel fieldInfoPanel, MethodInfoPanel methodInfoPanel, ResultOutputPanel resultOutputPanel){
		this.interpretFrame = interpretFrame;
		this.classInfoPanel = classInfoPanel;
		this.objectArrayInfoPanel = objectArrayInfoPanel;
		this.fieldInfoPanel = fieldInfoPanel;
		this.methodInfoPanel = methodInfoPanel;
		this.resultOutputPanel = resultOutputPanel;
	}

	public void pushedCreateObjects(String className , int size){
		try {
			objectSet = new ObjectSet();
			objectSet.CreateObjects(className, size);
			allReset();
			objectArrayInfoPanel.updateObjectArrayInfo(objectSet.getObjectTypeNames() , 0);
			pushedObjectArray(0);
		} catch (Exception e) {
			e.printStackTrace();
			allReset();
			resultOutputPanel.outputException(convertStackTraceToString(e));
		}
	}

	public void pushedObjectArray(int indexObject){
		try{
			objectSet.setCurrentObject(indexObject);
			fieldInfoPanel.updateFieldInfo(objectSet.getCurrentObjectFieldInformation());
			methodInfoPanel.updateMethodInfo(objectSet.getCurrentObjectMethodInformation());
			resultOutputPanel.reset();
		} catch (Exception e){
			e.printStackTrace();
			allReset();
			resultOutputPanel.outputException(convertStackTraceToString(e));
		}
	}

	public void clickedFieldInfoList(){
		methodInfoPanel.selectExclusive();
	}

	public void doubleClickedFieldInfoList(int indexField){
		try{
			objectSet.getCurrentObject().setSelectedFieldIndex(indexField);
			String[] fieldInfo = objectSet.getCurrentObject().getSelectedField();
			FieldOperationDialog dialog = new FieldOperationDialog(interpretFrame , fieldInfo , this);
			dialog.setVisible(true);
		} catch (Exception e){
			e.printStackTrace();
			allReset();
			resultOutputPanel.outputException(convertStackTraceToString(e));
		}
	}

	public void pushedFieldInfoDialog(String value){
		try {
			objectSet.getCurrentObject().rewriteSelectedField(value);
			fieldInfoPanel.updateFieldInfo(objectSet.getCurrentObjectFieldInformation());
		} catch (Exception e) {
			e.printStackTrace();
			allReset();
			resultOutputPanel.outputException(convertStackTraceToString(e));
		}
	}

	public void pushedMethodInfoDialog(){
		try{
			Object result = objectSet.getCurrentObject().executeSelectedMethod();
			resultOutputPanel.ouputMethodResult(result.toString());
		} catch (Exception e){
			e.printStackTrace();
			allReset();
			resultOutputPanel.outputException(convertStackTraceToString(e));
		}
	}

	public void clickedMethodInfoList(){
		fieldInfoPanel.selectExclusive();
	}

	public void doubleClickedMethodInfoList(int indexMethod){
		try{
			objectSet.getCurrentObject().setSelectedMethodIndex(indexMethod);
			String[] fieldInfo = objectSet.getCurrentObject().getSelectedMethod();
			MethodOperationDialog dialog = new MethodOperationDialog(interpretFrame , fieldInfo , this);
			dialog.setVisible(true);
		} catch (Exception e){
			e.printStackTrace();
			allReset();
			resultOutputPanel.outputException(convertStackTraceToString(e));
		}
	}

	private void allReset(){
		objectSet.setCurrentObject(-1);
		objectArrayInfoPanel.reset();
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
