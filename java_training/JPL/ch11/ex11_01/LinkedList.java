package ch11.ex11_01;

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
}
