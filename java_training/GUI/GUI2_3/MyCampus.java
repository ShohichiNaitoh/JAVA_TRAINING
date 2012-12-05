package GUI2_3;

import java.awt.Color;

public class MyCampus {

	Color currentBackColor = null;
	Color[] backColorSet = {Color.BLACK , Color.BLUE , Color.CYAN , Color.DARK_GRAY , Color.GRAY , Color.GREEN , Color.LIGHT_GRAY , Color.MAGENTA , Color.ORANGE , Color.PINK , Color.RED , Color.WHITE , Color.YELLOW};
	String[] backColorNameSet = {"Black" , "Blue" , "Cyan" , "DarkGray" , "Gray" , "Green" , "LightGray" , "Megenta" , "Orange" , "Pink" , "Red" , "White" , "Yellow"};

	public MyCampus(Color defaultBackColor){
		this.currentBackColor = defaultBackColor;
	}

	public Color getCurrentBackColor(){
		return currentBackColor;
	}

	public String getCurrentBackColorWithString(){
		for(int i=0 ; i<backColorSet.length ; i++){
			if(currentBackColor.equals(backColorSet[i])){
				return backColorNameSet[i];
			}
		}
		return null;
	}

	public void setCurrentBackColorWithString(String colorName){
		for(int i=0 ; i<backColorNameSet.length ; i++){
			if(colorName.equals(backColorNameSet[i])){
				this.currentBackColor = backColorSet[i];
			}
		}
	}

	public Color[] getBackColorSet(){
		return backColorSet;
	}

	public String[] getBackColorSetWithString(){
		return backColorNameSet;
	}

}
