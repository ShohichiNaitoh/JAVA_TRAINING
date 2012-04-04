package ch02.ex02_14;
public class LinkedList {

	private Object object = null;
	private LinkedList next = null;

	public LinkedList(Object object){
		this.object = object;
		this.next = null;
	}

	public LinkedList(Object object , LinkedList next){
		this.object = object;
		this.next = next;
	}

	public Object getObject(){
		return object;
	}

	public void setObject(Object object){
		this.object = object;
	}

	public LinkedList getNext(){
		return next;
	}

	public void setNext(LinkedList next){
		this.next = next;
	}

	public String toString(){
		String message = "";
		if(object != null){
			message = object.toString() + "\n";
		}
		if(next != null){
			message += next.toString();
		}
		return message;
	}
}
