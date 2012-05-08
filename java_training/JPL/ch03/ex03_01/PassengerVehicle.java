package ch03.ex03_01;

public class PassengerVehicle extends Vehicle {

	private int numberOfSeat = 0;
	private int numberOfSittingPerson = 0;


	public PassengerVehicle(String owner){
		super(owner);
	}

	public PassengerVehicle(String owner , int numberOfSeat){
		super(owner);
		setNumberOfSeat(numberOfSeat);
	}


	public int getNumberOfSeat(){
		return numberOfSeat;
	}

	public void setNumberOfSeat(int numberOfSeat){
		if(numberOfSeat < 0){
			throw new IllegalArgumentException("numberOfSeat is smaller than 0.");
		}
		this.numberOfSeat = numberOfSeat;
	}

	public int getNumberOfSittingPerson(){
		return numberOfSittingPerson;
	}

	public void setNumberOfSittingPerson(int numberOfSittingPerson){
		if(numberOfSittingPerson < 0){
			throw new IllegalArgumentException("numberOfSittingPerson is smaller than 0.");
		}else if(numberOfSeat < numberOfSittingPerson){
			throw new IllegalArgumentException("numberOfSittingPerson is over nummberOfSeat.");
		}
		this.numberOfSittingPerson = numberOfSittingPerson;
	}

	public String toString(){
		StringBuffer sb = new StringBuffer(super.toString() + " , ");
		sb.append("seat=" + getNumberOfSeat() + " , sitting person=" + getNumberOfSittingPerson());
		return sb.toString();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PassengerVehicle vehicle1 = new PassengerVehicle("Yamada");
		vehicle1.changeSpeed(100.0);
		vehicle1.turn(380);
		vehicle1.setNumberOfSeat(4);
		vehicle1.setNumberOfSittingPerson(4);
		System.out.println(vehicle1.toString());

		PassengerVehicle vehicle2 = new PassengerVehicle("Satoh" , 30);
		vehicle2.changeSpeed(50);
		vehicle2.turn(PassengerVehicle.TURN_LEFT);
		vehicle2.setNumberOfSittingPerson(15);
		System.out.println(vehicle2.toString());
	}

}
