package ch16.ex16_09.Interpret.interpret.gui.section.dialog;


import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.AbstractCellEditor;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.synth.ColorType;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import ch16.ex16_09.Interpret.interpret.dispatcher.RequestDispatcher;
import ch16.ex16_09.Interpret.interpret.util.GuiUtil;
import ch16.ex16_09.Interpret.interpret.util.ReflectionUtil;

public class MethodOperationDialog extends JDialog{
	private static final long serialVersionUID = 1L;

	private static final String  DIALOG_TITLE = "Method Operation";
	private static final boolean MODAL = true;

	private JPanel methodPanel = null;
	private JLabel modifierLabel = null;
	private JLabel signitureLabel = null;
	private JLabel throwsLabel = null;
	private JLabel exceptionLabel = null;

	private static final String ARG_LABEL_NAME = "Arguments";
	private JLabel argLabel = null;
	private MyJTable argTable = null;
	private DefaultTableModel tableModel = null;
	private String[] columnNames = {"type", "name", "value"};
	private JScrollPane argScrollPane = null;

	private JButton okButton = new JButton("      Execute      ");
	private JButton cancelButton = new JButton("      Cancel      ");

	Toolkit tk = getToolkit();
	Dimension dim;

	private RequestDispatcher requestDispatcher = null;


	public MethodOperationDialog(JFrame owner, String[] methodInfo , RequestDispatcher requestDispatcher){
		super(owner , DIALOG_TITLE , MODAL);
		dim = tk.getScreenSize();
		setSize(dim.width*3/5 , dim.height*1/2);
		setLocation(dim.width / 2 - getWidth()/2 , dim.height / 2 - getHeight()/2);
        setResizable(false);
        this.requestDispatcher = requestDispatcher;
		GridBagLayout gridBagLayout = new GridBagLayout();
        setLayout(gridBagLayout);

        String modifier = methodInfo[1];
        String returnValue = ReflectionUtil.getSimpleName(methodInfo[2]);
        String name = methodInfo[3];
        String args = methodInfo[4];
        String exception = methodInfo[5];

        methodPanel = new JPanel();
		GridBagLayout methodPanelLayout = new GridBagLayout();
		methodPanel.setLayout(methodPanelLayout);
        modifierLabel = new JLabel(" " + modifier + " ");
        modifierLabel.setFont(new Font(null, Font.BOLD, 16));
        modifierLabel.setForeground(Color.black);
        modifierLabel.setHorizontalAlignment(JLabel.LEFT);


		StringBuffer sb = new StringBuffer();
		sb.append(returnValue + "  ");
		sb.append(ReflectionUtil.getSimpleName(name));
		sb.append(ReflectionUtil.adjustArgsFormatBySimpleName(args));

        signitureLabel = new JLabel(" " + sb.toString() + " ");
        signitureLabel.setFont(new Font(null, Font.PLAIN, 16));
        signitureLabel.setForeground(Color.black);
        signitureLabel.setHorizontalAlignment(JLabel.LEFT);

        GuiUtil.addComponentByGridBagLayout(methodPanel, methodPanelLayout, modifierLabel, 0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER , new Insets(0,0,10,0));
        GuiUtil.addComponentByGridBagLayout(methodPanel, methodPanelLayout, signitureLabel, 1, 0, 2, 1, 0.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER , new Insets(0,0,10,0));

        if(exception != null && !exception.equals("")){
            throwsLabel = new JLabel("       " + "throws  ");
            throwsLabel.setFont(new Font(null, Font.BOLD, 16));
            throwsLabel.setForeground(Color.black);
            throwsLabel.setHorizontalAlignment(JLabel.LEFT);

            exceptionLabel = new JLabel(ReflectionUtil.getSimpleName(exception));
            exceptionLabel.setFont(new Font(null, Font.PLAIN, 16));
            exceptionLabel.setForeground(Color.black);
            exceptionLabel.setHorizontalAlignment(JLabel.LEFT);
            GuiUtil.addComponentByGridBagLayout(methodPanel, methodPanelLayout, throwsLabel, 1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER , new Insets(0,0,10,0));
            GuiUtil.addComponentByGridBagLayout(methodPanel, methodPanelLayout, exceptionLabel, 2, 1, 1, 1, 0.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER , new Insets(0,0,10,0));
        }

        TitledBorder methodTitleBorder = new TitledBorder(new EtchedBorder(), "Method Information");
        methodTitleBorder.setTitleColor(Color.black);
        methodTitleBorder.setTitleFont(new Font(null, Font.BOLD, 15));
        methodPanel.setBorder(methodTitleBorder);


        argLabel = new JLabel(ARG_LABEL_NAME);
        argLabel.setFont(new Font(null, Font.BOLD, 14));
        argLabel.setForeground(Color.blue);

        tableModel = new DefaultTableModel(columnNames , 0){
			private static final long serialVersionUID = 1L;
			@Override
        	public boolean isCellEditable(int row, int column) {
        		if(column == 2){
        			return true;
        		}else{
            		return false;
        		}
        	};
        };
        argTable = new MyJTable(tableModel);
        argTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        argTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        argTable.addMouseListener(new TableAction());
        argScrollPane = new JScrollPane();
        argScrollPane.getViewport().setView(argTable);

        if(args != null && !args.equals("")){
            String[] argTypes = args.split(",");
            for(int i=0 ; i<argTypes.length ; i++){
            	String[] str = new String[3];
            	str[0] = ReflectionUtil.getSimpleName(argTypes[i]);
            	str[1] = "arg" + i;
            	str[2] = null;
            	tableModel.addRow(str);
            }

            for(int i=0 ; i<argTypes.length ; i++){
            	String classType = ReflectionUtil.getSimpleName(argTypes[i]);
            	if(classType.equals("boolean")){
                	String[] value = {"true" , "false"};
                	JComboBox valueComboBox = new JComboBox(value);
                	valueComboBox.setSelectedIndex(0);
                	valueComboBox.setBackground(Color.white);
                	valueComboBox.setFocusable(false);
                	argTable.setValueAt(valueComboBox, i, 2);
            	}else if(classType.equals("byte")||  classType.equals("double")	|| classType.equals("float")
            			|| classType.equals("int") || classType.equals("long")
                		|| classType.equals("short")){
            		JTextField valueTextField = new JTextField("0");
            		valueTextField.setBackground(Color.white);
            		argTable.setValueAt(valueTextField, i, 2);
            	}else if(argTypes[i].equals("class java.lang.String")){
            		JTextField valueTextField = new JTextField("");
            		valueTextField.setBackground(Color.white);
            		argTable.setValueAt(valueTextField, i, 2);
            	}else if( classType.equals("char") ){
            		JTextField valueTextField = new JTextField("");
            		valueTextField.setBackground(Color.white);
            		argTable.setValueAt(valueTextField, i, 2);
            	}else{
                	String[] tempValue = requestDispatcher.getVariableNamesMatchClassType(argTypes[i]);
                	String[] value = new String[tempValue.length + 1];
                	for(int j=0 ; j<tempValue.length + 1 ; j++){
                		if(j == 0){
                			value[j] = "null";
                		}else{
                    		value[j] = tempValue[j-1];
                		}
                	}
                	JComboBox valueComboBox = new JComboBox(value);
                	valueComboBox.setBackground(Color.white);
                	valueComboBox.setFocusable(false);
                	if(value.length != 0){
                    	valueComboBox.setSelectedIndex(0);
                	}
                	argTable.setValueAt(valueComboBox, i, 2);
            	}
            }
        }

        GuiUtil.addComponentByGridBagLayout(this, gridBagLayout, methodPanel, 0, 0, 5, 1, 1, 0.1, GridBagConstraints.BOTH, GridBagConstraints.CENTER , new Insets(20,20,20,20));
        GuiUtil.addComponentByGridBagLayout(this, gridBagLayout, argLabel, 0, 1, 1, 1, 0, 0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER , new Insets(0,70,0,70));
        GuiUtil.addComponentByGridBagLayout(this, gridBagLayout, argScrollPane, 0, 2, 5, 1, 1, 1, GridBagConstraints.BOTH, GridBagConstraints.CENTER , new Insets(0,70,0,70));
        GuiUtil.addComponentByGridBagLayout(this, gridBagLayout, okButton, 3, 3, 1, 1, 1, 0.5, GridBagConstraints.NONE, GridBagConstraints.EAST, new Insets(0,140,0,10));
        GuiUtil.addComponentByGridBagLayout(this, gridBagLayout, cancelButton, 4, 3, 1, 1, 1, 0.5, GridBagConstraints.NONE, GridBagConstraints.WEST, new Insets(0,0,0,0));

        addWindowListener(new DialogAction());
        okButton.addActionListener(new DialogAction());
        cancelButton.addActionListener(new DialogAction());
        addWindowListener(new DialogAction());

	}

	class MyJTable extends JTable {

		private static final long serialVersionUID = 1L;

		MyJTable(DefaultTableModel model) {
			super(model);
		}

		protected TableCellRenderer cellRenderer = new MyCellRenderer();
		protected TableCellEditor cellEditor = new MyCellEditor();

		protected class MyCellRenderer implements TableCellRenderer {

			public Component getTableCellRendererComponent(JTable table , Object value , boolean isSelected , boolean hasFocus , int row , int	column){
				JComponent c = (JComponent)value;
				return c;
			}
		}

		protected class MyCellEditor extends AbstractCellEditor implements TableCellEditor {
			private static final long serialVersionUID = 1L;
			protected JComponent c = null;

			public Object getCellEditorValue() {
				return c;
			}

			public Component getTableCellEditorComponent(JTable table , Object value, boolean isSelected, int row, int column) {
				c = (JComponent)value;
				return c;
			}
		}

		public TableCellRenderer getCellRenderer(int row, int col) {
			TableCellRenderer renderer;
			Object o = getValueAt(row, col);
			if(o instanceof JComponent) {
				renderer = cellRenderer;
			} else {
				renderer = super.getCellRenderer(row, col);
			}
			return renderer;
		}

		public TableCellEditor getCellEditor(int row, int col) {
			TableCellEditor editor;
			Object o = getValueAt(row, col);
			if( o instanceof JComponent) {
				editor = cellEditor;
			} else {
				editor = super.getCellEditor(row, col);
			}
			return editor;
		}
	}

	class DialogAction extends WindowAdapter implements ActionListener {
	    public void windowClosing(WindowEvent e){
	       dispose();
	    }

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == okButton){
				String[] argsValue = new String[argTable.getRowCount()];
				for(int i=0 ; i<argTable.getRowCount() ; i++){
					if(argTable.getValueAt(i, 2) instanceof JTextField){
						argsValue[i] = ((JTextField) argTable.getValueAt(i, 2)).getText();
					}else if(argTable.getValueAt(i, 2) instanceof JComboBox){
						argsValue[i] = ((JComboBox) argTable.getValueAt(i, 2)).getSelectedItem().toString();
					}
				}
				requestDispatcher.pushedMethodInfoDialog(argsValue);
				dispose();
			}else if(e.getSource() == cancelButton){
				dispose();
			}
		}
	}

	class TableAction extends MouseAdapter{
		@Override
		public void mousePressed(MouseEvent e) {
			for(int i=0 ; i<argTable.getRowCount() ; i++){
				if(i != argTable.getSelectedRow()){
					((JComponent) argTable.getValueAt(i, 2)).setBackground(Color.white);
					if(argTable.getValueAt(i, 2) instanceof JTextField){
						((JTextField) argTable.getValueAt(i, 2)).select(0, 0);
					}
				}
			}
		}
	}

}
