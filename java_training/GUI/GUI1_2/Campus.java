package GUI1_2;

import java.awt.Color;

public class Campus {

	Color backColor = null;

	enum COLOR{
		WHITE , GREEN , YELLOW
	}

	public Campus(COLOR color){
		setColor(color);
	}

	public Color getColor(){
		return backColor;
	}

	public int getColorIndex(){
		int index = 0;
		if(backColor == Color.WHITE){
			index = 0;
		}else if(backColor == Color.GREEN){
			index = 1;
		}else if(backColor == Color.YELLOW){
			index = 2;
		}
		return index;
	}

	public void setColor(COLOR newColor){
		switch(newColor){
		case WHITE:
			this.backColor = Color.WHITE;
			break;
		case GREEN:
			this.backColor = Color.GREEN;
			break;
		case YELLOW:
			this.backColor = Color.YELLOW;
			break;
		default:
			throw new IllegalArgumentException();
		}
	}

}
