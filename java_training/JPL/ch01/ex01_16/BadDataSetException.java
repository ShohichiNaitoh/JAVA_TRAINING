package ch01.ex01_16;
import java.io.IOException;


public class BadDataSetException extends Exception {
	
	private String setName;
	private IOException e;

	public BadDataSetException(String setName , IOException e){
		this.setName = setName;
		this.e = e;
	}
	
	public String toString(){
		String message = "dataSetName=" + setName + " , ";
		message += "Exception=" + e + '\n';
		return message;
	}
}
