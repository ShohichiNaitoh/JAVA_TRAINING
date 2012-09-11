package ch17.ex17_03;

import org.junit.Test;

import junit.framework.TestCase;

public class ResourceManagerTest extends TestCase {

	@Test
	public void test() {
		Object[] args = null;
		ResourceManager rm = new ResourceManager();
		Key key1 = new Key();
		Key key2 = new Key();
		Resource res1 = rm.getResource(key1);
		Resource res2 = rm.getResource(key2);

		res1.use(key1, args);
		res2.use(key2, args);

		try{
			res1.use(key2, args);
			fail();
		}catch (Exception e){
		}

		try{
			res2.use(key1, args);
			fail();
		}catch (Exception e){
		}

		try{
			res1.use(null, args);
			fail();
		}catch (Exception e){
		}

		rm.shutdown();
	}



}

