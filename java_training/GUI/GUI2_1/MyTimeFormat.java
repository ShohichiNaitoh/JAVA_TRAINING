package GUI2_1;

import java.awt.Color;
import java.awt.GraphicsEnvironment;

public class MyTimeFormat implements Cloneable{

	String currentFont = "";
	int currentFontSize = 0;
	Color currentLiteralColor = null;

	String[] fontSet = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
	int[] fontSizeSet = {50 , 60 , 70 , 80 , 90 , 100 , 110 , 120 , 130 , 140 , 150 , 160 , 170 , 180 , 190 , 200};
	Color[] literalColorSet = {Color.BLACK , Color.BLUE , Color.CYAN , Color.DARK_GRAY , Color.GRAY , Color.GREEN , Color.LIGHT_GRAY , Color.MAGENTA , Color.ORANGE , Color.PINK , Color.RED , Color.WHITE , Color.YELLOW};
	String[] literalColorNameSet = {"Black" , "Blue" , "Cyan" , "DarkGray" , "Gray" , "Green" , "LightGray" , "Megenta" , "Orange" , "Pink" , "Red" , "White" , "Yellow"};

	public MyTimeFormat(String defaultFont , int defaultFontSize , Color defaultColor){
		this.currentFont = defaultFont;
		this.currentFontSize = defaultFontSize;
		this.currentLiteralColor = defaultColor;
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

	public int getCurrentFontSizeByIndex(){
		for(int i=0 ; i<fontSet.length ; i++){
			if(currentFontSize == fontSizeSet[i]){
				return i;
			}
		}
		return -1;
	}

	public void setCurrentFontSize(int fontSize){
		this.currentFontSize = fontSize;
	}

	public void setCurrentFontSizeByIndex(int index){
		this.currentFontSize = fontSizeSet[index];
	}

	public int[] getFontSizeSet(){
		return fontSizeSet;
	}

	public Color getCurrentLiteralColor(){
		return currentLiteralColor;
	}

	public void setCurrentLiteralColor(Color color){
		currentLiteralColor = color;
	}

	public String getCurrentLiteralColorWithString(){
		for(int i=0 ; i<literalColorSet.length ; i++){
			if(currentLiteralColor.equals(literalColorSet[i])){
				return literalColorNameSet[i];
			}
		}
		return null;
	}

	public void setCurrentLiteralColorWithString(String colorName){
		for(int i=0 ; i<literalColorNameSet.length ; i++){
			if(colorName.equals(literalColorNameSet[i])){
				this.currentLiteralColor = literalColorSet[i];
			}
		}
	}

	public Color[] getLiteralColorSet(){
		return literalColorSet;
	}

	public String[] getLiteralColorSetWithString(){
		return literalColorNameSet;
	}

	public MyTimeFormat clone(){
		MyTimeFormat obj = null;
		try {
			obj = (MyTimeFormat) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return obj;
	}
}
