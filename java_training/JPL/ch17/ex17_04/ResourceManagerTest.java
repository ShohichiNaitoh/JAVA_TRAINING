package ch17.ex17_04;

import org.junit.Test;

import junit.framework.TestCase;

public class ResourceManagerTest extends TestCase {

	private static final int numberOfKey = 1000000;
	private Key[] keys = null;

	@Test
	public void test() throws InterruptedException {
		Object[] args = null;
		ResourceManager rm = new ResourceManager();
		keys = new Key[numberOfKey];
		for(int i=0 ; i<numberOfKey ; i++){
			keys[i] = new Key();
			Resource res = rm.getResource(keys[i]);
		}

		System.out.println("Queued Object Number : " + rm.getQueuedObjectNumber());

		for(int i=0 ; i<numberOfKey ; i++){
			keys[i] = null;
		}
		Runtime.getRuntime().gc();

		rm.shutdown();
		System.out.println("\ncalled shutdown...");

		while(rm.isAliveReaperThread()){
			Thread.sleep(10);
			System.out.println("\nQueued Object Number : " + rm.getQueuedObjectNumber());
		}
	}



}

