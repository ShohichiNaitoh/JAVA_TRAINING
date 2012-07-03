package ch11.ex11_02;

public class Attr<E> {
	private final String name;
	private E value = null;

	public Attr(String name){
		this.name = name;
	}

	public Attr(String name , E value){
		this.name = name;
		this.value = value;
	}

	public String getName(){
		return name;
	}

	public E getValue(){
		return value;
	}

	public E setValue(E newValue){
		E oldVal = value;
		value = newValue;
		return oldVal;
	}

	public String toString(){
		return name + "='" + value + "'";
	}
}
