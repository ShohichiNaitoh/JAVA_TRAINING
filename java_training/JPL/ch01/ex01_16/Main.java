package ch01.ex01_16;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		MyUtilities myUtilities = new MyUtilities();
		try {
			myUtilities.getDataSet("aaa");
		} catch (BadDataSetException e) {
			e.printStackTrace();
		}
	}

}
