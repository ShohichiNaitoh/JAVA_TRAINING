package ch03.ex03_11;

public class SimpleSortDouble extends SortDouble {

	static boolean flag = false;

	public SimpleSortDouble(double[] data) {
		super(data);
	}

	@Override
	protected void doSort() {
		for(int i=0 ; i<getDataLength() ; i++){
			for(int j=i+1 ; j<getDataLength() ; j++){
				if(compare(i , j) > 0){
					swap(i , j);
				}
			}
		}

		if(!flag){
			flag = true;
			sort();
		}
	}
}
