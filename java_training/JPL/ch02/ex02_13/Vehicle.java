package ch02.ex02_13;

public class Vehicle {

	private double speed = 0.0;
	private double angle = 0.0;
	private String owner = "";
	private long id = 0;

	private static long nextID = 0;

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

	public double getSpeed(){
		return speed;
	}

	public double getAngle(){
		return angle;
	}

	public String getOwner(){
		return owner;
	}

	public void setOwner(String owner){
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
}
