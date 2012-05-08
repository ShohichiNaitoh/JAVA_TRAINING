package ch04.ex04_02;

abstract class SortDouble implements SortHarness<Double> {

	private double[] values;
	private double[] originalValues;
	private final SortMetrics curMetrics = new SortMetrics();

	public SortDouble(double[] data){
		values = data;
		originalValues = values.clone();
	}

	public final SortMetrics sort(){
		curMetrics.init();
		for(int i=0 ; i<values.length ; i++){
			values[i] = originalValues[i];
		}

		doSort();
		return getMetrics();
	}

	public final SortMetrics getMetrics(){
		return curMetrics.clone();
	}

	protected final int getDataLength(){
		return values.length;
	}

	@Override
	public final Double probe(int i) {
		curMetrics.probeCnt++;
		return values[i];
	}

	@Override
	public final int compare(int i, int j) {
		curMetrics.compareCnt++;
		Double d1 = values[i];
		Double d2 = values[j];
		if(d1.compareTo(d2) == 0){
			return 0;
		}else if(d1.compareTo(d2) < 0){
			return -1;
		}else{
			return 1;
		}
	}

	@Override
	public void swap(int i, int j) {
		curMetrics.swapCnt++;
		double tmp = values[i];
		values[i] = values[j];
		values[j] = tmp;
	}

	protected abstract void doSort();
}
