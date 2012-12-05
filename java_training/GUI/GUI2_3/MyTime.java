package GUI2_3;

import java.util.Calendar;

public class MyTime {

	private int hour = 0;
	private int minute = 0;
	private int second = 0;

	public String getCurrentTime(){
        hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        minute = Calendar.getInstance().get(Calendar.MINUTE);
        second= Calendar.getInstance().get(Calendar.SECOND);

		return fillZero(hour) + ":" + fillZero(minute) + ":" + fillZero(second);
	}

	public String getCurrentTimeForBlink(){
		return "";
	}

	private String fillZero(int i){
    	if(i < 10){
    		return "0" + i;
    	}else{
    		return "" + i;
    	}
	}
}
