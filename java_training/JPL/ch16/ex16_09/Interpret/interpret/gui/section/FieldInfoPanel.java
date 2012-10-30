package ch16.ex16_09.Interpret.interpret.gui.section;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
import javax.swing.table.TableColumn;

import ch16.ex16_09.Interpret.interpret.dispatcher.RequestDispatcher;
import ch16.ex16_09.Interpret.interpret.util.GuiUtil;
import ch16.ex16_09.Interpret.interpret.util.ReflectionUtil;


public class FieldInfoPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private static final String sectionName = "Field List Section";

	private static final String filedListName = "Field List";
	private JTable fieldTable = null;
	private JScrollPane fieldListScrollPanel = null;
	private DefaultTableModel tableMode = null;
	private String[] columnNames = {"modifier" , "type" , "name" , "value"};

	private static final String searchLabelName = "Search";
	private JLabel searchLabel = null;
	private JTextField searchTextField = null;

	private static final String resetButtonLableName = "Reset";
	private JButton resetButton = null;

	private RequestDispatcher requestDispatcher = null;
	private String[][] fieldInfo = null;


	public FieldInfoPanel(RequestDispatcher requestDispatcher){
		this.requestDispatcher = requestDispatcher;
		GridBagLayout gridBagLayout = new GridBagLayout();
        setLayout(gridBagLayout);

        searchLabel = new JLabel(searchLabelName);
        searchTextField = new JTextField();
        searchTextField.addKeyListener(new FieldInfoAction());

        resetButton = new JButton(resetButtonLableName);
        resetButton.addActionListener(new FieldInfoAction());

        tableMode = new DefaultTableModel(columnNames , 0){
        	@Override
        	public boolean isCellEditable(int row, int column) {
        		return false;
        	};
        };
        fieldTable = new JTable(tableMode);
        fieldTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        fieldTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        fieldTable.getSelectionModel().addListSelectionListener(new FieldInfoAction());
        fieldTable.addMouseListener(new FieldInfoAction());
        fieldListScrollPanel = new JScrollPane();
		fieldListScrollPanel.getViewport().setView(fieldTable);

        GuiUtil.addComponentByGridBagLayout(this, gridBagLayout, searchLabel, 0, 0, 1, 1, 0.1, 0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, new Insets(0,15,5,5));
        GuiUtil.addComponentByGridBagLayout(this, gridBagLayout, searchTextField, 1, 0, 1, 1, 1.0, 0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, new Insets(0,-20,5,0));
        GuiUtil.addComponentByGridBagLayout(this, gridBagLayout, resetButton, 2, 0, 1, 1, 0.2, 0, GridBagConstraints.NONE, GridBagConstraints.CENTER, new Insets(0,0,5,-15));
		GuiUtil.addComponentByGridBagLayout(this, gridBagLayout, fieldListScrollPanel, 0, 1, 3, 1, 1, 1, GridBagConstraints.BOTH, GridBagConstraints.CENTER, new Insets(5,5,5,5));

        TitledBorder titleBorder = new TitledBorder(new EtchedBorder(), sectionName);
        titleBorder.setTitleColor(Color.DARK_GRAY);
        setBorder(titleBorder);

        switchSearchFunction();
	}

	public void updateFieldInfo(String[][] fieldInfo){
		this.fieldInfo = fieldInfo;
		tableMode.setRowCount(0);
		for(int i=0 ; i<fieldInfo.length ; i++){
			String[] str = new String[columnNames.length];
			str[0] = fieldInfo[i][1];
			str[1] = ReflectionUtil.getSimpleName(fieldInfo[i][2]);
			str[2] = fieldInfo[i][3];
			str[3] = fieldInfo[i][4];
			tableMode.addRow(str);
		}

		switchSearchFunction();
	}


	public void resetSearchResult(){
		searchTextField.setText("");
		updateSearchResult();
	}

	public void reset(){
		tableMode.setRowCount(0);
		fieldInfo = null;
		searchTextField.setText("");
		switchSearchFunction();
	}

	public void selectExclusive(){
		fieldTable.clearSelection();
	}

	private void updateSearchResult(){
		String[] keywords = searchTextField.getText().split(" ");
		requestDispatcher.searchFieldInfoList(keywords);
	}

	private void switchSearchFunction(){
		if(requestDispatcher.getNumberOfField() == 0){
			searchTextField.setEditable(false);
			resetButton.setEnabled(false);
		}else{
			searchTextField.setEditable(true);
			resetButton.setEnabled(true);
		}
	}

	public String[] getKeywords(){
		return searchTextField.getText().split(" ");
	}

	class FieldInfoAction extends MouseAdapter implements ListSelectionListener , KeyListener , ActionListener{
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
				requestDispatcher.doubleClickedFieldInfoList(Integer.parseInt(fieldInfo[fieldTable.getSelectedRow()][0]));
			}
		}

		@Override
		public void keyTyped(KeyEvent e) {
			updateSearchResult();
			requestDispatcher.outputReset();
		}

		@Override
		public void keyPressed(KeyEvent e) {
			updateSearchResult();
		}

		@Override
		public void keyReleased(KeyEvent e) {
			updateSearchResult();

		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == resetButton){
				resetSearchResult();
			}
		}
	}
}
