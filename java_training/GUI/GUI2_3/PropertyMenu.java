package GUI2_3;


import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class PropertyMenu implements ActionListener , ItemListener{

	private JPopupMenu popupMenu = null;

	JMenu fontMenu = null;
	JCheckBoxMenuItem[] fontMenuItemSet = null;

	JMenu fontSizeMenu = null;
	JCheckBoxMenuItem[] fontSizeMenuItemSet = null;

	JMenu fontColorMenu = null;
	JCheckBoxMenuItem[] fontColorMenuItemSet = null;

	JMenu backColorMenu = null;
	JCheckBoxMenuItem[] backColorMenuItemSet = null;

	JMenu blinkMenu= null;
	JCheckBoxMenuItem[] blinkMenuItemSet = null;
	boolean doBlink = false;

	JMenuItem exitMenu = null;

	private MyFontFormat fontFormat = null;
	private MyCampus campus = null;


	public PropertyMenu(ClockWindow com , MyFontFormat fontFormat , MyCampus campus , boolean doBlink){
		popupMenu = new JPopupMenu();
		this.fontFormat = fontFormat;
		this.campus = campus;
		this.doBlink = doBlink;


	    fontMenu = new JMenu("Font");
	    String[] fontSet = this.fontFormat.getFontSet();
	    fontMenuItemSet = new JCheckBoxMenuItem[fontSet.length];
	    for(int i=0 ; i<fontMenuItemSet.length ; i++){
	    	fontMenuItemSet[i] = new JCheckBoxMenuItem(fontSet[i]);
	    	fontMenuItemSet[i].addItemListener(this);
	    	fontMenu.add(fontMenuItemSet[i]);
	    }
	    popupMenu.add(fontMenu);


	    fontSizeMenu = new JMenu("FontSize");
	    int[] fontSizeSet = this.fontFormat.getFontSizeSet();
	    fontSizeMenuItemSet = new JCheckBoxMenuItem[fontSizeSet.length];
	    for(int i=0 ; i<fontSizeMenuItemSet.length ; i++){
	    	fontSizeMenuItemSet[i] = new JCheckBoxMenuItem(String.valueOf(fontSizeSet[i]));
	    	fontSizeMenuItemSet[i].addItemListener(this);
	    	fontSizeMenu.add(fontSizeMenuItemSet[i]);
	    }
	    popupMenu.add(fontSizeMenu);

	    fontColorMenu = new JMenu("FontColor");
	    String[] fontColorNameSet = this.fontFormat.getFontColorSetWithString();
	    fontColorMenuItemSet = new JCheckBoxMenuItem[fontColorNameSet.length];
	    for(int i=0 ; i<fontColorMenuItemSet.length ; i++){
	    	fontColorMenuItemSet[i] = new JCheckBoxMenuItem(fontColorNameSet[i].toString());
	    	fontColorMenuItemSet[i].addItemListener(this);
	    	fontColorMenu.add(fontColorMenuItemSet[i]);
	    }
	    popupMenu.add(fontColorMenu);


	    backColorMenu = new JMenu("BackColor");
	    String[] backColorNameSet = this.campus.getBackColorSetWithString();
	    backColorMenuItemSet = new JCheckBoxMenuItem[backColorNameSet.length];
	    for(int i=0 ; i<backColorMenuItemSet.length ; i++){
	    	backColorMenuItemSet[i] = new JCheckBoxMenuItem(backColorNameSet[i].toString());
	    	backColorMenuItemSet[i].addItemListener(this);
	    	backColorMenu.add(backColorMenuItemSet[i]);
	    }
	    popupMenu.add(backColorMenu);


	    blinkMenu = new JMenu("Blink");
	    blinkMenuItemSet = new JCheckBoxMenuItem[2];
	    blinkMenuItemSet[0] = new JCheckBoxMenuItem("On");
	    blinkMenuItemSet[0].addItemListener(this);
	    blinkMenu.add(blinkMenuItemSet[0]);
	    blinkMenuItemSet[1] = new JCheckBoxMenuItem("Off");
	    blinkMenuItemSet[1].addItemListener(this);
	    blinkMenu.add(blinkMenuItemSet[1]);
	    popupMenu.add(blinkMenu);


	    exitMenu = new JMenuItem("Exit");
	    exitMenu.addActionListener(this);
	    popupMenu.add(exitMenu);


	    com.add(popupMenu);
	}

	public void show(Component com , int x , int y){

	    for(int i=0 ; i<fontMenuItemSet.length ; i++){
	    	String currentFont = fontFormat.getCurrentFont();
	    	if(currentFont.equals(fontMenuItemSet[i].getText())){
	    		fontMenuItemSet[i].setState(true);
	    	}else{
	    		fontMenuItemSet[i].setState(false);
	    	}
	    }

	    for(int i=0 ; i<fontSizeMenuItemSet.length ; i++){
	    	String currentFontSize = String.valueOf(fontFormat.getCurrentFontSize());
	    	if(currentFontSize.equals(fontSizeMenuItemSet[i].getText())){
	    		fontSizeMenuItemSet[i].setState(true);
	    	}else{
	    		fontSizeMenuItemSet[i].setState(false);
	    	}
	    }

	    for(int i=0 ; i<fontColorMenuItemSet.length ; i++){
	    	String currentFontColor = fontFormat.getCurrentFontColorWithString();
	    	if(currentFontColor.equals(fontColorMenuItemSet[i].getText())){
	    		fontColorMenuItemSet[i].setState(true);
	    	}else{
	    		fontColorMenuItemSet[i].setState(false);
	    	}
	    }

	    for(int i=0 ; i<backColorMenuItemSet.length ; i++){
	    	String currentBackFont = campus.getCurrentBackColorWithString();
	    	if(currentBackFont.equals(backColorMenuItemSet[i].getText())){
	    		backColorMenuItemSet[i].setState(true);
	    	}else{
	    		backColorMenuItemSet[i].setState(false);
	    	}
	    }

	    if(doBlink == true){
	    	blinkMenuItemSet[0].setState(true);
	    	blinkMenuItemSet[1].setState(false);
	    }else{
	    	blinkMenuItemSet[0].setState(false);
	    	blinkMenuItemSet[1].setState(true);
	    }

		popupMenu.show(com, x, y);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == exitMenu){
			System.exit(0);
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		for(int i=0 ; i<fontMenuItemSet.length ; i++){
			if(e.getSource() == fontMenuItemSet[i]){
				fontFormat.setCurrentFont(((JCheckBoxMenuItem)e.getItem()).getText());
			}
		}

		for(int i=0 ; i<fontSizeMenuItemSet.length ; i++){
			if(e.getSource() == fontSizeMenuItemSet[i]){
				fontFormat.setCurrentFontSize(Integer.parseInt(((JCheckBoxMenuItem)e.getItem()).getText()));
			}
		}

		for(int i=0 ; i<fontColorMenuItemSet.length ; i++){
			if(e.getSource() == fontColorMenuItemSet[i]){
				fontFormat.setCurrentFontColorWithString(((JCheckBoxMenuItem)e.getItem()).getText());
			}
		}

		for(int i=0 ; i<backColorMenuItemSet.length ; i++){
			if(e.getSource() == backColorMenuItemSet[i]){
				campus.setCurrentBackColorWithString(((JCheckBoxMenuItem)e.getItem()).getText());
			}
		}

		if(e.getSource() == blinkMenuItemSet[0]){
			doBlink = true;
		}else if(e.getSource() == blinkMenuItemSet[1]){
			doBlink = false;
		}
	}

	synchronized public boolean getDoBlink(){
		return doBlink;
	}
}
