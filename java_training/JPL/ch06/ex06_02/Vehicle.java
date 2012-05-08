package ch06.ex06_02;

public class Vehicle {

	enum DIRECTION{
		LEFT,
		RIGHT,
	}

	private double speed = 0.0;
	private double angle = 0.0;
	private String owner = "";
	private long id = 0;

	private static long nextID = 0;

	private static final double ANGLE_MAX = 360;
	private static final double ANGLE_MIN = 0;


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

	public void changeSpeed(double speed){
		if(speed < 0.0){
			throw new IllegalArgumentException();
		}
		this.speed = speed;
	}

	public void stop(){
		speed = 0.0;
	}

	public void turn(double angle){
		double tempAngle = this.angle + angle;
		do{
			tempAngle = calcAngle(tempAngle);
		}while(tempAngle < ANGLE_MIN || ANGLE_MAX < tempAngle);

		this.angle = tempAngle;
	}

	public void turn(DIRECTION direction){
		if(direction == null){
			throw new IllegalArgumentException("the value of direction is null.");
		}

		switch(direction){
		case LEFT:
			this.turn( -ANGLE_MAX / 4 );
			break;
		case RIGHT:
			this.turn( ANGLE_MAX / 4 );
			break;
		default:
			throw new IllegalArgumentException("the value of direction is unsupported.");
		}
	}

	public String toString(){
		String message = "id=" + id + " , speed=" + speed + " , angle=" + angle + " , owner=" + owner;
		return message;
	}

	private double calcAngle(double angle){
		double newAngle = 0.0;

		if(ANGLE_MAX < angle){
			newAngle = angle - ANGLE_MAX;
		}else if(angle < ANGLE_MIN ){
			newAngle = ANGLE_MAX + angle;
		}else{
			newAngle = angle;
		}

		return newAngle;
	}
}

