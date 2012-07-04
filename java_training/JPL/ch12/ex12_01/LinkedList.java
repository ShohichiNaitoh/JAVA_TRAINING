package ch12.ex12_01;

public class LinkedList<E> {
	private E element = null;
	private LinkedList<E> next = null;

	public LinkedList(E element){
		this.element = element;
		next = null;
	}

	public LinkedList(E element , LinkedList<E> next){
		this.element = element;
		this.next = next;
	}

	public E getElement(){
		return element;
	}

	public void setElement(E element){
		this.element = element;
	}

	public LinkedList<E> getNext(){
		return next;
	}

	public void setNext(LinkedList<E> next){
		this.next = next;
	}

	public LinkedList<E> find(E target) throws ObjectNotFoundException{
		if(element.equals(target)){
			return this;
		}else{
			if(next != null){
				return next.find(target);
			}
		}
		throw new ObjectNotFoundException(target + " not found.");
	}

}
