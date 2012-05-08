package ch03.ex03_08;

public class PassengerVehicle extends Vehicle implements Cloneable {

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

	public PassengerVehicle clone() throws CloneNotSupportedException {
		return (PassengerVehicle) super.clone();
	}

}
