package ch22.ex22_01;

public class ArrayViewMain {

	public static void showArray(double[] array , int row){
		for(int i=0 ; i<row ; i++){
			System.out.printf("%80.80e\n" , array[i]);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		double[] doubleArray = {3.14 , 5.6 , 10 , 15332342423443.232 , 0.002213120123132102131231};
		ArrayViewMain.showArray(doubleArray , doubleArray.length);
	}

}
