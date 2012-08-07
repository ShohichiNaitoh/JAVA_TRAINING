package ch14.ex14_02;

public class PrintServerTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//正しい呼び方
		System.out.println("正しい呼び方");
		PrintServer printServer = new PrintServer();

		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		//誤った呼び方
		System.out.println("\n誤った呼び方");
		printServer.run();
	}

}
