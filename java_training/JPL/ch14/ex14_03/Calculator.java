package ch14.ex14_03;

public class Calculator {
	private int value = 0;

	public synchronized void addValue(int addedValue){
		System.out.print(value + " + " + addedValue);
		value += addedValue;
		System.out.println(" = " + value);
	}
}
