package ch20.ex20_07;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Attr {

	private String name;
	private Object value = null;

	public Attr(String name){
		this.name = name;
	}

	public Attr(DataInputStream in) throws IOException {
		name = in.readUTF();
		in.readChar();
		value = in.readUTF();
	}

	public Attr(String name , Object value){
		this.name = name;
		this.value = value;
	}

	public String getName(){
		return name;
	}

	public Object getValue(){
		return value;
	}

	public Object setValue(Object newValue){
		Object oldVal = value;
		value = newValue;
		return oldVal;
	}

	public String toString(){
		return name + "='" + value + "'";
	}

	public void write(DataOutputStream out) throws IOException{
		out.writeUTF(name);
		out.writeChar('\t');
		out.writeUTF(value.toString());
	}

}
