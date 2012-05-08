package ch04.ex04_01;

public class GasTank implements EnergySource {

	private double quantity = 0.0;

	public GasTank(double quantity){
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
