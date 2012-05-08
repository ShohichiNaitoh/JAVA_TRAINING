package gui1_1l;

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
    	setSize(200, 100);
        setVisible(true);
        addWindowListener(new WA());
	}

    public void paint(Graphics g){
    	/*
    	g.setFont(Font.)
    	g.setColor(Color.red);
    	*/
        g.drawString(hour+":"+minute+":"+second,50,59);
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