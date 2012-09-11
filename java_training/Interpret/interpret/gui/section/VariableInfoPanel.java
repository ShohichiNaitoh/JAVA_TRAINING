package interpret.gui.section;

import interpret.dispatcher.RequestDispatcher;
import interpret.gui.InterpretFrame;
import interpret.gui.util.GuiUtility;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class VariableInfoPanel extends JPanel{

	private static final long serialVersionUID = 1L;

	private static final String sectionName = "Valiable List Section";

	private static final String objectListName = "Valiable List";
	private JList variableList = null;
	private JScrollPane variableListScrollPanel = null;
	private DefaultListModel defaultListMode = null;

	private RequestDispatcher requestDispatcher = null;


	public VariableInfoPanel(RequestDispatcher requestDispatcher){
		this.requestDispatcher = requestDispatcher;
		GridBagLayout gridBagLayout = new GridBagLayout();
		setLayout(gridBagLayout);

		defaultListMode = new DefaultListModel();
		variableList = new JList(defaultListMode);
		variableList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		variableList.addListSelectionListener(new VariableInfoAction());
		variableListScrollPanel = new JScrollPane();
		variableListScrollPanel.getViewport().setView(variableList);

		GuiUtility.addComponentByGridBagLayout(this, gridBagLayout, variableListScrollPanel, 0, 0, 1, 1, 1, 1, GridBagConstraints.BOTH, GridBagConstraints.CENTER, new Insets(0,5,5,5));

        TitledBorder titleBorder = new TitledBorder(new EtchedBorder(), sectionName);
        titleBorder.setTitleColor(Color.DARK_GRAY);
        setBorder(titleBorder);
	}

	public void updateVariableInfo(ArrayList<String> variableInfo , int defaultSelect){
		defaultListMode.clear();
		for(int i=0 ; i<variableInfo.size() ; i++){
			defaultListMode.addElement(variableInfo.get(i));
		}
		variableList.setModel(defaultListMode);
		variableList.setSelectedIndex(defaultSelect);
	}

	class VariableInfoAction implements ListSelectionListener{
		@Override
		public void valueChanged(ListSelectionEvent e) {
			if(e.getValueIsAdjusting()){
				if(variableList.getSelectedIndex() != -1){
					requestDispatcher.pushedVariableInfoList(variableList.getSelectedIndex());
				}
			}
		}
	}

	public void reset(){
		variableList.setSelectedIndex(-1);
		defaultListMode.clear();
		variableList.setModel(defaultListMode);
	}
}
