package GUI2_4;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class ClockWindow extends JFrame implements Runnable{

	private static final String WINDOW_TITLE = "Clock";
	public static final String SETTING_MENU = "Setting";
	public static final String PROPERTY_MENUITEM = "Property";

	private PropertyDialog propertyDialog = null;
	private JMenuBar menuBar = null;
	private JMenu menuSetting = null;
	private JMenuItem menuProperty = null;

	private Dimension dim;
	private Image imgBuf;
	private Graphics gBuf;

	private MyTimeFormat myTimeFormat = new MyTimeFormat("Arial" , 100 , Color.BLACK);
	private MyBackCampus myBackCampus = new MyBackCampus(Color.WHITE);
	private MyTime myTime = new MyTime();
	private boolean doBlink = false;
	private boolean alternativaFlag = true;
	private boolean iconified = false;

	private static final int MAX_WINDOW_SIZE_X = 1100;
	private static final int MAX_WINDOW_SIZE_Y = 550;
	private static final int MIN_WINDOW_SIZE_X = 300;
	private static final int MIN_WINDOW_SIZE_Y = 150;

	private Preferences user = Preferences.userRoot();
	private static final String KEY_FONT = "naitoh_font_2_1";
	private static final String KEY_FONT_SIZE = "naitoh_fontsize_2_1";
	private static final String KEY_LITERAL_COLOR = "naitoh_literal_color_2_1";
	private static final String KEY_BACK_COLOR = "naitoh_back_color_2_1";
	private static final String KEY_DO_BLINK = "naitoh_do_blink_2_1";
	private static final String KEY_WINDOW_POS_X = "naitoh_pos_x_2_1";
	private static final String KEY_WINDOW_POS_Y = "naitoh_pos_y_2_1";
	private static final String KEY_WINDOW_SIZE_X = "naitoh_size_x_2_1";
	private static final String KEY_WINDOW_SIZE_Y = "naitoh_size_y_2_1";


	public ClockWindow(){
    	setTitle(WINDOW_TITLE);
        addWindowListener(new WindowAction(this));

        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        menuSetting = new JMenu(SETTING_MENU);
        menuBar.add(menuSetting);

        menuProperty = new JMenuItem(PROPERTY_MENUITEM);
        menuSetting.add(menuProperty);
        menuProperty.addActionListener(new WindowAction(this));

        this.getContentPane().add(new ClockPanel());

        addComponentListener(new WindowAction(this));

        myTimeFormat.setCurrentFont(user.get(KEY_FONT, "Arial"));
        myTimeFormat.setCurrentFontSize(user.getInt(KEY_FONT_SIZE, 100));
        myTimeFormat.setCurrentLiteralColorWithString(user.get(KEY_LITERAL_COLOR, "Black"));
        myBackCampus.setCurrentBackColorWithString(user.get(KEY_BACK_COLOR, "White"));
        doBlink = user.getBoolean(KEY_DO_BLINK, false);
		setLocation((int)user.getDouble(KEY_WINDOW_POS_X, 0) , (int)user.getDouble(KEY_WINDOW_POS_Y, 0));
		setSize((int)user.getDouble(KEY_WINDOW_SIZE_X, 500) , (int)user.getDouble(KEY_WINDOW_SIZE_Y, 250));

        setVisible(true);
    }

	@Override
	public void run() {
        while(true){
	    	if(iconified){
	    		setTitle(getTime());
	    	}else{
	    		setTitle(WINDOW_TITLE);
	    	}

        	if(propertyDialog != null){
               	doBlink = propertyDialog.getDoBlink();
        	}
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

	private void adjustFontSizeBasedWindowSize(int windowWidth , int windowHeight){
		if(windowWidth < MIN_WINDOW_SIZE_X){
			windowWidth = MIN_WINDOW_SIZE_X + 1;
		}
		if(windowHeight < MIN_WINDOW_SIZE_Y){
			windowHeight = MIN_WINDOW_SIZE_Y + 1;
		}

		if(windowWidth > MAX_WINDOW_SIZE_X){
			windowWidth = MAX_WINDOW_SIZE_X  - 1;
		}
		if(windowHeight > MAX_WINDOW_SIZE_Y){
			windowHeight = MAX_WINDOW_SIZE_Y - 1;
		}
		windowHeight = windowWidth * 1/2;

		int[] fontSizeSet = myTimeFormat.getFontSizeSet();
		double xInterval = (double)(MAX_WINDOW_SIZE_X - MIN_WINDOW_SIZE_X) / fontSizeSet.length;
		double yInterval = (double)(MAX_WINDOW_SIZE_Y - MIN_WINDOW_SIZE_Y) / fontSizeSet.length;

		int xIndex = (int)((windowWidth - MIN_WINDOW_SIZE_X) / xInterval);
		int yIndex = (int)((windowHeight - MIN_WINDOW_SIZE_Y) / yInterval);

		myTimeFormat.setCurrentFontSizeByIndex((xIndex < yIndex) ? xIndex : yIndex);
		setSize(windowWidth , windowHeight);
	}

	private void adjustWindowSizeBasedFontSize(){
		int fontSizeIndex = myTimeFormat.getCurrentFontSizeByIndex();

		int[] fontSizeSet = myTimeFormat.getFontSizeSet();
		double xInterval = (double)(MAX_WINDOW_SIZE_X - MIN_WINDOW_SIZE_X) / fontSizeSet.length;
		double yInterval = (double)(MAX_WINDOW_SIZE_Y - MIN_WINDOW_SIZE_Y) / fontSizeSet.length;

		int windowWidth = (int) (MIN_WINDOW_SIZE_X + xInterval * fontSizeIndex);
		setSize(windowWidth , windowWidth * 1/2);
	}

	private String getTime(){
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
    	return currentTime;
	}


	private class ClockPanel extends JPanel{
		@Override
	    public void paintComponent(Graphics g){
			super.paintComponent(g);
	    	adjustWindowSizeBasedFontSize();

	        dim = getSize();
	        imgBuf = createImage(dim.width, dim.height);
	    	gBuf = imgBuf.getGraphics();

	    	gBuf.setColor(myBackCampus.getCurrentBackColor());
	    	gBuf.fillRect(0, 0, dim.width - 1, dim.height - 1);

	    	gBuf.setColor(myTimeFormat.getCurrentLiteralColor());
	    	gBuf.setFont(new Font(myTimeFormat.getCurrentFont() , Font.BOLD , myTimeFormat.getCurrentFontSize()));

	    	String currentTime = getTime();
	    	FontMetrics fm = gBuf.getFontMetrics();
	    	int strWidth = fm.stringWidth(myTime.getCurrentTime());
	        gBuf.drawString(currentTime , dim.width/2-strWidth/2 , (dim.height-10-fm.getAscent()-fm.getDescent())/2+fm.getAscent());
	        g.drawImage(imgBuf , 0 , 0 ,this);
	    }
	}

	class WindowAction extends WindowAdapter implements ActionListener , ComponentListener{
		private JFrame owner = null;

		public WindowAction(ClockWindow owner){
			this.owner = owner;
		}

		public void windowClosing(WindowEvent e){
			user.put(KEY_FONT, myTimeFormat.getCurrentFont());
			user.putInt(KEY_FONT_SIZE, myTimeFormat.getCurrentFontSize());
			user.put(KEY_LITERAL_COLOR, myTimeFormat.getCurrentLiteralColorWithString());
			user.put(KEY_BACK_COLOR, myBackCampus.getCurrentBackColorWithString());
			user.putBoolean(KEY_DO_BLINK, doBlink);
			user.putDouble(KEY_WINDOW_POS_X, owner.getLocation().getX());
			user.putDouble(KEY_WINDOW_POS_Y, owner.getLocation().getY());
			user.putDouble(KEY_WINDOW_SIZE_X, owner.getSize().getWidth());
			user.putDouble(KEY_WINDOW_SIZE_Y, owner.getSize().getHeight());
			try {
				user.flush();
			} catch (BackingStoreException e1) {
				e1.printStackTrace();
			}
			System.exit(0);
		}

		public void windowDeiconified(WindowEvent e){
			iconified = false;
		}

		public void windowIconified(WindowEvent e){
			iconified = true;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			int beforeFontSize = myTimeFormat.getCurrentFontSize();
			Object o = e.getSource();
			if(e.getSource() == menuProperty){
				propertyDialog = new PropertyDialog(owner, myTimeFormat, myBackCampus, doBlink);
				propertyDialog.setVisible(true);
				if(beforeFontSize != myTimeFormat.getCurrentFontSize()){
					adjustWindowSizeBasedFontSize();
				}
			}
		}

		@Override
		public void componentResized(ComponentEvent e) {
	        dim = getSize();
			adjustFontSizeBasedWindowSize(dim.width , dim.height);
		}

		@Override
		public void componentMoved(ComponentEvent e) {

		}

		@Override
		public void componentShown(ComponentEvent e) {

		}

		@Override
		public void componentHidden(ComponentEvent e) {
		}
	}
}
