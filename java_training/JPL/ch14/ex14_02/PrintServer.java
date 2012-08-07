package ch14.ex14_02;

public class PrintServer implements Runnable {
	private final PrintQueue requests = new PrintQueue();
	private Thread workerThread = null;

	public PrintServer(){
		workerThread = new Thread(this);
		workerThread.start();
	}

	public void print(PrintJob job){
		requests.add(job);
	}

	@Override
	public void run(){
		if(workerThread == Thread.currentThread()){
			realPrint(requests.remove());
		}else{
			System.out.println("run failed.");
		}
	}

	private void realPrint(PrintJob job){
		System.out.println("realPrint called.");
	}
}
