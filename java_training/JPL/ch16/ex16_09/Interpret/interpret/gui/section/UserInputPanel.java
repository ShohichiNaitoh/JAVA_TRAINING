package ch16.ex16_09.Interpret.interpret.gui.section;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.LayoutFocusTraversalPolicy;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import ch16.ex16_09.Interpret.interpret.dispatcher.RequestDispatcher;
import ch16.ex16_09.Interpret.interpret.logic.Variable.VariableType;
import ch16.ex16_09.Interpret.interpret.util.GuiUtil;


public class UserInputPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private static final String sectionName = "User Input Section";

	private static final String variabInputLabelName = "Variable Name                                      ";
	private JLabel variableInputLabel = null;
	private JTextField variableInputField = null;

	private static final String classInputLabelName =  "Class Name                                         ";
	private JLabel classNameInputLabel = null;
	private JTextField classNameInputField = null;

	private static final String variableTypeSectionName = "Variable Type";
	private ButtonGroup variableTypeGroup = null;
	private static final String notArrayTypeButtonName = "Not Array";
	private static final String arrayTypeButtonName = "Array";
	private JRadioButton notArrayTypeButton = null;
	private JRadioButton arrayTypeButton = null;
	private JComboBox sizeComboBox = null;

	private static final String okButtonName = "    Create Variable !!    ";
	private JButton okButton = null;

	public static final int MAX_ARRAY_SIZE = 10;
	private RequestDispatcher requestDispatcher = null;


	public UserInputPanel(RequestDispatcher requestDispatcher){
		this.requestDispatcher = requestDispatcher;
        GridBagLayout gridBagLayout = new GridBagLayout();
        setLayout(gridBagLayout);

        variableTypeGroup = new ButtonGroup();
        notArrayTypeButton = new JRadioButton(notArrayTypeButtonName , true);
        notArrayTypeButton.setActionCommand(notArrayTypeButtonName);
        arrayTypeButton = new JRadioButton(arrayTypeButtonName);
        arrayTypeButton.setActionCommand(arrayTypeButtonName);
        variableTypeGroup.add(notArrayTypeButton);
        variableTypeGroup.add(arrayTypeButton);
        String[] combodata = new String[MAX_ARRAY_SIZE];
        for(int i=0 ; i<MAX_ARRAY_SIZE ; i++){
        	combodata[i] = String.valueOf(i+1);
        }
        sizeComboBox = new JComboBox(combodata);
        sizeComboBox.setSelectedIndex(0);
        sizeComboBox.setEnabled(false);
        JPanel variableTypePanel = new JPanel();
        GridBagLayout variablePanelLayout = new GridBagLayout();
        variableTypePanel.setLayout(variablePanelLayout);
        TitledBorder panelTitleBorder = new TitledBorder(new EtchedBorder(), variableTypeSectionName);
        panelTitleBorder.setTitleColor(Color.BLACK);
        variableTypePanel.setBorder(panelTitleBorder);
        GuiUtil.addComponentByGridBagLayout(variableTypePanel, variablePanelLayout, notArrayTypeButton, 0, 0, 1, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, new Insets(0,10,10,15));
        GuiUtil.addComponentByGridBagLayout(variableTypePanel, variablePanelLayout, arrayTypeButton, 1, 0, 1, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, new Insets(0,0,10,5));
        GuiUtil.addComponentByGridBagLayout(variableTypePanel, variablePanelLayout, sizeComboBox, 2, 0, 1, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, new Insets(0,0,10,15));

        notArrayTypeButton.addActionListener(new UserInputAction());
        arrayTypeButton.addActionListener(new UserInputAction());

        variableInputLabel = new JLabel(variabInputLabelName);
        variableInputField = new JTextField();
        variableInputField.addKeyListener(new UserInputAction());
		classNameInputLabel = new JLabel(classInputLabelName);
		classNameInputField = new JTextField();
		classNameInputField.addKeyListener(new UserInputAction());

		GuiUtil.addComponentByGridBagLayout(this, gridBagLayout, variableTypePanel, 0, 0, 1, 2, 0, 0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER , new Insets(-10,30,0,10));

        GuiUtil.addComponentByGridBagLayout(this, gridBagLayout, variableInputLabel, 1, 0, 1, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER , new Insets(0,15,0,15));
        GuiUtil.addComponentByGridBagLayout(this, gridBagLayout, variableInputField, 1, 1, 1, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.NORTH , new Insets(0,15,0,15));
        GuiUtil.addComponentByGridBagLayout(this, gridBagLayout, classNameInputLabel, 2, 0, 1, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER , new Insets(0,15,0,30));
        GuiUtil.addComponentByGridBagLayout(this, gridBagLayout, classNameInputField, 2, 1, 1, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.NORTH , new Insets(0,15,0,30));

        okButton = new JButton(okButtonName);
        okButton.addActionListener(new UserInputAction());

        GuiUtil.addComponentByGridBagLayout(this, gridBagLayout, okButton, 2, 2, 1, 1, 0, 0, GridBagConstraints.NONE, GridBagConstraints.CENTER , new Insets(-10,0,10,0));

        TitledBorder titleBorder = new TitledBorder(new EtchedBorder(), sectionName);
        titleBorder.setTitleColor(Color.BLUE);
        this.setBorder(titleBorder);

        switchExecuteButton();
	}

	private void switchExecuteButton(){
		if(variableInputField.getText().length() != 0 && classNameInputField.getText().length() != 0){
			okButton.setEnabled(true);
		}else{
			okButton.setEnabled(false);
		}
	}

	class UserInputAction implements ActionListener , KeyListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == okButton){
				String variableName = variableInputField.getText();
				String className = classNameInputField.getText();
				int size = Integer.parseInt((String)sizeComboBox.getSelectedItem());

				if(variableTypeGroup.getSelection().getActionCommand() == notArrayTypeButtonName){
					requestDispatcher.pushedExecuteButton(VariableType.NOT_ARRAY , -1 , variableName , className);
				}else if(variableTypeGroup.getSelection().getActionCommand() == arrayTypeButtonName){
					requestDispatcher.pushedExecuteButton(VariableType.ARRAY , size , variableName , className);
				}
			}else if(e.getSource() == notArrayTypeButton){
				sizeComboBox.setEnabled(false);
			}else if(e.getSource() == arrayTypeButton){
				sizeComboBox.setEnabled(true);
			}
		}

		@Override
		public void keyTyped(KeyEvent e) {
			switchExecuteButton();
		}

		@Override
		public void keyPressed(KeyEvent e) {
			switchExecuteButton();
		}

		@Override
		public void keyReleased(KeyEvent e) {
			switchExecuteButton();
		}
	}

}
