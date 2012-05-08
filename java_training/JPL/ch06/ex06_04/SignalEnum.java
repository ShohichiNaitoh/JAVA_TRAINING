package ch06.ex06_04;

public class SignalEnum {

	enum Signal {
		BLUE(blue),
		YELLOW(yellow),
		RED(red);

		Color color;
		Signal(Color color) { this.color = color;}

		public Color getColor() { return color; }
	}

	static public Color blue = new Color();
	static public Color yellow = new Color();
	static public Color red = new Color();

}
