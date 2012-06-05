package GUI1_2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;

public class ClockWindow extends Frame implements ActionListener , Runnable{

	private Literal literal = new Literal(Literal.FONT.SERIF , Literal.SIZE.FOUTY , Literal.COLOR.BLACK);
	private Campus campus = new Campus(Campus.COLOR.WHITE);

	private int hour = 0;
	private int minute = 0;
	private int second = 0;

	Toolkit tk = getToolkit();
	Dimension dim;
	Image imgBuf;
	Graphics gBuf;

	private MenuBar menuBar;
	private Menu menuSetting;
	private MenuItem menuProperty;

	public static final String WINDOW_TITLE = "時計";
	public static final String SETTING_MENU = "設定";
	public static final String PROPERTY_MENUITEM = "プロパティ";

	public ClockWindow(){
    	setTitle(WINDOW_TITLE);
    	adjustWindowSize();
        setVisible(true);
        setResizable(false);
        addWindowListener(new WindowAction());

        menuBar = new MenuBar();
        setMenuBar(menuBar);

        menuSetting = new Menu(SETTING_MENU);
        menuBar.add(menuSetting);

        menuProperty = new MenuItem(PROPERTY_MENUITEM , new MenuShortcut('O'));
        menuSetting.add(menuProperty);
        menuProperty.addActionListener(this);
	}

    public void paint(Graphics g){
    	adjustWindowSize();
        dim = getSize();
        imgBuf = createImage(dim.width, dim.height);
    	gBuf = imgBuf.getGraphics();

    	gBuf.setColor(campus.getColor());
    	gBuf.fillRect(0, 0, dim.width - 1, dim.height - 1);

    	gBuf.setColor(literal.getColor());
    	gBuf.setFont(new Font(literal.getFont() , Font.BOLD , literal.getSize()));
    	FontMetrics fm = gBuf.getFontMetrics();
    	int strWidth = fm.stringWidth(fillZero(hour)+":"+fillZero(minute)+":"+ fillZero(second));
        gBuf.drawString(fillZero(hour)+":"+fillZero(minute)+":"+ fillZero(second) , dim.width/2-strWidth/2 , ((dim.height-65)/2+65));
        g.drawImage(imgBuf , 0 , 0 ,this);
    }

	public void update(Graphics g) {
		paint(g);
	}

    private String fillZero(int i){
    	if(i < 10){
    		return "0" + i;
    	}else{
    		return "" + i;
    	}
    }

    private void adjustWindowSize(){
    	int literalSize = literal.getSize();
    	setSize(literalSize * 5 , literalSize * 3);
    }

    public void run(){
        while(true){
              hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
              minute = Calendar.getInstance().get(Calendar.MINUTE);
              second= Calendar.getInstance().get(Calendar.SECOND);
              repaint();

              try{
                  Thread.sleep(100);
              }catch(InterruptedException e){
              }
        }
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		int a = 0;
		Object o = e.getSource();
		if(e.getSource() == menuProperty){
			PropertyDialog propertyDialog = new PropertyDialog(this, ClockWindow.PROPERTY_MENUITEM, true , literal , campus);
			propertyDialog.setVisible(true);
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

	class WindowAction extends WindowAdapter{
	    public void windowClosing(WindowEvent e){
	       System.exit(0);
	    }
	}
}





