package ch02.ex02_07;

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

	public void show(){
		System.out.println("id=" + id +
				" , speed=" + speed +
				" , angle=" + angle +
				" , owner=" + owner + '\n');
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

		System.out.println("vehicle1");
		vehicle1.show();

		System.out.println("vehicle2");
		vehicle2.show();

		System.out.println("vehicle3");
		vehicle3.show();
	}

}
