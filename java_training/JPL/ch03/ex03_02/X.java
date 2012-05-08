package ch03.ex03_02;

public class X {

	protected int xMask = 0x00ff;
	protected int fullMask;

	public X(){
		displayMask();
		fullMask = xMask;
		displayMask();
	}

	public int mask(int orig){
		return (orig & fullMask);
	}

	public void displayMask(){
		System.out.printf( "xMask=%04x , fullMask=%04x%n" , xMask , fullMask );
	}

}
