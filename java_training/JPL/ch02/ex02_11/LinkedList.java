package ch02.ex02_11;

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

	public static void main(String[] args) {
		Vehicle vehicle1 = new Vehicle("yamada");
		vehicle1.speed = 100.0;
		vehicle1.angle = 30.0;

		Vehicle vehicle2 = new Vehicle("satoh");
		vehicle2.speed = 50.0;
		vehicle2.angle = 60.0;

		Vehicle vehicle3 = new Vehicle("sasaki");
		vehicle3.speed = 0.0;
		vehicle3.angle = 120.0;

		LinkedList linkedList3 = new LinkedList(vehicle3);
		LinkedList linkedList2 = new LinkedList(vehicle2 , linkedList3);
		LinkedList linkedList1 = new LinkedList(vehicle1 , linkedList2);

		System.out.println(linkedList1);
	}
}
