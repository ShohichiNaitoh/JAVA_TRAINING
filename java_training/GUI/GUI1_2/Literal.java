package GUI1_2;

import java.awt.Color;
import java.awt.Font;

public class Literal {

	String font = "";
	int fontSize = 0;
	Color fontColor = null;

	enum FONT{
		SERIF , SANS_SERIF , MONOSPACED
	}

	enum SIZE{
		FOUTY , HUNDRED , TWO_HUNDRED
	}

	enum COLOR{
		BLACK , RED , BLUE
	}


	public Literal(FONT font , SIZE size , COLOR color){
		setFont(font);
		setSize(size);
		setColor(color);
	}

	public String getFont(){
		return font;
	}

	public int getFontIndex(){
		int index = 0;
		if(font.equals("Serif")){
			index = 0;
		}else if(font.equals("SansSerif")){
			index = 1;
		}else if(font.equals("Monospaced")){
			index = 2;
		}
		return index;
	}

	public void setFont(FONT newFont){
		switch(newFont){
		case SERIF:
			this.font = "Serif";
			break;
		case SANS_SERIF:
			this.font = "SansSerif";
			break;
		case MONOSPACED:
			this.font = "Monospaced";
			break;
		default:
			throw new IllegalArgumentException();
		}
	}

	public int getSize(){
		return fontSize;
	}

	public int getSizeIndex(){
		int index = 0;
		if(fontSize == 40){
			index = 0;
		}else if(fontSize == 100){
			index = 1;
		}else if(fontSize == 200){
			index = 2;
		}
		return index;
	}

	public void setSize(SIZE newSize){
		switch(newSize){
		case FOUTY:
			this.fontSize = 40;
			break;
		case HUNDRED:
			this.fontSize = 100;
			break;
		case TWO_HUNDRED:
			this.fontSize = 200;
			break;
		default:
			throw new IllegalArgumentException();
		}
	}

	public Color getColor(){
		return fontColor;
	}

	public int getColorIndex(){
		int index = 0;
		if(fontColor == Color.BLACK){
			index = 0;
		}else if(fontColor == Color.RED){
			index = 1;
		}else if(fontColor ==  Color.BLUE){
			index = 2;
		}
		return index;
	}

	public void setColor(COLOR newColor){
		switch(newColor){
		case BLACK:
			this.fontColor = Color.BLACK;
			break;
		case RED:
			this.fontColor = Color.RED;
			break;
		case BLUE:
			this.fontColor = Color.BLUE;
			break;
		default:
			throw new IllegalArgumentException();
		}
	}
}
