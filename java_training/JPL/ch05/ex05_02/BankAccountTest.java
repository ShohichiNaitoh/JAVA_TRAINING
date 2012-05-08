package ch05.ex05_02;

import java.lang.reflect.Field;

import org.junit.Test;

import ch05.ex05_02.BankAccount.Action;
import junit.framework.TestCase;

public class BankAccountTest extends TestCase {

	@Test
	public void testNext() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException{
		BankAccount bankAccount = new BankAccount();
		bankAccount.deposit(100);
		bankAccount.withdraw(100);
		bankAccount.deposit(100);

		Action action = null;
		int count = 0;
		do{
			action = bankAccount.history().next();
			if(action != null){
				count++;
				//System.out.println(action.toString());
			}
		}while(action != null);

		assertEquals(3, count);
	}

	@Test
	public void testNext2() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException{
		BankAccount bankAccount = new BankAccount();
		bankAccount.deposit(200);
		bankAccount.withdraw(200);
		bankAccount.deposit(200);
		bankAccount.withdraw(200);
		bankAccount.deposit(200);
		bankAccount.withdraw(200);
		bankAccount.deposit(200);
		bankAccount.withdraw(200);
		bankAccount.deposit(200);
		bankAccount.withdraw(200);

		Action action = null;
		int count = 0;
		do{
			action = bankAccount.history().next();
			if(action != null){
				count++;
				//System.out.println(action.toString());
			}
		}while(action != null);

		assertEquals(10, count);
	}

	@Test
	public void testNext3() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException{
		BankAccount bankAccount = new BankAccount();

		bankAccount.deposit(300);
		bankAccount.deposit(300);
		bankAccount.deposit(300);
		bankAccount.deposit(300);
		bankAccount.deposit(300);
		bankAccount.withdraw(300);
		bankAccount.withdraw(300);
		bankAccount.withdraw(300);
		bankAccount.withdraw(300);
		bankAccount.withdraw(300);
		bankAccount.deposit(1000);
		bankAccount.deposit(1000);
		bankAccount.deposit(1000);
		bankAccount.deposit(1000);
		bankAccount.deposit(1000);

		Action action = null;
		int count = 0;
		do{
			action = bankAccount.history().next();
			if(action != null){
				count++;
				System.out.println(action.toString());
			}
		}while(action != null);

		assertEquals(10, count);
	}
}
