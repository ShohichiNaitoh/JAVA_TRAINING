package gui1_1;

import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;

public class Clock extends Frame implements Runnable{

	private int hour = 0;
	private int minute = 0;
	private int second = 0;
	private static final String title = "Clock";

	public Clock(){
    	setTitle(title);
    	setSize(240, 160);
        setVisible(true);
        setResizable(false);
        addWindowListener(new WA());
	}

    public void paint(Graphics g){
    	g.setFont(new Font("Serif" , Font.BOLD , 40));
    	g.setColor(Color.black);

        g.drawString(fillZero(hour)+":"+fillZero(minute)+":"+ fillZero(second) ,40,100);
    }

    private String fillZero(int i){
    	if(i < 10){
    		return "0" + i;
    	}else{
    		return "" + i;
    	}
    }

    public void run(){
        while(true){
              hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
              minute = Calendar.getInstance().get(Calendar.MINUTE);
              second= Calendar.getInstance().get(Calendar.SECOND);
              repaint();

              try{
                  Thread.sleep(1000);
              }catch(InterruptedException e){
              }
        }
    }

    public static void main(String args[]){
    	Clock clock = new Clock();
    	Thread thread = new Thread(clock);
    	thread.start();
    }
}


class WA extends WindowAdapter
{
    public void windowClosing(WindowEvent e){
       System.exit(0);
    }
}