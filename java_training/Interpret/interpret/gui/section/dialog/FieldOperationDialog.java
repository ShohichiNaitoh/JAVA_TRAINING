package interpret.gui.section.dialog;

import interpret.dispatcher.RequestDispatcher;
import interpret.util.GuiUtil;
import interpret.util.ReflectionUtil;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class FieldOperationDialog extends JDialog {
	private static final long serialVersionUID = 1L;

	private static final String  DIALOG_TITLE = "Field Oparation";
	private static final boolean MODAL = true;

	private JPanel fieldPanel = null;

	private JLabel modifierLabel = null;
	private JLabel classNameLabel = null;
	private JLabel variableNameLabel = null;
	private JLabel variableNameLabel2 = null;
	private JLabel equalLabel = null;

	private JScrollPane valueScrollPane = null;
	private JTextField valueTextField = null;
	private JComboBox valueComboBox = null;

	private JButton okButton = new JButton("         OK         ");
	private JButton cancelButton = new JButton("      Cancel      ");

	String modifier = null;
	String className = null;
	String variableName = null;
	String initialValue = null;

	Toolkit tk = getToolkit();
	Dimension dim;

	private RequestDispatcher requestDispatcher = null;


	public FieldOperationDialog(JFrame owner , String[] fieldInfo , RequestDispatcher requestDispatcher){
		super(owner , DIALOG_TITLE , MODAL);
		dim = tk.getScreenSize();
		setSize(dim.width*3/5 , dim.height*1/3);
		setLocation(dim.width / 2 - getWidth()/2 , dim.height / 2 - getHeight()/2);
        setResizable(false);
        this.requestDispatcher = requestDispatcher;
		GridBagLayout gridBagLayout = new GridBagLayout();
        setLayout(gridBagLayout);

        String modifier = fieldInfo[1];
        String className = fieldInfo[2];
        String variableName = fieldInfo[3];
        initialValue = fieldInfo[4];

        fieldPanel = new JPanel();
		GridBagLayout panelLayout = new GridBagLayout();
        fieldPanel.setLayout(panelLayout);
        modifierLabel = new JLabel(" " + modifier + " ");
        modifierLabel.setFont(new Font(null, Font.BOLD, 16));
        modifierLabel.setForeground(Color.black);
        modifierLabel.setHorizontalAlignment(JLabel.LEFT);

        classNameLabel = new JLabel(" " + ReflectionUtil.getSimpleName(className) + " ");
        classNameLabel.setFont(new Font(null, Font.PLAIN, 16));
        classNameLabel.setForeground(Color.black);
        classNameLabel.setHorizontalAlignment(JLabel.LEFT);

        variableNameLabel = new JLabel(" " + variableName + " ");
        variableNameLabel.setFont(new Font(null, Font.ITALIC, 16));
        variableNameLabel.setForeground(Color.blue);
        variableNameLabel.setHorizontalAlignment(JLabel.LEFT);

        GuiUtil.addComponentByGridBagLayout(fieldPanel, panelLayout, modifierLabel, 0, 0, 1, 1, 0.0, 1.0, GridBagConstraints.BOTH, GridBagConstraints.CENTER , new Insets(0,0,10,0));
        GuiUtil.addComponentByGridBagLayout(fieldPanel, panelLayout, classNameLabel, 1, 0, 1, 1, 0.0, 1.0, GridBagConstraints.BOTH, GridBagConstraints.CENTER , new Insets(0,0,10,0));
        GuiUtil.addComponentByGridBagLayout(fieldPanel, panelLayout, variableNameLabel, 2, 0, 1, 1, 0.0, 1.0, GridBagConstraints.BOTH, GridBagConstraints.CENTER , new Insets(0,0,10,0));

        TitledBorder titleBorder = new TitledBorder(new EtchedBorder(), "Field Information");
        titleBorder.setTitleColor(Color.black);
        titleBorder.setTitleFont(new Font(null, Font.BOLD, 15));
        fieldPanel.setBorder(titleBorder);

        variableNameLabel2 = new JLabel(variableName);
        variableNameLabel2.setFont(new Font(null, Font.ITALIC, 14));
        variableNameLabel2.setForeground(Color.blue);
        variableNameLabel2.setHorizontalAlignment(JLabel.RIGHT);

        equalLabel = new JLabel("  =  ");
        equalLabel.setFont(new Font(null, Font.PLAIN, 15));

        valueScrollPane = new JScrollPane();
        String classType = ReflectionUtil.getSimpleName(className);
        if(classType.equals("boolean")){
        	String[] value = {"true" , "false"};
        	valueComboBox = new JComboBox(value);
        	if(initialValue.equals(value[0])){
        		valueComboBox.setSelectedIndex(0);
        	}else{
        		valueComboBox.setSelectedIndex(1);
        	}
        	valueScrollPane.getViewport().setView(valueComboBox);
        }else if(classType.equals("byte") || classType.equals("char") ||  classType.equals("double")
        		|| classType.equals("float") || classType.equals("int") || classType.equals("long")
        		|| classType.equals("short") || className.equals("class java.lang.String")){
            valueTextField = new JTextField(initialValue);
            valueScrollPane.getViewport().setView(valueTextField);
        }else{
        	String[] tempValue = requestDispatcher.getVariableNamesMatchClassType(className);
        	String[] value = new String[tempValue.length + 1];
        	for(int i=0 ; i<tempValue.length + 1 ; i++){
        		if(i == 0){
        			value[i] = "null";
        		}else{
            		value[i] = tempValue[i-1];
        		}
        	}
        	valueComboBox = new JComboBox(value);
        	if(value.length != 0){
        		valueComboBox.setSelectedIndex(0);
        	}
        	valueScrollPane.getViewport().setView(valueComboBox);
        }
        valueScrollPane.setPreferredSize(valueScrollPane.getPreferredSize());

        GuiUtil.addComponentByGridBagLayout(this, gridBagLayout, fieldPanel, 0, 0, 5, 1, 1, 1, GridBagConstraints.BOTH, GridBagConstraints.CENTER , new Insets(20,20,20,20));
        GuiUtil.addComponentByGridBagLayout(this, gridBagLayout, variableNameLabel2, 0, 1, 1, 1, 1, 0, GridBagConstraints.BOTH, GridBagConstraints.EAST , new Insets(0,60,0,0));
        GuiUtil.addComponentByGridBagLayout(this, gridBagLayout, equalLabel, 1, 1, 1, 1, 0, 1, GridBagConstraints.NONE, GridBagConstraints.CENTER , new Insets(0,10,0,10));
        GuiUtil.addComponentByGridBagLayout(this, gridBagLayout, valueScrollPane, 2, 1, 3, 1, 1, 0.1, GridBagConstraints.BOTH, GridBagConstraints.WEST , new Insets(0,0,0,150));
        GuiUtil.addComponentByGridBagLayout(this, gridBagLayout, okButton, 3, 2, 1, 1, 1, 1, GridBagConstraints.NONE, GridBagConstraints.EAST, new Insets(20,0,0,10));
        GuiUtil.addComponentByGridBagLayout(this, gridBagLayout, cancelButton, 4, 2, 1, 1, 1, 1, GridBagConstraints.NONE, GridBagConstraints.WEST, new Insets(20,0,0,0));

        addWindowListener(new DialogAction());
        okButton.addActionListener(new DialogAction());
        cancelButton.addActionListener(new DialogAction());
	}


	class DialogAction extends WindowAdapter implements ActionListener{
	    public void windowClosing(WindowEvent e){
	       dispose();
	    }

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == okButton){
				if(valueScrollPane.getViewport().getView() instanceof JTextField){
					requestDispatcher.pushedFieldInfoDialog(valueTextField.getText());
				}else if(valueScrollPane.getViewport().getView() instanceof JComboBox){
					requestDispatcher.pushedFieldInfoDialog(valueComboBox.getSelectedItem().toString());
				}
				dispose();
			}else if(e.getSource() == cancelButton){
				dispose();
			}
		}
	}

}
