package ch02.ex02_10;

public class Vehicle {

	public double speed = 0.0;
	public double angle = 0.0;
	public String owner = "";
	public long id = 0;

	public static long nextID = 0;

	public Vehicle(){
		id = nextID++;
	}

	public Vehicle(String owner){
		this();

		if(owner == null){
			this.owner = "";
		}else{
			this.owner = owner;
		}
	}

	public String toString(){
		String message = "id=" + id + " , speed=" + speed + " , angle=" + angle + " , owner=" + owner;
		return message;
	}

	public static void main(String[] args) {
		Vehicle vehicle1 = new Vehicle("yamada");
		System.out.println(vehicle1);

		Vehicle vehicle2 = new Vehicle();
		System.out.println(vehicle2);

		Vehicle vehicle3 = new Vehicle(null);
		System.out.println(vehicle3);
	}

}
