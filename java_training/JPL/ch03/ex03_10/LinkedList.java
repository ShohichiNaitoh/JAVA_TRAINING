package ch03.ex03_10;

public class LinkedList implements Cloneable {

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

	public int getCount(){
		int count = 1;
		if(next != null){
			count += next.getCount();
		}
		return count;
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

	public LinkedList clone(){
		LinkedList cloneObj = null;
		try {
			cloneObj = (LinkedList) super.clone();
			if(next != null){
				cloneObj.next = next.clone();
			}
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return cloneObj;
	}
}
