package ch01.ex01_08;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Point point1 = new Point();
		point1.x = 1.0;
		point1.y = 2.0;
		
		Point point2 = new Point();
		point2.x = 10.0;
		point2.y = 20.0;
		
		point1.setPoint(point2);
		System.out.println("point1 : " + "x=" + point1.x + " , y=" + point1.y);
		System.out.println("point2 : " + "x=" + point2.x + " , y=" + point2.y);
	}

}
