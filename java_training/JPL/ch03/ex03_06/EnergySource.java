package ch03.ex03_06;

public abstract class EnergySource {

	protected double quantity = 0.0;

	public EnergySource(){
	}

	public EnergySource(double quantity){
		if(quantity < 0.0){
			throw new IllegalArgumentException("quantity is smaller than 0.");
		}
		this.quantity = quantity;
	}

	public abstract boolean empty();
}
