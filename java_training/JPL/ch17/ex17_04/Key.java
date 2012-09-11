package ch17.ex17_04;

public class Key {
	private long id = 0;
	private static long keyId = 0;

	public Key(){
		id = keyId++;
	}

	public long getId(){
		return id;
	}

	/*
	@Override
	public boolean equals(Object obj){
		return true;
	}
	*/
}
