package ch14.ex14_09;

public class ThreadGroupDisplay{

	public void displayThreadLayerPeriodic(final ThreadGroup threadGroup){
		if(threadGroup == null){
			throw new IllegalArgumentException("threadGroup must not be null.");
		}

		new Thread(new Runnable(){
			@Override
			public void run() {
				while(true){
					System.out.println("------------------------------------");
					displayThreadLayerInternal(threadGroup);
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	private void displayThreadLayerInternal(ThreadGroup threadGroup){
		Thread[] threadList = new Thread[10];
		threadGroup.enumerate(threadList, false);
		System.out.println("ThreadGroup Name : " + threadGroup.getName() + " include fllowing Threads.");
		for(Thread thread : threadList){
			if(thread != null){
				System.out.println("\t" + "Thread Name : " + thread.getName());
			}
		}
		System.out.println();

		ThreadGroup parentGroup = threadGroup.getParent();
		if(parentGroup == null){
			return;
		}
		displayThreadLayerInternal(parentGroup);
	}
}
