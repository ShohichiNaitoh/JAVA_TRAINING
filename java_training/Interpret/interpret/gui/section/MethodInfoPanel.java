package interpret.gui.section;
import interpret.gui.section.ObjectArrayInfoPanel.ObjArryInfoAction;
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


public class MethodInfoPanel extends JPanel{

	private static final long serialVersionUID = 1L;

	private static final String sectionName = "Method List Section";

	private static final String methodListName = "Method List";
	private JLabel methodListLabel = null;
	private JTable methodTable = null;
	private JScrollPane methodListScrollPanel = null;
	private DefaultTableModel tableMode = null;
	private String[] columnNames = {"modifier" , "signiture"};

	private RequestDispatcher requestDispatcher = null;


	public MethodInfoPanel(RequestDispatcher requestDispatcher){
		this.requestDispatcher = requestDispatcher;
        GridBagLayout gridBagLayout = new GridBagLayout();
        setLayout(gridBagLayout);

        methodListLabel = new JLabel(methodListName);
        tableMode = new DefaultTableModel(columnNames , 0){
        	@Override
        	public boolean isCellEditable(int row, int column) {
        		return false;
        	};
        };
        methodTable = new JTable(tableMode);
        methodTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        methodTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        methodTable.getSelectionModel().addListSelectionListener(new MethodInfoAction());
        methodTable.addMouseListener(new MethodInfoAction());
        methodListScrollPanel = new JScrollPane();
		methodListScrollPanel.getViewport().setView(methodTable);

		//GuiUtility.addComponentByGridBagLayout(this , gridBagLayout , new JLabel("        ") , 0 , 0 , 1 , 1 , GridBagConstraints.BOTH, GridBagConstraints.WEST);
        //GuiUtility.addComponentByGridBagLayout(this , gridBagLayout , methodListLabel , 0 , 0 , 1 , 1 , GridBagConstraints.NONE, GridBagConstraints.NORTHWEST);
        GuiUtility.addComponentByGridBagLayout(this , gridBagLayout , methodListScrollPanel , 0 , 0 , 1 , 1 , GridBagConstraints.BOTH, GridBagConstraints.NORTHWEST);
        //GuiUtility.addComponentByGridBagLayout(this , gridBagLayout , new JLabel("        ") , 3 , 3 , 1 , 1 , GridBagConstraints.BOTH, GridBagConstraints.WEST);

        TitledBorder titleBorder = new TitledBorder(new EtchedBorder(), sectionName);
        titleBorder.setTitleColor(Color.DARK_GRAY);
        setBorder(titleBorder);
	}

	public void updateMethodInfo(String[][] methodInfo){
		tableMode.setRowCount(0);
		for(int i=0 ; i<methodInfo.length ; i++){
			tableMode.addRow(methodInfo[i]);
		}
	}

	public void reset(){
		tableMode.setRowCount(0);
	}

	public void selectExclusive(){
		methodTable.clearSelection();
	}

	class MethodInfoAction extends MouseAdapter implements ListSelectionListener{
		@Override
		public void valueChanged(ListSelectionEvent e) {
			if(e.getValueIsAdjusting()){
				if(e.getLastIndex() != -1){
					requestDispatcher.clickedMethodInfoList();
				}
			}
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getClickCount() >= 2){
				requestDispatcher.doubleClickedMethodInfoList(methodTable.getSelectedRow());
			}
		}
	}
}
