package ch03.ex03_06;

public class GasTank extends EnergySource {

	public GasTank(double quantity){
		super(quantity);
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
