package ch17.ex17_01;

public class GarbageCollectMain {

	public static final int numberOfObjects = 100000;
	public static String[] strArray = null;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Start...");
		System.out.println("TotalMemory : " + Runtime.getRuntime().totalMemory());
		System.out.println("Free Memory : " + Runtime.getRuntime().freeMemory());

		strArray = new String[numberOfObjects];
		for(int i=0 ; i<numberOfObjects ; i++){
			strArray[i] = new String("aaaa");
		}
		System.out.println("\nCreate Objects...");
		System.out.println("TotalMemory : " + Runtime.getRuntime().totalMemory());
		System.out.println("Free Memory : " + Runtime.getRuntime().freeMemory());

		for(int i=0 ; i<numberOfObjects ; i++){
			strArray[i] = null;
		}
		Runtime.getRuntime().gc();
		System.out.println("\nRun gc...");
		System.out.println("TotalMemory : " + Runtime.getRuntime().totalMemory());
		System.out.println("Free Memory : " + Runtime.getRuntime().freeMemory());
	}

}
