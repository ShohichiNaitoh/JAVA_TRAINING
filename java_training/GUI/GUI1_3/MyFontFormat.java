package GUI1_3;

import java.awt.Color;
import java.awt.GraphicsEnvironment;

public class MyFontFormat {

	String currentFont = "";
	int currentFontSize = 0;
	Color currentFontColor = null;

	String[] fontSet = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
	int[] fontSizeSet = {50 , 60 , 70 , 80 , 90 , 100 , 110 , 120 , 130 , 140 , 150 , 160 , 170 , 180 , 190 , 200};
	Color[] fontColorSet = {Color.BLACK , Color.BLUE , Color.CYAN , Color.DARK_GRAY , Color.GRAY , Color.GREEN , Color.LIGHT_GRAY , Color.MAGENTA , Color.ORANGE , Color.PINK , Color.RED , Color.WHITE , Color.YELLOW};
	String[] fontColorNameSet = {"Black" , "Blue" , "Cyan" , "DarkGray" , "Gray" , "Green" , "LightGray" , "Megenta" , "Orange" , "Pink" , "Red" , "White" , "Yellow"};

	public MyFontFormat(String defaultFont , int defaultFontSize , Color defaultColor){
		this.currentFont = defaultFont;
		this.currentFontSize = defaultFontSize;
		this.currentFontColor = defaultColor;
	}

	public String getCurrentFont(){
		return currentFont;
	}

	public void setCurrentFont(String font){
		this.currentFont = font;
	}

	public String[] getFontSet(){
		return fontSet;
	}

	public int getCurrentFontSize(){
		return currentFontSize;
	}

	public void setCurrentFontSize(int fontSize){
		this.currentFontSize = fontSize;
	}

	public int[] getFontSizeSet(){
		return fontSizeSet;
	}

	public Color getCurrentFontColor(){
		return currentFontColor;
	}

	public String getCurrentFontColorWithString(){
		for(int i=0 ; i<fontColorSet.length ; i++){
			if(currentFontColor.equals(fontColorSet[i])){
				return fontColorNameSet[i];
			}
		}
		return null;
	}

	public void setCurrentFontColorWithString(String colorName){
		for(int i=0 ; i<fontColorNameSet.length ; i++){
			if(colorName.equals(fontColorNameSet[i])){
				this.currentFontColor = fontColorSet[i];
			}
		}
	}

	public Color[] getFontColorSet(){
		return fontColorSet;
	}

	public String[] getFontColorSetWithString(){
		return fontColorNameSet;
	}
}
