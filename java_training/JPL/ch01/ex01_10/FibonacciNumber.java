package ch01.ex01_10;

public class FibonacciNumber {

	private int number = 0;
	private boolean flagEven = false;
	
	public FibonacciNumber(int number){
		this.number = number;
		
		if(number % 2 == 0){
			flagEven = true;
		}else{
			flagEven = false;
		}
	}
	
	public int getNumber(){
		return number;
	}
	
	public boolean isEven(){
		return flagEven;
	}
	
	public String toString(){
		String text = String.valueOf(number);
		if(flagEven){
			text += " *";
		}
		return text;
	}
}
