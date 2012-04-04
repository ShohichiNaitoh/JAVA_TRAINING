package ch02.ex02_08;

public class LinkedList {

	public Object object = null;
	public LinkedList next = null;

	public LinkedList(Object object){
		this.object = object;
		this.next = null;
	}

	public LinkedList(Object object , LinkedList next){
		this.object = object;
		this.next = next;
	}
}
