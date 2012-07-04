package ch12.ex12_01;

public class ObjectNotFoundException extends Exception {

	public ObjectNotFoundException(){
	}

	public ObjectNotFoundException(String details){
		super(details);
	}
}
