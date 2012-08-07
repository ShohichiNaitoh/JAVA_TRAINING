package ch14.ex14_05;

public class Calculator {
	private static int value = 0;

	public synchronized static void addValue(int addedValue){
		System.out.print(Calculator.value + " + " + addedValue);
		Calculator.value += addedValue;
		System.out.println(" = " + Calculator.value);
	}

	public static void subtractValue(int subtractedValue){
		synchronized(Calculator.class){
			System.out.print(Calculator.value + " - " + subtractedValue);
			Calculator.value -= subtractedValue;
			System.out.println(" = " + Calculator.value);
		}
	}
}
