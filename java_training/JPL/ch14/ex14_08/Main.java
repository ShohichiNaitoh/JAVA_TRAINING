package ch14.ex14_08;

/*
 * yieldなし  デッドロック発生 0/10
 * yieldあり  デッドロック発生 5/5
 *
 * デットロックの回避方法
 *
 * 		new Thread(new Runnable(){
 *			public void run() {
 *				synchronized(Friendly.class){
 *					jareth.hug();
 *				}
 *			}
 *		} , "Thread1").start();
 *
 *		new Thread(new Runnable(){
 *			public void run() {
 *				synchronized(Friendly.class){
 *					cory.hug();
 *				}
 *			}
 *		} , "Thread2").start();
 *
 */

public class Main {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final Friendly jareth = new Friendly("jareth");
		final Friendly cory = new Friendly("cory");

		jareth.becomeFriend(cory);
		cory.becomeFriend(jareth);

		new Thread(new Runnable(){
			public void run() {
				jareth.hug();
			}
		} , "Thread1").start();

		new Thread(new Runnable(){
			public void run() {
				cory.hug();
			}
		} , "Thread2").start();
	}

}
