package ch10.ex10_05;

public class CharctorDrawer {

	public void showCharactor(char charA , char charB){
		char largeChar;
		char smallChar;

		if(charA < charB){
			smallChar = charA;
			largeChar = charB;
		}else{
			smallChar = charB;
			largeChar = charA;
		}

		do{
			System.out.print(smallChar);
			smallChar++;
		}while(smallChar <= largeChar);

		System.out.println();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CharctorDrawer cd = new CharctorDrawer();
		cd.showCharactor('A', 'c');
		cd.showCharactor('c', 'A');
		cd.showCharactor('q', 'b');
	}

}
