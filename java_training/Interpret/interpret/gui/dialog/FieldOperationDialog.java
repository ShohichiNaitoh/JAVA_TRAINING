package interpret.gui.dialog;

import interpret.gui.util.GuiUtility;
import interpret.logic.RequestDispatcher;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
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

	private JTable fieldTable = null;
	private DefaultTableModel tableMode = null;
	private String[] columnNames = {"modifier", "type", "name"};

	private static final String valueLabelName  = "value";
	private JLabel valueLabel = null;
	private JTextField valueTextField = null;

	private JButton okButton = new JButton("     OK     ");
	private JButton cancelButton = new JButton("  Cancel  ");

	Toolkit tk = getToolkit();
	Dimension dim;

	private RequestDispatcher requestDispatcher = null;


	public FieldOperationDialog(JFrame owner , String[] fieldInfo , RequestDispatcher requestDispatcher){
		super(owner , DIALOG_TITLE , MODAL);
		setSize(600 , 200);
		dim = tk.getScreenSize();
		setLocation(dim.width / 2 - 200 , dim.height / 2 - 200);
        setResizable(false);
        this.requestDispatcher = requestDispatcher;

		GridBagLayout gridBagLayout = new GridBagLayout();
        setLayout(gridBagLayout);

        tableMode = new DefaultTableModel(columnNames , 0){
        	@Override
        	public boolean isCellEditable(int row, int column) {
        		return false;
        	};
        };
        fieldTable = new JTable(tableMode);
        fieldTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane fieldListScrollPanel = new JScrollPane();
		fieldListScrollPanel.getViewport().setView(fieldTable);

		String[] data = new String[3];
		data[0] = fieldInfo[0];
		data[1] = fieldInfo[1];
		data[2] = fieldInfo[2];
		tableMode.addRow(data);

		valueLabel = new JLabel(valueLabelName);
		valueTextField = new JTextField(fieldInfo[3]);

        GuiUtility.addComponentByGridBagLayout(this , gridBagLayout , fieldListScrollPanel , 0 , 0 , 6 , 1 , GridBagConstraints.BOTH, GridBagConstraints.CENTER);
        GuiUtility.addComponentByGridBagLayout(this , gridBagLayout , valueLabel , 1 , 1 , 1 , 1 , GridBagConstraints.VERTICAL, GridBagConstraints.CENTER);
        GuiUtility.addComponentByGridBagLayout(this , gridBagLayout , valueTextField , 2 , 1 , 2 , 1 , GridBagConstraints.HORIZONTAL, GridBagConstraints.EAST);
        GuiUtility.addComponentByGridBagLayout(this , gridBagLayout , new JLabel(" ") , 5 , 1 , 1 , 1 , GridBagConstraints.HORIZONTAL, GridBagConstraints.EAST);
        GuiUtility.addComponentByGridBagLayout(this , gridBagLayout , okButton , 2 , 2 , 1 , 1 , GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        GuiUtility.addComponentByGridBagLayout(this , gridBagLayout , cancelButton , 3 , 2 , 1 , 1 , GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);

        TitledBorder titleBorder = new TitledBorder(new EtchedBorder(), "Field Information");
        titleBorder.setTitleColor(Color.BLACK);
        fieldListScrollPanel.setBorder(titleBorder);

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
				requestDispatcher.pushedFieldInfoDialog(valueTextField.getText());
				dispose();
			}else if(e.getSource() == cancelButton){
				dispose();
			}
		}
	}

}
