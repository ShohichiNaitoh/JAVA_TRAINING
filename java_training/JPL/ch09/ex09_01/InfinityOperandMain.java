package ch09.ex09_01;

public class InfinityOperandMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Double POSITIVE_INFINITY = Double.POSITIVE_INFINITY;
		Double NEGATIVE_INFINITY = Double.NEGATIVE_INFINITY;

		System.out.println("�� + �� = " + (POSITIVE_INFINITY + POSITIVE_INFINITY));
		System.out.println("�� + (-��) = " + (POSITIVE_INFINITY + NEGATIVE_INFINITY));
		System.out.println("(-��) + �� = " + (NEGATIVE_INFINITY + POSITIVE_INFINITY));
		System.out.println("(-��) + (-��) = " + (NEGATIVE_INFINITY + NEGATIVE_INFINITY));
		System.out.println();
		System.out.println("�� - �� = " + (POSITIVE_INFINITY - POSITIVE_INFINITY));
		System.out.println("�� - (-��) = " + (POSITIVE_INFINITY - NEGATIVE_INFINITY));
		System.out.println("(-��) - �� = " + (NEGATIVE_INFINITY - POSITIVE_INFINITY));
		System.out.println("(-��) - (-��) = " + (NEGATIVE_INFINITY - NEGATIVE_INFINITY));
		System.out.println();
		System.out.println("�� * �� = " + (POSITIVE_INFINITY * POSITIVE_INFINITY));
		System.out.println("�� * (-��) = " + (POSITIVE_INFINITY * NEGATIVE_INFINITY));
		System.out.println("(-��) * �� = " + (NEGATIVE_INFINITY * POSITIVE_INFINITY));
		System.out.println("(-��) * (-��) = " + (NEGATIVE_INFINITY * NEGATIVE_INFINITY));
		System.out.println();
		System.out.println("�� / �� = " + (POSITIVE_INFINITY / POSITIVE_INFINITY));
		System.out.println("�� / (-��) = " + (POSITIVE_INFINITY / NEGATIVE_INFINITY));
		System.out.println("(-��) / �� = " + (NEGATIVE_INFINITY / POSITIVE_INFINITY));
		System.out.println("(-��) / (-��) = " + (NEGATIVE_INFINITY / NEGATIVE_INFINITY));
	}

}
