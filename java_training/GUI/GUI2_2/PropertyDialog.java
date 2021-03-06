package GUI2_2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;


public class PropertyDialog extends JDialog {

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

	JComboBox fontChoice = new JComboBox();
	JComboBox fontSizeChoice = new JComboBox();
	JComboBox literalColorChoice = new JComboBox();
	JComboBox backColorChoice = new JComboBox();
	JComboBox blinkChoice = new JComboBox();

	JLabel chipColorLiteral = new JLabel("　　　");
	JLabel chipColorBack = new JLabel("　　　");

	JButton okButton = null;
	JButton cancelButton = null;

	Toolkit tk = getToolkit();
	Dimension dim;

	public PropertyDialog(JFrame owner , MyTimeFormat fontFormat , MyBackCampus campus , boolean doBlink){
		super(owner , DIALOG_TITLE , MODAL);
		this.originalMyTimeFormat = fontFormat.clone();
		this.originalMyBackCampus = campus.clone();
		this.originalDoBlink = doBlink;

		this.selectedMyTimeFormat = fontFormat;
		this.selectedMyBackCampus = campus;
		this.selectedDoBlink = doBlink;

		dim = tk.getScreenSize();
		setSize(dim.width*1/3 , dim.height*2/5);
		int x = (int)(owner.getLocation().getX()+owner.getWidth()/5);
		int y = (int)(owner.getLocation().getY()+owner.getHeight()*4/5);
		if(x < 0){
			x = 0;
		}else if(dim.width < x+getWidth()){
			x = dim.width - getWidth();
		}
		if(y < 0){
			y = 0;
		}else if(dim.height < y+getHeight()){
			y = dim.height - getHeight();
		}
		setLocation(x , y);
        setResizable(false);
        addWindowListener(new DialogAction());
        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);

	    String[] fontSet = this.selectedMyTimeFormat.getFontSet();
	    for(int i=0 ; i<fontSet.length ; i++){
	        fontChoice.addItem(fontSet[i]);
	    }
        fontChoice.setSelectedItem(selectedMyTimeFormat.getCurrentFont());
        JLabel fontLabel = new JLabel(FONT_LABEL);
        fontLabel.setHorizontalAlignment(JLabel.RIGHT);
	    GuiUtil.addComponentByGridBagLayout(this, layout, fontLabel, 0, 0, 1, 1, 1, 1, GridBagConstraints.NONE, GridBagConstraints.EAST, new Insets(0,0,0,10));
	    GuiUtil.addComponentByGridBagLayout(this, layout, fontChoice, 3, 0, 2, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST, new Insets(0,0,0,10));
	    fontChoice.addItemListener(new DialogAction());

        int[] fontSizeSet = this.selectedMyTimeFormat.getFontSizeSet();
        for(int i=0 ; i<fontSizeSet.length ; i++){
        	fontSizeChoice.addItem(String.valueOf(fontSizeSet[i]));
	    }
        fontSizeChoice.setSelectedItem(String.valueOf(selectedMyTimeFormat.getCurrentFontSize()));
        JLabel fontSizeLabel = new JLabel(FONT_SIZE_LABEL);
        fontSizeLabel.setHorizontalAlignment(JLabel.RIGHT);
	    GuiUtil.addComponentByGridBagLayout(this, layout, fontSizeLabel, 0, 1, 1, 1, 1, 1, GridBagConstraints.NONE, GridBagConstraints.EAST, new Insets(0,0,0,10));
	    GuiUtil.addComponentByGridBagLayout(this, layout, fontSizeChoice, 3, 1, 2, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST, new Insets(0,0,0,10));
	    fontSizeChoice.addItemListener(new DialogAction());

        String[] fontColorNameSet = this.selectedMyTimeFormat.getLiteralColorSetWithString();
        for(int i=0 ; i<fontColorNameSet.length ; i++){
        	literalColorChoice.addItem(fontColorNameSet[i]);
	    }
        literalColorChoice.setSelectedItem(selectedMyTimeFormat.getCurrentLiteralColorWithString());
        JLabel literalColorLabel = new JLabel(LITERAL_COLOR_LABEL);
        chipColorLiteral.setBackground(selectedMyTimeFormat.getCurrentLiteralColor());
        chipColorLiteral.setBorder(new LineBorder(Color.BLACK));
        chipColorLiteral.setOpaque(true);
        literalColorLabel.setHorizontalAlignment(JLabel.RIGHT);
	    GuiUtil.addComponentByGridBagLayout(this, layout, literalColorLabel, 0, 2, 1, 1, 1, 1, GridBagConstraints.NONE, GridBagConstraints.EAST, new Insets(0,0,0,10));
	    GuiUtil.addComponentByGridBagLayout(this, layout, chipColorLiteral, 6, 2, 1, 1, 0, 0, GridBagConstraints.NONE, GridBagConstraints.WEST, new Insets(0,0,0,10));
	    GuiUtil.addComponentByGridBagLayout(this, layout, literalColorChoice, 3, 2, 2, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST, new Insets(0,0,0,10));
	    literalColorChoice.addItemListener(new DialogAction());

        String[] backColorNameSet = this.selectedMyBackCampus.getBackColorSetWithString();
        for(int i=0 ; i<backColorNameSet.length ; i++){
        	backColorChoice.addItem(backColorNameSet[i]);
	    }
        backColorChoice.setSelectedItem(selectedMyBackCampus.getCurrentBackColorWithString());
        JLabel backColorLabel = new JLabel(BACK_COLOR_LABEL);
        chipColorBack.setBackground((selectedMyBackCampus.getCurrentBackColor()));
        chipColorBack.setBorder(new LineBorder(Color.BLACK));
        chipColorBack.setOpaque(true);
        backColorLabel.setHorizontalAlignment(JLabel.RIGHT);
	    GuiUtil.addComponentByGridBagLayout(this, layout, backColorLabel, 0, 3, 1, 1, 1, 1, GridBagConstraints.NONE, GridBagConstraints.EAST, new Insets(0,0,0,10));
	    GuiUtil.addComponentByGridBagLayout(this, layout, chipColorBack, 6, 3, 1, 1, 0, 0, GridBagConstraints.NONE, GridBagConstraints.WEST, new Insets(0,0,0,10));
	    GuiUtil.addComponentByGridBagLayout(this, layout, backColorChoice, 3, 3, 2, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST, new Insets(0,0,0,10));
	    backColorChoice.addItemListener(new DialogAction());

	    blinkChoice.addItem("ON");
	    blinkChoice.addItem("OFF");
	    if(selectedDoBlink == true){
	    	blinkChoice.setSelectedItem("ON");
	    }else{
	    	blinkChoice.setSelectedItem("OFF");
	    }
	    JLabel blinkLabel = new JLabel(BLINK_LABEL);
	    blinkLabel.setHorizontalAlignment(JLabel.RIGHT);
	    GuiUtil.addComponentByGridBagLayout(this, layout, blinkLabel, 0, 4, 1, 1, 1, 1, GridBagConstraints.NONE, GridBagConstraints.EAST, new Insets(0,0,0,10));
	    GuiUtil.addComponentByGridBagLayout(this, layout, blinkChoice, 3, 4, 2, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST, new Insets(0,0,0,10));
	    blinkChoice.addItemListener(new DialogAction());

	    okButton = new JButton("    OK    ");
        okButton.addActionListener(new DialogAction());
	    GuiUtil.addComponentByGridBagLayout(this, layout, okButton, 3, 5, 1, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.EAST, new Insets(0,10,0,5));

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new DialogAction());
	    GuiUtil.addComponentByGridBagLayout(this, layout, cancelButton, 4, 5, 1, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.EAST, new Insets(0,5,0,10));
	}

	synchronized public boolean getDoBlink(){
		return selectedDoBlink;
	}

	class DialogAction extends WindowAdapter implements ActionListener , ItemListener{
	    public void windowClosing(WindowEvent e){
			selectedMyTimeFormat.setCurrentFont(originalMyTimeFormat.getCurrentFont());
			selectedMyTimeFormat.setCurrentFontSize(originalMyTimeFormat.getCurrentFontSize());
			selectedMyTimeFormat.setCurrentLiteralColorWithString(originalMyTimeFormat.getCurrentLiteralColorWithString());
			selectedMyBackCampus.setCurrentBackColorWithString(originalMyBackCampus.getCurrentBackColorWithString());
			selectedDoBlink = originalDoBlink;
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
		        chipColorLiteral.setBackground(selectedMyTimeFormat.getCurrentLiteralColor());
			}else if(e.getSource() == backColorChoice){
				selectedMyBackCampus.setCurrentBackColorWithString((String)e.getItem());
		        chipColorBack.setBackground(selectedMyBackCampus.getCurrentBackColor());
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
