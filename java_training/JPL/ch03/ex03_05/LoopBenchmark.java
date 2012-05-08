package ch03.ex03_05;

public class LoopBenchmark extends Benchmark {

	private int numberOfLoop = 0;

	public LoopBenchmark(int numberOfLoop){
		this.numberOfLoop = numberOfLoop;
	}

	@Override
	void benchmark() {
		for(int i=0 ; i<numberOfLoop ; i++){
		}
	}

	public static void main(String[] args){
		if(args == null || args.length != 2){
			throw new IllegalArgumentException("command line arguments are inappropriate.");
		}

		int count = Integer.parseInt(args[0]);
		if(count < 0){
			throw new IllegalArgumentException("args[0] is smaller than 0.");
		}

		int numberOfLoop = Integer.parseInt(args[1]);
		if(numberOfLoop < 0){
			throw new IllegalArgumentException("args[1] is smaller than 0.");
		}

		LoopBenchmark loopBenchmark = new LoopBenchmark(numberOfLoop);
		long time = loopBenchmark.repeat(count);
		System.out.println(count + " methods in " + time + " nanoseconds");
	}

}
