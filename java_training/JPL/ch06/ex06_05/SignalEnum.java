package ch06.ex06_05;

public class SignalEnum {

	enum Signal {
		BLUE(blue){
			public Color getColor() {
				return color;
			}
		},
		YELLOW(yellow){
			public Color getColor() {
				return color;
			}
		},
		RED(red){
			public Color getColor() {
				return color;
			}
		};

		Color color;
		Signal(Color color) { this.color = color;}

		abstract public Color getColor();
	}

	static public Color blue = new Color();
	static public Color yellow = new Color();
	static public Color red = new Color();

}
