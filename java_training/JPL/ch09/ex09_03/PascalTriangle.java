package ch09.ex09_03;

public class PascalTriangle {

	private int[][] pascalsTriangleElements;

	public PascalTriangle(int depth){
		if(depth <= 0){
			throw new IllegalArgumentException("depth must be lager than 0.");
		}
		pascalsTriangleElements = new int[depth][];
		for(int y=0 ; y<pascalsTriangleElements.length ; y++){
			pascalsTriangleElements[y] = new int[y+1];
		}
		init();
	}

	private void init(){
		for(int x=0 ; x<pascalsTriangleElements.length ; x++){
			for(int y=0 ; y<pascalsTriangleElements[x].length ; y++){
				if( (y==0) || (y==pascalsTriangleElements[x].length -1)){
					pascalsTriangleElements[x][y] = 1;
				}else{
					pascalsTriangleElements[x][y] += pascalsTriangleElements[x-1][y-1] + pascalsTriangleElements[x-1][y];
				}
			}
		}
	}

	public void show(){
		for(int[] elements : pascalsTriangleElements){
			for(int element : elements){
				System.out.print(element + " ");
			}
			System.out.println();
		}
	}

	public int[][] getPascalsTriangleElements(){
		return pascalsTriangleElements;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PascalTriangle pascalTriangle = new PascalTriangle(12);
		pascalTriangle.show();
	}

}
