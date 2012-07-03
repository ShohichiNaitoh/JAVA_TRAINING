package GUI1_3;

import java.awt.CheckboxMenuItem;
import java.awt.Color;
import java.awt.Component;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class PropertyMenu implements ActionListener , ItemListener{

	private PopupMenu popupMenu = null;

	Menu fontMenu = null;
	CheckboxMenuItem[] fontMenuItemSet = null;

	Menu fontSizeMenu = null;
	CheckboxMenuItem[] fontSizeMenuItemSet = null;

	Menu fontColorMenu = null;
	CheckboxMenuItem[] fontColorMenuItemSet = null;

	Menu backColorMenu = null;
	CheckboxMenuItem[] backColorMenuItemSet = null;

	Menu blinkMenu= null;
	CheckboxMenuItem[] blinkMenuItemSet = null;
	boolean doBlink = false;

	MenuItem exitMenu = null;

	private MyFontFormat fontFormat = null;
	private MyCampus campus = null;


	public PropertyMenu(Component com , MyFontFormat fontFormat , MyCampus campus , boolean doBlink){
		popupMenu = new PopupMenu();
		this.fontFormat = fontFormat;
		this.campus = campus;
		this.doBlink = doBlink;


	    fontMenu = new Menu("Font");
	    String[] fontSet = this.fontFormat.getFontSet();
	    fontMenuItemSet = new CheckboxMenuItem[fontSet.length];
	    for(int i=0 ; i<fontMenuItemSet.length ; i++){
	    	fontMenuItemSet[i] = new CheckboxMenuItem(fontSet[i]);
	    	fontMenuItemSet[i].addItemListener(this);
	    	fontMenu.add(fontMenuItemSet[i]);
	    }
	    popupMenu.add(fontMenu);


	    fontSizeMenu = new Menu("FontSize");
	    int[] fontSizeSet = this.fontFormat.getFontSizeSet();
	    fontSizeMenuItemSet = new CheckboxMenuItem[fontSizeSet.length];
	    for(int i=0 ; i<fontSizeMenuItemSet.length ; i++){
	    	fontSizeMenuItemSet[i] = new CheckboxMenuItem(String.valueOf(fontSizeSet[i]));
	    	fontSizeMenuItemSet[i].addItemListener(this);
	    	fontSizeMenu.add(fontSizeMenuItemSet[i]);
	    }
	    popupMenu.add(fontSizeMenu);

	    fontColorMenu = new Menu("FontColor");
	    String[] fontColorNameSet = this.fontFormat.getFontColorSetWithString();
	    fontColorMenuItemSet = new CheckboxMenuItem[fontColorNameSet.length];
	    for(int i=0 ; i<fontColorMenuItemSet.length ; i++){
	    	fontColorMenuItemSet[i] = new CheckboxMenuItem(fontColorNameSet[i].toString());
	    	fontColorMenuItemSet[i].addItemListener(this);
	    	fontColorMenu.add(fontColorMenuItemSet[i]);
	    }
	    popupMenu.add(fontColorMenu);


	    backColorMenu = new Menu("BackColor");
	    String[] backColorNameSet = this.campus.getBackColorSetWithString();
	    backColorMenuItemSet = new CheckboxMenuItem[backColorNameSet.length];
	    for(int i=0 ; i<backColorMenuItemSet.length ; i++){
	    	backColorMenuItemSet[i] = new CheckboxMenuItem(backColorNameSet[i].toString());
	    	backColorMenuItemSet[i].addItemListener(this);
	    	backColorMenu.add(backColorMenuItemSet[i]);
	    }
	    popupMenu.add(backColorMenu);


	    blinkMenu = new Menu("Blink");
	    blinkMenuItemSet = new CheckboxMenuItem[2];
	    blinkMenuItemSet[0] = new CheckboxMenuItem("On");
	    blinkMenuItemSet[0].addItemListener(this);
	    blinkMenu.add(blinkMenuItemSet[0]);
	    blinkMenuItemSet[1] = new CheckboxMenuItem("Off");
	    blinkMenuItemSet[1].addItemListener(this);
	    blinkMenu.add(blinkMenuItemSet[1]);
	    popupMenu.add(blinkMenu);


	    exitMenu = new MenuItem("Exit");
	    exitMenu.addActionListener(this);
	    popupMenu.add(exitMenu);


	    com.add(popupMenu);
	}

	public void show(Component com , int x , int y){

	    for(int i=0 ; i<fontMenuItemSet.length ; i++){
	    	String currentFont = fontFormat.getCurrentFont();
	    	if(currentFont.equals(fontMenuItemSet[i].getLabel())){
	    		fontMenuItemSet[i].setState(true);
	    	}else{
	    		fontMenuItemSet[i].setState(false);
	    	}
	    }

	    for(int i=0 ; i<fontSizeMenuItemSet.length ; i++){
	    	String currentFontSize = String.valueOf(fontFormat.getCurrentFontSize());
	    	if(currentFontSize.equals(fontSizeMenuItemSet[i].getLabel())){
	    		fontSizeMenuItemSet[i].setState(true);
	    	}else{
	    		fontSizeMenuItemSet[i].setState(false);
	    	}
	    }

	    for(int i=0 ; i<fontColorMenuItemSet.length ; i++){
	    	String currentFontColor = fontFormat.getCurrentFontColorWithString();
	    	if(currentFontColor.equals(fontColorMenuItemSet[i].getLabel())){
	    		fontColorMenuItemSet[i].setState(true);
	    	}else{
	    		fontColorMenuItemSet[i].setState(false);
	    	}
	    }

	    for(int i=0 ; i<backColorMenuItemSet.length ; i++){
	    	String currentBackFont = campus.getCurrentBackColorWithString();
	    	if(currentBackFont.equals(backColorMenuItemSet[i].getLabel())){
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
				fontFormat.setCurrentFont((String)e.getItem());
			}
		}

		for(int i=0 ; i<fontSizeMenuItemSet.length ; i++){
			if(e.getSource() == fontSizeMenuItemSet[i]){
				fontFormat.setCurrentFontSize(Integer.parseInt((String) e.getItem()));
			}
		}

		for(int i=0 ; i<fontColorMenuItemSet.length ; i++){
			if(e.getSource() == fontColorMenuItemSet[i]){
				fontFormat.setCurrentFontColorWithString((String)e.getItem());
			}
		}

		for(int i=0 ; i<backColorMenuItemSet.length ; i++){
			if(e.getSource() == backColorMenuItemSet[i]){
				campus.setCurrentBackColorWithString((String)e.getItem());
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
