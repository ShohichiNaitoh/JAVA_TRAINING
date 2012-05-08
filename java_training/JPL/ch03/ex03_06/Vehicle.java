package ch03.ex03_06;

public class Vehicle {

	private EnergySource energySource = null;
	private long id = 0;
	private static long nextID = 0;

	public Vehicle(){
	}

	public Vehicle(EnergySource energySource){
		if(energySource == null){
			throw new IllegalArgumentException("EnergySource is null.");
		}
		this.energySource = energySource;

		id = nextID++;
	}

	public boolean start(){
		if(energySource.empty()){
			System.out.println("スタートできません。");
			return false;
		}
		System.out.println("スタートします。");
		return true;
	}

}
