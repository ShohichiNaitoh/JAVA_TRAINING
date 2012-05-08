package ch04.ex04_01;

public class Battery implements EnergySource {

	private double quantity = 0.0;

	public Battery(double quantity){
		if(quantity < 0.0){
			throw new IllegalArgumentException("quantity is smaller than 0.");
		}
		this.quantity = quantity;
	}

	@Override
	public boolean empty() {
		if(quantity <= 0.0){
			return true;
		}else{
			return false;
		}
	}

}
