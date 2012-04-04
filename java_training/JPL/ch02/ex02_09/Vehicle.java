package ch02.ex02_09;

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

	public static long getMaxID(){
		if(nextID == 0){
			return nextID;
		}else{
			return nextID - 1;
		}
	}
}
