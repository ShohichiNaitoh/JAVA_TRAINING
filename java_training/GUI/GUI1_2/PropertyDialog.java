package GUI1_2;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import GUI1_2.ClockWindow.WindowAction;

public class PropertyDialog extends Dialog implements ActionListener {

	Literal newLiteral = null;
	Campus newCampus = null;

	Toolkit tk = getToolkit();
	Dimension dim;

	Choice fontChoice;
	Choice fontSizeChoice;
	Choice fontColorChoice;
	Choice backColorChoice;

	Button okButton;
	Button cancelButton;

	public PropertyDialog(Frame owner, String title, boolean modal , Literal newLiteral , Campus newCampus) {
		super(owner, title, modal);
		this.newLiteral = newLiteral;
		this.newCampus = newCampus;

		setSize(320 , 220);
		dim = tk.getScreenSize();
		setLocation(dim.width / 2 - 200 , dim.height / 2 - 200);
        setResizable(false);
        addWindowListener(new WindowAction());

        setLayout(new GridLayout(3,2));
        fontChoice = new Choice();
        fontChoice.add("明朝体");
        fontChoice.add("ゴシック体");
        fontChoice.add("等幅フォント");
        fontChoice.select(newLiteral.getFontIndex());
        Label l1 = new Label("フォント");
        Panel p1 = new Panel(new GridLayout(4,1));
        p1.add(l1);
        p1.add(fontChoice);
        add(p1);

        fontSizeChoice = new Choice();
        fontSizeChoice.add("40");
        fontSizeChoice.add("100");
        fontSizeChoice.add("200");
        fontSizeChoice.select(newLiteral.getSizeIndex());
        Label l2 = new Label("フォントサイズ");
        Panel p2 = new Panel(new GridLayout(4,1));
        p2.add(l2);
        p2.add(fontSizeChoice);
        add(p2);

        fontColorChoice = new Choice();
        fontColorChoice.add("黒");
        fontColorChoice.add("赤");
        fontColorChoice.add("青");
        fontColorChoice.select(newLiteral.getColorIndex());
        add(fontColorChoice);
        Label l3 = new Label("文字色");
        Panel p3 = new Panel(new GridLayout(4,1));
        p3.add(l3);
        p3.add(fontColorChoice);
        add(p3);

        backColorChoice = new Choice();
        backColorChoice.add("白");
        backColorChoice.add("緑");
        backColorChoice.add("黄色");
        backColorChoice.select(newCampus.getColorIndex());
        add(backColorChoice);
        Label l4 = new Label("背景色");
        Panel p4 = new Panel(new GridLayout(4,1));
        p4.add(l4);
        p4.add(backColorChoice);
        add(p4);

        okButton = new Button("OK");
        okButton.addActionListener(this);

        cancelButton = new Button("Cancel");
        cancelButton.addActionListener(this);

        add(okButton);
        add(cancelButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == okButton){
			String font = fontChoice.getSelectedItem();
			if(font.equals("明朝体")){
				newLiteral.setFont(Literal.FONT.SERIF);
			}else if(font.equals("ゴシック体")){
				newLiteral.setFont(Literal.FONT.SANS_SERIF);
			}else if(font.equals("等幅フォント")){
				newLiteral.setFont(Literal.FONT.MONOSPACED);
			}

			String fontSize = fontSizeChoice.getSelectedItem();
			if(fontSize.equals("40")){
				newLiteral.setSize(Literal.SIZE.FOUTY);
			}else if(fontSize.equals("100")){
				newLiteral.setSize(Literal.SIZE.HUNDRED);
			}else if(fontSize.equals("200")){
				newLiteral.setSize(Literal.SIZE.TWO_HUNDRED);
			}

			String fontColor = fontColorChoice.getSelectedItem();
			if(fontColor.equals("黒")){
				newLiteral.setColor(Literal.COLOR.BLACK);
			}else if(fontColor.equals("赤")){
				newLiteral.setColor(Literal.COLOR.RED);
			}else if(fontColor.equals("青")){
				newLiteral.setColor(Literal.COLOR.BLUE);
			}

			String backColor = backColorChoice.getSelectedItem();
			if(backColor.equals("白")){
				newCampus.setColor(Campus.COLOR.WHITE);
			}else if(backColor.equals("緑")){
				newCampus.setColor(Campus.COLOR.GREEN);
			}else if(backColor.equals("黄色")){
				newCampus.setColor(Campus.COLOR.YELLOW);
			}

			dispose();
		}else if(e.getSource() == cancelButton){
			dispose();
		}
	}

	class WindowAction extends WindowAdapter{
	    public void windowClosing(WindowEvent e){
	       dispose();
	    }
	}
}
