package interpret.gui.section;
import interpret.dispatcher.RequestDispatcher;
import interpret.util.GuiUtil;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.Document;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;


public class ResultOutputPanel extends JPanel{
	private static final long serialVersionUID = 1L;

	private static final String sectionName = "Result Section";

	private JTextPane outputTextPane = null;
	private JScrollPane scrollPanel = null;

	private RequestDispatcher requestDispatcher = null;


	public ResultOutputPanel(RequestDispatcher requestDispatcher){
		this.requestDispatcher = requestDispatcher;
        GridBagLayout gridBagLayout = new GridBagLayout();
        setLayout(gridBagLayout);

		outputTextPane = new JTextPane();
		scrollPanel = new JScrollPane(outputTextPane);
		scrollPanel.setPreferredSize(new Dimension(500, 150));

        GuiUtil.addComponentByGridBagLayout(this, gridBagLayout, scrollPanel, 0, 0, 1, 1, 1, 1, GridBagConstraints.BOTH, GridBagConstraints.CENTER , new Insets(0,5,5,5));

        TitledBorder titleBorder = new TitledBorder(new EtchedBorder(), sectionName);
        titleBorder.setTitleColor(Color.RED);
        setBorder(titleBorder);
	}

	public void outputException(String exception){
		outputTextPane.setText(exception);
		MutableAttributeSet attr = new SimpleAttributeSet();
		StyleConstants.setForeground(attr , Color.RED);
		StyledDocument doc = outputTextPane.getStyledDocument();
		doc.setCharacterAttributes(0, exception.length() , attr, true);
		outputTextPane.setCaretPosition(0);
	}

	public void ouputMethodResult(String result){
		String message = "return  ： " + result;
		outputTextPane.setText(message);
		MutableAttributeSet attr = new SimpleAttributeSet();
		StyleConstants.setForeground(attr , Color.BLACK);
		StyledDocument doc = outputTextPane.getStyledDocument();
		doc.setCharacterAttributes(0, message.length() , attr, true);
		outputTextPane.setCaretPosition(0);
	}

	public void reset(){
		outputTextPane.setText("");
	}

}
