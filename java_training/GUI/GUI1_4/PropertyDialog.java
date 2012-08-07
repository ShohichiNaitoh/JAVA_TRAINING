package GUI1_4;

import java.awt.Button;
import java.awt.CheckboxMenuItem;
import java.awt.Choice;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.Menu;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class PropertyDialog extends Dialog {

	private static final String  DIALOG_TITLE = "Property";
	private static final boolean MODAL = true;

	private static final String FONT_LABEL = "Font";
	private static final String FONT_SIZE_LABEL = "FontSize";
	private static final String LITERAL_COLOR_LABEL = "LiteralColor";
	private static final String BACK_COLOR_LABEL = "BackColor";
	private static final String BLINK_LABEL = "Blink";

	private MyTimeFormat originalMyTimeFormat = null;
	private MyBackCampus originalMyBackCampus = null;
	private boolean originalDoBlink = false;

	private MyTimeFormat selectedMyTimeFormat = null;
	private MyBackCampus selectedMyBackCampus = null;
	private boolean selectedDoBlink = false;

	Choice fontChoice = new Choice();
	Choice fontSizeChoice = new Choice();
	Choice literalColorChoice = new Choice();
	Choice backColorChoice = new Choice();
	Choice blinkChoice = new Choice();

	Button okButton = null;
	Button cancelButton = null;

	Toolkit tk = getToolkit();
	Dimension dim;

	public PropertyDialog(Frame owner , MyTimeFormat fontFormat , MyBackCampus campus , boolean doBlink){
		super(owner , DIALOG_TITLE , MODAL);
		this.originalMyTimeFormat = fontFormat.clone();
		this.originalMyBackCampus = campus.clone();
		this.originalDoBlink = doBlink;

		this.selectedMyTimeFormat = fontFormat;
		this.selectedMyBackCampus = campus;
		this.selectedDoBlink = doBlink;

		setSize(300 , 280);
		dim = tk.getScreenSize();
		setLocation(dim.width / 2 - 200 , dim.height / 2 - 200);
        setResizable(false);
        addWindowListener(new DialogAction());
        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);
        addComponent(this, layout, new Label(""), 1, 0, 1, 1, GridBagConstraints.WEST);

	    String[] fontSet = this.selectedMyTimeFormat.getFontSet();
	    for(int i=0 ; i<fontSet.length ; i++){
	        fontChoice.add(fontSet[i]);
	    }
        fontChoice.select(selectedMyTimeFormat.getCurrentFont());
        Label fontLabel = new Label(FONT_LABEL);
        fontLabel.setAlignment(Label.RIGHT);
	    addComponent(this, layout, fontLabel, 0, 0, 1, 1, GridBagConstraints.EAST);
	    addComponent(this, layout, fontChoice, 2, 0, 2, 1, GridBagConstraints.WEST);
	    fontChoice.addItemListener(new DialogAction());

        int[] fontSizeSet = this.selectedMyTimeFormat.getFontSizeSet();
        for(int i=0 ; i<fontSizeSet.length ; i++){
        	fontSizeChoice.add(String.valueOf(fontSizeSet[i]));
	    }
        fontSizeChoice.select(String.valueOf(selectedMyTimeFormat.getCurrentFontSize()));
        Label fontSizeLabel = new Label(FONT_SIZE_LABEL);
        fontSizeLabel.setAlignment(Label.RIGHT);
	    addComponent(this, layout, fontSizeLabel, 0, 1, 1, 1, GridBagConstraints.EAST);
	    addComponent(this, layout, fontSizeChoice, 2, 1, 2, 1, GridBagConstraints.WEST);
	    fontSizeChoice.addItemListener(new DialogAction());

        String[] fontColorNameSet = this.selectedMyTimeFormat.getLiteralColorSetWithString();
        for(int i=0 ; i<fontColorNameSet.length ; i++){
        	literalColorChoice.add(fontColorNameSet[i]);
	    }
        literalColorChoice.select(selectedMyTimeFormat.getCurrentLiteralColorWithString());
        Label literalColorLabel = new Label(LITERAL_COLOR_LABEL);
        literalColorLabel.setAlignment(Label.RIGHT);
	    addComponent(this, layout, literalColorLabel, 0, 2, 1, 1, GridBagConstraints.EAST);
	    addComponent(this, layout, literalColorChoice, 2, 2, 2, 1, GridBagConstraints.WEST);
	    literalColorChoice.addItemListener(new DialogAction());

        String[] backColorNameSet = this.selectedMyBackCampus.getBackColorSetWithString();
        for(int i=0 ; i<backColorNameSet.length ; i++){
        	backColorChoice.add(backColorNameSet[i]);
	    }
        backColorChoice.select(selectedMyBackCampus.getCurrentBackColorWithString());
        Label backColorLabel = new Label(BACK_COLOR_LABEL);
        backColorLabel.setAlignment(Label.RIGHT);
	    addComponent(this, layout, backColorLabel, 0, 3, 1, 1, GridBagConstraints.EAST);
	    addComponent(this, layout, backColorChoice, 2, 3, 2, 1, GridBagConstraints.WEST);
	    backColorChoice.addItemListener(new DialogAction());

	    blinkChoice.add("ON");
	    blinkChoice.add("OFF");
	    if(selectedDoBlink == true){
	    	blinkChoice.select("ON");
	    }else{
	    	blinkChoice.select("OFF");
	    }
	    Label blinkLabel = new Label(BLINK_LABEL);
	    blinkLabel.setAlignment(Label.RIGHT);
	    addComponent(this, layout, blinkLabel, 0, 4, 1, 1, GridBagConstraints.EAST);
	    addComponent(this, layout, blinkChoice, 2, 4, 2, 1, GridBagConstraints.WEST);
	    blinkChoice.addItemListener(new DialogAction());

	    okButton = new Button(" OK ");
	    okButton.setSize(100 , 50);
        okButton.addActionListener(new DialogAction());
	    addComponent(this, layout, okButton, 2, 5, 1, 1, GridBagConstraints.EAST);

        cancelButton = new Button("Cancel");
        cancelButton.setSize(50 , 50);
        cancelButton.addActionListener(new DialogAction());
	    addComponent(this, layout, cancelButton, 3, 5, 1, 1, GridBagConstraints.EAST);
	}

	private void  addComponent(Container container , GridBagLayout layout ,Component comp , int x , int y , int width , int height , int anchor){
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = width;
        gbc.gridheight = height;
        gbc.weightx=0.5;
        gbc.weighty=0.1;
        gbc.anchor = anchor;
        layout.setConstraints(comp, gbc);
        container.add(comp);
	}

	synchronized public boolean getDoBlink(){
		return selectedDoBlink;
	}

	class DialogAction extends WindowAdapter implements ActionListener , ItemListener{
	    public void windowClosing(WindowEvent e){
	       dispose();
	    }

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == okButton){
				dispose();
			}else if(e.getSource() == cancelButton){
				selectedMyTimeFormat.setCurrentFont(originalMyTimeFormat.getCurrentFont());
				selectedMyTimeFormat.setCurrentFontSize(originalMyTimeFormat.getCurrentFontSize());
				selectedMyTimeFormat.setCurrentLiteralColorWithString(originalMyTimeFormat.getCurrentLiteralColorWithString());
				selectedMyBackCampus.setCurrentBackColorWithString(originalMyBackCampus.getCurrentBackColorWithString());
				selectedDoBlink = originalDoBlink;
				dispose();
			}
		}

		@Override
		public void itemStateChanged(ItemEvent e) {
			if(e.getSource() == fontChoice){
				selectedMyTimeFormat.setCurrentFont((String)e.getItem());
			}else if(e.getSource() == fontSizeChoice){
				selectedMyTimeFormat.setCurrentFontSize(Integer.parseInt((String) e.getItem()));
			}else if(e.getSource() == literalColorChoice){
				selectedMyTimeFormat.setCurrentLiteralColorWithString((String)e.getItem());
			}else if(e.getSource() == backColorChoice){
				selectedMyBackCampus.setCurrentBackColorWithString((String)e.getItem());
			}else if(e.getSource() == blinkChoice){
				if( ((String)e.getItem()).equals("ON")){
					selectedDoBlink = true;
				}else{
					selectedDoBlink = false;
				}
			}
		}
	}
}
