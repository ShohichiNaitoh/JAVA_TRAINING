package interpret.gui.section;
import interpret.gui.section.MethodInfoPanel.MethodInfoAction;
import interpret.gui.util.GuiUtility;
import interpret.logic.RequestDispatcher;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
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
import javax.swing.table.DefaultTableModel;


public class FieldInfoPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private static final String sectionName = "Field List Section";

	private static final String filedListName = "Field List";
	private JLabel fieldListLabel = null;
	private JTable fieldTable = null;
	private JScrollPane fieldListScrollPanel = null;
	private DefaultTableModel tableMode = null;
	private String[] columnNames = {"modifier", "type", "name" , "value"};

	private RequestDispatcher requestDispatcher = null;


	public FieldInfoPanel(RequestDispatcher requestDispatcher){
		this.requestDispatcher = requestDispatcher;
		GridBagLayout gridBagLayout = new GridBagLayout();
        setLayout(gridBagLayout);

        fieldListLabel = new JLabel(filedListName);
        tableMode = new DefaultTableModel(columnNames , 0){
        	@Override
        	public boolean isCellEditable(int row, int column) {
        		return false;
        	};
        };
        fieldTable = new JTable(tableMode);
        fieldTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        fieldTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        fieldTable.getSelectionModel().addListSelectionListener(new FieldInfoAction());
        fieldTable.addMouseListener(new FieldInfoAction());
        fieldListScrollPanel = new JScrollPane();
		fieldListScrollPanel.getViewport().setView(fieldTable);

		//GuiUtility.addComponentByGridBagLayout(this , gridBagLayout , new JLabel("        ") , 0 , 0 , 1 , 1 , GridBagConstraints.NONE, GridBagConstraints.WEST);
        //GuiUtility.addComponentByGridBagLayout(this , gridBagLayout , fieldListLabel , 0 , 0 , 1 , 1 , GridBagConstraints.NONE, GridBagConstraints.NORTHWEST);
        GuiUtility.addComponentByGridBagLayout(this , gridBagLayout , fieldListScrollPanel , 0 , 0 , 1 , 1 , GridBagConstraints.BOTH, GridBagConstraints.NORTHWEST);
        //GuiUtility.addComponentByGridBagLayout(this , gridBagLayout , new JLabel("        ") , 3 , 3 , 1 , 1 , GridBagConstraints.NONE, GridBagConstraints.WEST);

        TitledBorder titleBorder = new TitledBorder(new EtchedBorder(), sectionName);
        titleBorder.setTitleColor(Color.DARK_GRAY);
        setBorder(titleBorder);
	}

	public void updateFieldInfo(String[][] fieldInfo){
		tableMode.setRowCount(0);
		for(int i=0 ; i<fieldInfo.length ; i++){
			tableMode.addRow(fieldInfo[i]);
		}
	}

	public void reset(){
		tableMode.setRowCount(0);
	}

	public void selectExclusive(){
		fieldTable.clearSelection();
	}

	class FieldInfoAction extends MouseAdapter implements ListSelectionListener{
		@Override
		public void valueChanged(ListSelectionEvent e) {
			if(e.getValueIsAdjusting()){
				if(e.getLastIndex() != -1){
					requestDispatcher.clickedFieldInfoList();
				}
			}
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getClickCount() >= 2){
				requestDispatcher.doubleClickedFieldInfoList(fieldTable.getSelectedRow());
			}
		}
	}
}
