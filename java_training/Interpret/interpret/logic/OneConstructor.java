package interpret.logic;

import interpret.gui.util.GuiUtility;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;


public class OneConstructor {
	private Constructor constructor = null;

	public static int NUMBER_OF_INFO_ELEMENT = 5;
	private int id = -1;
	private String modifier = "";
	private String returnValue = "";
	private String constructorName = "";
	private String[] constructorArgs = null;

	public OneConstructor(int id , Constructor constructor){
		this.id = id;
		if(constructor == null){
			constructorName = "null";
		}else{
			this.constructor = constructor;
			modifier = GuiUtility.convertModifiers(constructor.getModifiers());
			constructorName = constructor.getName();
			Type[] types =  constructor.getGenericParameterTypes();
			constructorArgs = new String[types.length];
			for(int i=0 ; i<types.length ; i++){
				constructorArgs[i] = types[i].toString();
			}
		}
	}

	public int getId(){
		return id;
	}

	public String getModifier(){
		return modifier;
	}

	public String getName(){
		return constructorName + GuiUtility.adjustArgsFormat(GuiUtility.joinArgs(constructorArgs));
	}


	public String[] getInfo(){
		String[] str = new String[NUMBER_OF_INFO_ELEMENT];
		str[0] = String.valueOf(id);
		str[1] = modifier;
		str[2] = returnValue;
		str[3] = constructorName;
		str[4] = GuiUtility.joinArgs(constructorArgs);
		/*
		if(constructorArgs != null){
			str[2] = "";
			for(int i=0 ; i<constructorArgs.length ; i++){
				if(i != constructorArgs.length-1){
					str[2] += constructorArgs[i].toString() +  ",";
				}else{
					str[2] += constructorArgs[i].toString();
				}
			}
		}else{
			str[2] = null;
		}
		*/
		return str;
	}


}
