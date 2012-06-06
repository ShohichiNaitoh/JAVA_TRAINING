package ch10.ex10_03;

public class WorkDay {

	enum Week {
		SUNDAY,
		MONDAY,
		TUESDAY,
		WEDNESDAY,
		THURSDAY,
		FRIDAY,
		SATURDAY,
	}

	boolean judgeWithIfElse(Week oneDay){
		if(oneDay == null){
			throw new IllegalArgumentException("oneDay must not be null.");
		}

		if(oneDay == Week.SUNDAY || oneDay == Week.SATURDAY){
			return false;
		}else if(oneDay == Week.MONDAY || oneDay == Week.TUESDAY ||
				 oneDay == Week.WEDNESDAY || oneDay == Week.THURSDAY || oneDay == Week.FRIDAY){
			return true;
		}else{
			//‹N‚±‚è“¾‚È‚¢
			throw new IllegalArgumentException("oneDay is unsopported value.");
		}
	}

	boolean judgeWithSwitch(Week oneDay){
		if(oneDay == null){
			throw new IllegalArgumentException("oneDay must not be null.");
		}

		switch(oneDay){
		case SUNDAY: case SATURDAY:
			return false;
		case MONDAY: case THURSDAY: case WEDNESDAY: case TUESDAY: case FRIDAY:
			return true;
		default:
			//‹N‚±‚è“¾‚È‚¢
			throw new IllegalArgumentException("oneDay is unsopported value.");
		}
	}
}
