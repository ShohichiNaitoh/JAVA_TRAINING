package GUI1_3;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;


public class ClockWindow extends Window implements Runnable{

	private PropertyMenu propertyMenu = null;

	private Dimension dim;
	private Image imgBuf;
	private Graphics gBuf;

	private MyFontFormat myFontFormat = new MyFontFormat("Arial" , 100 , Color.BLACK);
	private MyCampus myCampus = new MyCampus(Color.WHITE);
	private MyTime myTime = new MyTime();

	private boolean doBlink = false;
	private boolean alternativaFlag = true;


	public ClockWindow(){
		super(null);
		setSize(100,100);
    	setAlwaysOnTop(true);
        setVisible(true);

        propertyMenu = new PropertyMenu(this , myFontFormat , myCampus , doBlink);
        addMouseListener(new MouseAction(this));
	}

    public void paint(Graphics g){
        dim = getSize();
        imgBuf = createImage(dim.width, dim.height);
    	gBuf = imgBuf.getGraphics();

    	gBuf.setColor(myCampus.getCurrentBackColor());
    	gBuf.fillRect(0, 0, dim.width - 1, dim.height - 1);

    	gBuf.setColor(myFontFormat.getCurrentFontColor());
    	gBuf.setFont(new Font(myFontFormat.getCurrentFont() , Font.BOLD , myFontFormat.getCurrentFontSize()));

    	String currentTime = "";
    	if(doBlink == true){
    		if(alternativaFlag == true){
    			currentTime = myTime.getCurrentTime();
    			alternativaFlag = false;
    		}else{
    			currentTime = myTime.getCurrentTimeForBlink();
    			alternativaFlag = true;
    		}
    	}else{
    		currentTime = myTime.getCurrentTime();
    	}

    	FontMetrics fm = gBuf.getFontMetrics();
    	int strWidth = fm.stringWidth(myTime.getCurrentTime());
    	setSize(strWidth + 100 , fm.getAscent() + fm.getDescent() + fm.getLeading() + 100);
        gBuf.drawString(currentTime , dim.width/2-strWidth/2 , dim.height/2+fm.getDescent());
        g.drawImage(imgBuf , 0 , 0 ,this);
    }

	public void update(Graphics g) {
		paint(g);
	}

	@Override
	public void run() {
        while(true){
        	doBlink = propertyMenu.getDoBlink();
            repaint();
            try{
                Thread.sleep(500);
            }catch(InterruptedException e){
            }
        }
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ClockWindow clockWindow = new ClockWindow();
    	Thread thread = new Thread(clockWindow);
    	thread.start();
	}


	class MouseAction extends MouseAdapter{
		private ClockWindow owner = null;

		public MouseAction(ClockWindow owner){
			this.owner = owner;
		}

		public void mouseReleased(MouseEvent e){
	    	if ((e.getModifiers() & java.awt.event.MouseEvent.BUTTON1_MASK) != 0){
	    		owner.setLocation(owner.getLocation().x + e.getX() , owner.getLocation().y + e.getY());
	    	}else if ((e.getModifiers() & java.awt.event.MouseEvent.BUTTON3_MASK) != 0){
	    		propertyMenu.show(e.getComponent() , e.getX(), e.getY());
	    	}
	    }
	}
}
