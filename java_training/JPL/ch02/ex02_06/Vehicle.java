package ch02.ex02_06;

public class Vehicle {

	public double speed = 0.0;
	public double angle = 0.0;
	public String owner = "";
	public long id = 0; 
	
	public static long nextID = 0; 
	
	public void show(){
		System.out.println("id=" + id + 
				" , speed=" + speed + 
				" , angle=" + angle + 
				" , owner=" + owner + '\n');
	}
}