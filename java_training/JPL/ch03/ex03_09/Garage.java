package ch03.ex03_09;

public class Garage implements Cloneable {

	private int capacity = 0;
	private Vehicle[] vehicles = null;

	public Garage(int capacity){
		if(capacity <= 0){
			throw new IllegalArgumentException("capacity is smaller than 0.");
		}

		this.capacity = capacity;
		vehicles = new Vehicle[capacity];
		for(int i=0 ; i<vehicles.length ; i++){
			vehicles[i] = null;
		}
	}

	public boolean park(int number , Vehicle vehicle){
		if(number < 0 || capacity-1 < number){
			throw new IllegalArgumentException("number is out of range.");
		}

		if(vehicle == null){
			throw new IllegalArgumentException("vehicle is null.");
		}

		if(vehicles[number] == null){
			vehicles[number] = vehicle;
			return true;
		}else{
			return false;
		}
	}

	public boolean departure(int number){
		if(number < 0 || capacity-1 < number){
			throw new IllegalArgumentException("number is out of range.");
		}

		if(vehicles[number] != null){
			vehicles[number] = null;
			return true;
		}else{
			return false;
		}
	}

	public Vehicle[] getVehicles(){
		return vehicles;
	}

	public Vehicle getVehicle(int number){
		return vehicles[number];
	}

	public Garage clone(){
		Garage cloneObj = null;
		try {
			cloneObj = (Garage) super.clone();

			cloneObj.vehicles = new Vehicle[this.vehicles.length];
			for(int i=0 ; i<this.vehicles.length ; i++){
				if(this.vehicles[i] == null){
					cloneObj.vehicles[i] = null;
				}else{
					cloneObj.vehicles[i] = this.vehicles[i].clone();
				}
			}
		}catch (CloneNotSupportedException e) {
		}

		return cloneObj;
	}

	public String toString(){
		StringBuffer sb = new StringBuffer();

		for(int i=0 ; i<vehicles.length ; i++){
			sb.append("Area " + i);
			sb.append("\n");

			if(vehicles[i] == null){
				sb.append("no vehicle.");
			}else{
				sb.append(vehicles[i].toString());
			}
			sb.append("\n\n");
		}

		return sb.toString();
	}


	public static void main(String[] args) {
		Garage garage = new Garage(10);

		Vehicle vehicle1 = new Vehicle("Satoh");
		vehicle1.changeSpeed(20.0);
		vehicle1.turn(20);

		Vehicle vehicle2 = new Vehicle("Tanaka");
		vehicle2.changeSpeed(40.0);
		vehicle2.turn(40);

		Vehicle vehicle3 = new Vehicle("Sasaki");
		vehicle3.changeSpeed(60.0);
		vehicle3.turn(60);

		garage.park(0, vehicle1);
		garage.park(5, vehicle2);
		garage.park(9, vehicle3);

		Garage cloneObj = garage.clone();
		System.out.println(cloneObj);
	}

}
