package ch09.ex09_04;

public class Operate {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("3 << 2L - 1 : " + (3 << 2L - 1));
		System.out.println("(3L << 2) - 1 : " +  ((3L << 2) - 1));
		System.out.println("10 < 12 == 6 > 17 :" + (10 < 12 == 6 > 17));
		System.out.println("10 << 12 == 6 >> 17 :" + (10 << 12 == 6 >> 17));
		System.out.println("13.5e-1 % Float.POSITIVE_INFINITY : " + (13.5e-1 % Float.POSITIVE_INFINITY));
		System.out.println("Float.POSITIVE_INFINITY + Double.NEGATIVE_INFINITY : " + (Float.POSITIVE_INFINITY + Double.NEGATIVE_INFINITY));
		System.out.println("Double.POSITIVE_INFINITY - Float.NEGATIVE_INFINITY : " + (Double.POSITIVE_INFINITY - Float.NEGATIVE_INFINITY));
		System.out.println("0.0 / -0.0 == -0.0 / 0.0 : " + (0.0 / -0.0 == -0.0 / 0.0));
		System.out.println("Integer.MAX_VALUE + Integer.MIN_VALUE : " + (Integer.MAX_VALUE + Integer.MIN_VALUE));
		System.out.println("Long.MAX_VALUE + 5 : " + (Long.MAX_VALUE + 5));
		System.out.println("(short) 5 * (byte) 10 : " + (short) 5 * (byte) 10);
		int i=3;
		System.out.println("i < 15 ? 1.72e3f : 0  :  " + (i < 15 ? 1.72e3f : 0));
		System.out.println("i++ + i++ + --i : " + (i++ + i++ + --i));
	}

}
