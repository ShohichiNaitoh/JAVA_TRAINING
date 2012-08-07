package ch14.ex14_04;

public class Calculator {
	private static int value = 0;

	public synchronized static void addValue(int addedValue){
		System.out.print(Calculator.value + " + " + addedValue);
		Calculator.value += addedValue;
		System.out.println(" = " + Calculator.value);
	}
}
