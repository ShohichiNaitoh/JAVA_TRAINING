package ch03.ex03_02;

public class Y extends X {

	protected int yMask = 0xff00;

	public Y(){
		displayMask();
		fullMask |= yMask;
		displayMask();
	}

	public void displayMask(){
		System.out.printf( "xMask=%04x , yMask=%04x , fullMask=%04x%n" , xMask , yMask , fullMask );
	}

	public static void main(String[] args) {
		Y y = new Y();
	}

}
