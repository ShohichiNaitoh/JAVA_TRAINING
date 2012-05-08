package ch03.ex03_07;

public class ScreenColorMock extends ScreenColor{

	private boolean equalsResult = false;
	private int hashResult = 0;

	public ScreenColorMock(boolean equalsResult , int hashResult){
		super(null);
		this.equalsResult = equalsResult;
		this.hashResult = hashResult;
	}

	public boolean equals(Object obj){
		return equalsResult;
	}

	public int hashCode(){
		return hashResult;
	}
}
