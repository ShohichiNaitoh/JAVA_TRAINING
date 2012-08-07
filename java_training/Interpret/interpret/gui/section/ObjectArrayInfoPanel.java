package interpret.gui.section;

import interpret.gui.InterpretFrameMain;
import interpret.gui.util.GuiUtility;
import interpret.logic.RequestDispatcher;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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

public class ObjectArrayInfoPanel extends JPanel{

	private static final long serialVersionUID = 1L;

	private static final String sectionName = "Object List Section";

	private static final String objectListName = "Object List";
	private JLabel objectListLabel = null;
	private JList objectList = null;
	private JScrollPane objectListScrollPanel = null;
	private DefaultListModel defaultListMode = null;

	private int previousIndex = -1;
	private boolean firstflag = true;
	private RequestDispatcher requestDispatcher = null;


	public ObjectArrayInfoPanel(RequestDispatcher requestDispatcher){
		this.requestDispatcher = requestDispatcher;
		GridBagLayout gridBagLayout = new GridBagLayout();
		setLayout(gridBagLayout);

		objectListLabel = new JLabel(objectListName);
		defaultListMode = new DefaultListModel();
		objectList = new JList(defaultListMode);
		objectList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		objectList.addListSelectionListener(new ObjArryInfoAction());
		objectListScrollPanel = new JScrollPane();
		objectListScrollPanel.getViewport().setView(objectList);
		objectListScrollPanel.setPreferredSize(new Dimension(200, 100));

		//GuiUtility.addComponentByGridBagLayout(this , gridBagLayout , new JLabel("        ") , 0 , 0 , 1 , 1 , GridBagConstraints.BOTH, GridBagConstraints.NORTHWEST);
		//GuiUtility.addComponentByGridBagLayout(this , gridBagLayout , objectListLabel , 0 , 0 , 1 , 1 , GridBagConstraints.NONE, GridBagConstraints.NORTHWEST);
		GuiUtility.addComponentByGridBagLayout(this , gridBagLayout , objectListScrollPanel , 0 , 0 , 1 , 1 , GridBagConstraints.BOTH, GridBagConstraints.NORTHWEST);
		//GuiUtility.addComponentByGridBagLayout(this , gridBagLayout , new JLabel("        ") , 2 , 3 , 1 , 1 , GridBagConstraints.BOTH, GridBagConstraints.NORTHWEST);

        TitledBorder titleBorder = new TitledBorder(new EtchedBorder(), sectionName);
        titleBorder.setTitleColor(Color.DARK_GRAY);
        setBorder(titleBorder);
	}

	public void updateObjectArrayInfo(ArrayList<String> objectTypeArrays , int defaultSelect){
		defaultListMode.clear();
		for(int i=0 ; i<objectTypeArrays.size() ; i++){
			defaultListMode.addElement(objectTypeArrays.get(i));
		}
		objectList.setModel(defaultListMode);
		objectList.setSelectedIndex(defaultSelect);
	}

	class ObjArryInfoAction implements ListSelectionListener{
		@Override
		public void valueChanged(ListSelectionEvent e) {
			if(e.getValueIsAdjusting()){
				if(objectList.getSelectedIndex() != -1){
					System.out.println(objectList.getSelectedIndex());
					requestDispatcher.pushedObjectArray(objectList.getSelectedIndex());
				}
			}
		}
	}

	public void reset(){
		objectList.setSelectedIndex(-1);
		defaultListMode.clear();
		objectList.setModel(defaultListMode);
	}
}
