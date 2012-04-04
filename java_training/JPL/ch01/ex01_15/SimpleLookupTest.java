package ch01.ex01_15;
import java.lang.reflect.Field;

import org.junit.Test;

import junit.framework.TestCase;


public class SimpleLookupTest extends TestCase{

	@Test
	public void testAdd1(){
		SimpleLookup lookup = new SimpleLookup(50);

		for(int i=0 ; i<3 ; i++){
			if(lookup.add("key"+i, i) == null){
				fail();
			}
		}

		System.out.println("testAdd1");
		System.out.println(lookup);
		System.out.println();
	}

	@Test
	public void testAdd2(){
		SimpleLookup lookup = new SimpleLookup(50);

		for(int i=0 ; i<50 ; i++){
			if(lookup.add("key"+i, i) == null){
				fail();
			}
		}

		assertNull(lookup.add("key"+50, 50));
	}

	@Test
	public void testAdd3(){
		SimpleLookup lookup = new SimpleLookup(50);

		for(int i=0 ; i<3 ; i++){
			if(lookup.add("key"+i, i) == null){
				fail();
			}
		}

		lookup.add("key1", "overwriting");

		System.out.println("testAdd3");
		System.out.println(lookup);
		System.out.println();
	}

	@Test
	public void testAddException(){
		SimpleLookup lookup = new SimpleLookup(50);

		try{
			lookup.add("", "aaa");
			fail();
		}catch(Exception e){
			assertTrue(e instanceof IllegalArgumentException);
		}

		try{
			lookup.add(null, "aaa");
			fail();
		}catch(Exception e){
			assertTrue(e instanceof IllegalArgumentException);
		}

		try{
			lookup.add("key1", null);
			fail();
		}catch(Exception e){
			assertTrue(e instanceof IllegalArgumentException);
		}
	}

	@Test
	public void testRemove(){
		SimpleLookup lookup = new SimpleLookup(50);

		for(int i=0 ; i<50 ; i++){
			if(lookup.add("key"+i, i) == null){
				fail();
			}
		}

		lookup.remove("key1");
		lookup.remove("key2");
		lookup.remove("key3");

		System.out.println("testRemove");
		System.out.println(lookup);
		System.out.println();
	}

	@Test
	public void testRemove2(){
		SimpleLookup lookup = new SimpleLookup(50);

		for(int i=0 ; i<40 ; i++){
			if(lookup.add("key"+i, i) == null){
				fail();
			}
		}

		lookup.remove("key1");
		lookup.remove("key2");
		lookup.remove("key3");

		lookup.add("add", "add");
		lookup.add("add2", "add2");
		lookup.add("add3", "add3");
		lookup.add("add4", "add4");

		System.out.println("testRemove2");
		System.out.println(lookup);
		System.out.println();
	}

	@Test
	public void testRemoveException(){
		SimpleLookup lookup = new SimpleLookup(50);

		try{
			lookup.remove("");
			fail();
		}catch(Exception e){
			assertTrue(e instanceof IllegalArgumentException);
		}

		try{
			lookup.remove(null);
			fail();
		}catch(Exception e){
			assertTrue(e instanceof IllegalArgumentException);
		}
	}


	@Test
	public void testFind(){
		SimpleLookup lookup = new SimpleLookup(50);

		for(int i=0 ; i<50 ; i++){
			if(lookup.add("key"+i, i) == null){
				fail();
			}
		}

		for(int i=0 ; i<50 ; i++){
			assertEquals(i, lookup.find("key"+i));
		}
	}

	@Test
	public void testFineException(){
		SimpleLookup lookup = new SimpleLookup(50);

		try{
			lookup.find("");
			fail();
		}catch(Exception e){
			assertTrue(e instanceof IllegalArgumentException);
		}

		try{
			lookup.find(null);
			fail();
		}catch(Exception e){
			assertTrue(e instanceof IllegalArgumentException);
		}
	}
}
