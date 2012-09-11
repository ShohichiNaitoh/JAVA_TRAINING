package interpret.gui.section.dialog;

import interpret.dispatcher.RequestDispatcher;
import interpret.gui.section.dialog.FieldOperationDialog.DialogAction;
import interpret.gui.util.GuiUtility;

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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class MethodOperationDialog extends JDialog{
	private static final long serialVersionUID = 1L;

	private static final String  DIALOG_TITLE = "Method Operation";
	private static final boolean MODAL = true;
	private JTable fieldTable = null;
	private DefaultTableModel tableMode = null;
	private String[] columnNames = {"modifier", "signiture"};

	private static final String argLabelName  = "arguments";
	private JLabel argLabel = null;
	private JComboBox argCombBox = null;

	private JButton okButton = new JButton("  Execute  ");
	private JButton cancelButton = new JButton("  Cancel  ");

	Toolkit tk = getToolkit();
	Dimension dim;

	private RequestDispatcher requestDispatcher = null;


	public MethodOperationDialog(JFrame owner, String[] fieldInfo , RequestDispatcher requestDispatcher){
		super(owner , DIALOG_TITLE , MODAL);
		dim = tk.getScreenSize();
		setSize(dim.width*2/3 , dim.height*2/3);
		setLocation(dim.width / 2 - getWidth()/2 , dim.height / 2 - getHeight()/2);
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
        fieldTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        JScrollPane fieldListScrollPanel = new JScrollPane();
		fieldListScrollPanel.getViewport().setView(fieldTable);

		String[] data = new String[2];
		data[0] = fieldInfo[1];
		data[1] = fieldInfo[2] + " " + fieldInfo[3] + GuiUtility.adjustArgsFormat(fieldInfo[4]);
		tableMode.addRow(data);

		argLabel = new JLabel(argLabelName);
		//argCombBox =

        GuiUtility.addComponentByGridBagLayout(this , gridBagLayout , fieldListScrollPanel , 0 , 0 , 6 , 1 , 1, 1, GridBagConstraints.BOTH, GridBagConstraints.CENTER, new Insets(0,0,0,0));
        GuiUtility.addComponentByGridBagLayout(this , gridBagLayout , argLabel , 1 , 1 , 1 , 1 , 1, 1, GridBagConstraints.VERTICAL, GridBagConstraints.CENTER, new Insets(0,0,0,0));
        //GuiUtility.addComponentByGridBagLayout(this , gridBagLayout , argTextField , 2 , 1 , 2 , 1 , GridBagConstraints.HORIZONTAL, GridBagConstraints.EAST);
        GuiUtility.addComponentByGridBagLayout(this , gridBagLayout , new JLabel(" ") , 5 , 1 , 1 , 1 , 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.EAST, new Insets(0,0,0,0));
        GuiUtility.addComponentByGridBagLayout(this , gridBagLayout , okButton , 2 , 2 , 1 , 1 , 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, new Insets(0,0,0,0));
        GuiUtility.addComponentByGridBagLayout(this , gridBagLayout , cancelButton , 3 , 2 , 1 , 1 , 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, new Insets(0,0,0,0));

        TitledBorder titleBorder = new TitledBorder(new EtchedBorder(), "Method information");
        titleBorder.setTitleColor(Color.BLACK);
        fieldListScrollPanel.setBorder(titleBorder);

        addWindowListener(new DialogAction());
        okButton.addActionListener(new DialogAction());
        cancelButton.addActionListener(new DialogAction());
        addWindowListener(new DialogAction());

	}

	class DialogAction extends WindowAdapter implements ActionListener{
	    public void windowClosing(WindowEvent e){
	       dispose();
	    }

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == okButton){
				requestDispatcher.pushedMethodInfoDialog();
				dispose();
			}else if(e.getSource() == cancelButton){
				dispose();
			}
		}
	}

}
