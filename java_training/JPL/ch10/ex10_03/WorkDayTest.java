package ch10.ex10_03;

import org.junit.Test;

import ch10.ex10_03.WorkDay.Week;
import junit.framework.TestCase;

public class WorkDayTest extends TestCase {

	@Test
	public void testJudgeWithIfElse(){
		WorkDay workDay = new WorkDay();
		assertFalse(workDay.judgeWithIfElse(Week.SUNDAY));
		assertTrue(workDay.judgeWithIfElse(Week.MONDAY));
		assertTrue(workDay.judgeWithIfElse(Week.TUESDAY));
		assertTrue(workDay.judgeWithIfElse(Week.WEDNESDAY));
		assertTrue(workDay.judgeWithIfElse(Week.THURSDAY));
		assertTrue(workDay.judgeWithIfElse(Week.FRIDAY));
		assertFalse(workDay.judgeWithIfElse(Week.SATURDAY));
	}

	@Test
	public void testJudgeWithIfElseException(){
		WorkDay workDay = new WorkDay();
		try{
			workDay.judgeWithIfElse(null);
		}catch(Exception e){
			assertTrue(e instanceof IllegalArgumentException);
			assertEquals("oneDay must not be null.", e.getMessage());
		}
	}

	@Test
	public void testJudgeWithSwitch(){
		WorkDay workDay = new WorkDay();
		assertFalse(workDay.judgeWithSwitch(Week.SUNDAY));
		assertTrue(workDay.judgeWithSwitch(Week.MONDAY));
		assertTrue(workDay.judgeWithSwitch(Week.TUESDAY));
		assertTrue(workDay.judgeWithSwitch(Week.WEDNESDAY));
		assertTrue(workDay.judgeWithSwitch(Week.THURSDAY));
		assertTrue(workDay.judgeWithSwitch(Week.FRIDAY));
		assertFalse(workDay.judgeWithSwitch(Week.SATURDAY));
	}

	@Test
	public void testJudgeWithSwitcheException(){
		WorkDay workDay = new WorkDay();
		try{
			workDay.judgeWithSwitch(null);
		}catch(Exception e){
			assertTrue(e instanceof IllegalArgumentException);
			assertEquals("oneDay must not be null.", e.getMessage());
		}
	}

}


