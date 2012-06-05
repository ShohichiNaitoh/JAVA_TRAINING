package ch07.ex07_02;

public class LiteralMain {

	private short shortData;
	private int intData;
	private long longData;
	private float floatData;
	private double doubleData;

	public void assign(){
		shortData = 1000;
		System.out.println("shortData = 1000 : " + shortData);

		shortData = (short) Integer.MAX_VALUE;
		System.out.println("shortData = (short) Integer.MAX_VALUE : " + shortData);

		shortData = (short) Integer.MIN_VALUE;
		System.out.println("shortData = (short) Integer.MIN_VALUE : " + shortData);

		shortData = (short) 1000L;
		System.out.println("shortData = (short) 1000L : " + shortData);

		shortData = (short) Long.MAX_VALUE;
		System.out.println("shortData = (short) Long.MAX_VALUE : " + shortData);

		shortData = (short) Long.MIN_VALUE;
		System.out.println("shortData = (short) Long.MIN_VALUE : " + shortData);

		shortData = (short) 3.5f;
		System.out.println("shortData = (short) 3.5f : " + shortData);

		shortData = (short) 18E+2;
		System.out.println("shortData = (short) 18E+2 : " + shortData);

		shortData = (short) Double.MAX_VALUE;
		System.out.println("shortData = (short) Double.MAX_VALUE : " + shortData);

		shortData = (short) Double.MIN_VALUE;
		System.out.println("shortData = (short) Double.MIN_VALUE : " + shortData);

		shortData = (short) -0d;
		System.out.println("shortData = (short) -0d : " + shortData);

		intData = (int) 1000L;
		System.out.println("intData = (int) 1000L : " + intData);

		intData = (int) Long.MAX_VALUE;
		System.out.println("intData = (int) Long.MAX_VALUE : " + intData);

		intData = (int) Long.MIN_VALUE;
		System.out.println("intData = (int) Long.MIN_VALUE : " + intData);

		intData = (int) 3.5f;
		System.out.println("intData = (int) 3.5f : " + intData);

		intData = (int) 3.5d;
		System.out.println("intData = (int) 3.5d : " + intData);

		intData = (int) 18E+2;
		System.out.println("intData = (int) 18E+2 : " + intData);

		intData = (int) 18E-2;
		System.out.println("intData = (int) 18E-2 : " + intData);

		intData = (int) Double.MAX_VALUE;
		System.out.println("intData = (int) Double.MAX_VALUE : " + intData);

		intData = (int) Double.MIN_VALUE;
		System.out.println("intData = (int) Double.MIN_VALUE : " + intData);

		intData = (int) -0d;
		System.out.println("intData = (int) -0d : " + intData);

		longData = 1000;
		System.out.println("longData = 1000 : " + longData);

		longData = (long) 3.5f;
		System.out.println("longData = (long) 3.5f : " + longData);

		longData = (long) 18E+2;
		System.out.println("longData = (long) 18E+2 : " + longData);

		longData = (long) 18E-2;
		System.out.println("longData = (long) 18E-2 : " + longData);

		longData = (long) Double.MAX_VALUE;
		System.out.println("longData = (long) Double.MAX_VALUE : " + longData);

		longData = (long) Double.MIN_VALUE;
		System.out.println("longData = (long) Double.MIN_VALUE : " + longData);

		floatData = 1000;
		System.out.println("floatData = 1000 : " + floatData);

		floatData = 1000L;
		System.out.println("floatData = 1000L : " + floatData);

		floatData = (float) 3.5d;
		System.out.println("floatData = (float) 3.5d : " + floatData);

		floatData = (float) Double.MAX_VALUE;
		System.out.println("floatData = (float) Double.MAX_VALUE : " + floatData);

		floatData = (float) Double.MIN_VALUE;
		System.out.println("floatData = (float) Double.MIN_VALUE : " + floatData);

		doubleData = 3.5f;
		System.out.println("doubleData = 3.5f : " + doubleData);

		doubleData = Float.MAX_VALUE;
		System.out.println("doubleData = Float.MAX_VALUE : " + doubleData);

		doubleData = Float.MIN_VALUE;
		System.out.println("doubleData = Float.MIN_VALUE : " + doubleData);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LiteralMain literal = new LiteralMain();
		literal.assign();
	}

}
