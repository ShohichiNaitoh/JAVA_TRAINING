package ch04.ex04_03;

public class LinkedListImpl implements LinkedList , Cloneable{

	private Object object = null;
	private LinkedList next = null;

	public LinkedListImpl(Object object){
		this.object = object;
		this.next = null;
	}

	public LinkedListImpl(Object object , LinkedListImpl next){
		this.object = object;
		this.next = next;
	}

	@Override
	public Object getObject() {
		return object;
	}

	@Override
	public void setObject(Object object) {
		this.object = object;
	}

	@Override
	public LinkedList getNext() {
		return next;
	}

	@Override
	public void setNext(LinkedList next) {
		this.next = next;
	}

	@Override
	public int getCount() {
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

	public LinkedListImpl clone(){
		LinkedListImpl cloneObj = null;
		try {
			cloneObj = (LinkedListImpl) super.clone();
			if(next != null){
				cloneObj.next = ((LinkedListImpl) next).clone();
			}
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return cloneObj;
	}

}
