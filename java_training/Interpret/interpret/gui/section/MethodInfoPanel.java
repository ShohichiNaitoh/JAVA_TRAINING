package interpret.gui.section;
import interpret.dispatcher.RequestDispatcher;
import interpret.gui.section.VariableInfoPanel.VariableInfoAction;
import interpret.util.GuiUtil;
import interpret.util.ReflectionUtil;

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


public class MethodInfoPanel extends JPanel{

	private static final long serialVersionUID = 1L;

	private static final String sectionName = "Method List Section";

	private static final String methodListName = "Method List";
	private JTable methodTable = null;
	private JScrollPane methodListScrollPanel = null;
	private DefaultTableModel tableMode = null;
	private String[] columnNames = {"modifier" , "signiture"};

	private static final String searchLabelName = "Search";
	private JLabel searchLabel = null;
	private JTextField searchTextField = null;

	private static final String resetButtonLableName = "Reset";
	private JButton resetButton = null;

	private RequestDispatcher requestDispatcher = null;
	private String[][] constructorInfo = null;
	private String[][] methodInfo = null;


	public MethodInfoPanel(RequestDispatcher requestDispatcher){
		this.requestDispatcher = requestDispatcher;
        GridBagLayout gridBagLayout = new GridBagLayout();
        setLayout(gridBagLayout);

        searchLabel = new JLabel(searchLabelName);
        searchTextField = new JTextField();
        searchTextField.addKeyListener(new MethodInfoAction());

        resetButton = new JButton(resetButtonLableName);
        resetButton.addActionListener(new MethodInfoAction());

        tableMode = new DefaultTableModel(columnNames , 0){
        	@Override
        	public boolean isCellEditable(int row, int column) {
        		return false;
        	};
        };
        methodTable = new JTable(tableMode);
        methodTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        methodTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        int columnWidth = methodTable.getColumnModel().getTotalColumnWidth();
        TableColumn tableColumn = methodTable.getColumnModel().getColumn(0);
        tableColumn.setPreferredWidth((int)(columnWidth*0.2));
        tableColumn = methodTable.getColumnModel().getColumn(1);
        tableColumn.setPreferredWidth((int)(columnWidth*1.8));
        methodTable.getSelectionModel().addListSelectionListener(new MethodInfoAction());
        methodTable.addMouseListener(new MethodInfoAction());
        methodListScrollPanel = new JScrollPane();
		methodListScrollPanel.getViewport().setView(methodTable);

        GuiUtil.addComponentByGridBagLayout(this, gridBagLayout, searchLabel, 0, 0, 1, 1, 0.1, 0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, new Insets(0,15,5,5));
        GuiUtil.addComponentByGridBagLayout(this, gridBagLayout, searchTextField , 1, 0, 1, 1, 1.0, 0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, new Insets(0,-20,5,0));
        GuiUtil.addComponentByGridBagLayout(this, gridBagLayout, resetButton, 2, 0, 1, 1, 0.2, 0, GridBagConstraints.NONE, GridBagConstraints.CENTER, new Insets(0,0,5,-15));
        GuiUtil.addComponentByGridBagLayout(this, gridBagLayout, methodListScrollPanel, 0, 1, 3, 1, 1, 1, GridBagConstraints.BOTH, GridBagConstraints.CENTER, new Insets(5,5,5,5));

        TitledBorder titleBorder = new TitledBorder(new EtchedBorder(), sectionName);
        titleBorder.setTitleColor(Color.DARK_GRAY);
        setBorder(titleBorder);

		switchSearchFunction();
	}

	public void updateMethodInfo(String[][] constructorInfo , String[][] methodInfo){
		tableMode.setRowCount(0);
		this.constructorInfo = constructorInfo;
		this.methodInfo = methodInfo;

		for(int i=0 ; i<constructorInfo.length ; i++){
			String[] str = new String[columnNames.length];
			str[0] = constructorInfo[i][1];
			StringBuffer sb = new StringBuffer();
			sb.append(constructorInfo[i][2]);
			//sb.append(GuiUtility.getSimpleName(methodInfo[i][1] + "  "));
			sb.append(ReflectionUtil.getSimpleName(constructorInfo[i][3]));
			sb.append(ReflectionUtil.adjustArgsFormatBySimpleName(constructorInfo[i][4]));
			/*
			if(constructorInfo[i][2] != null){
				sb.append(" ( ");
				if(constructorInfo[i][2].length() != 0){
					String[] args = constructorInfo[i][2].split(",");
					for(int j=0 ; j<args.length ; j++){
						sb.append(GuiUtility.getSimpleName(args[j]));
						if(j != args.length-1){
							sb.append(" arg" + (j+1) + " , ");
						}else{
							sb.append(" arg" + (j+1));
						}
					}
				}
				sb.append(" )");
			}*/
			str[1] = sb.toString();
			tableMode.addRow(str);
		}

		for(int i=0 ; i<methodInfo.length ; i++){
			String[] str = new String[columnNames.length];
			str[0] = methodInfo[i][1];
			StringBuffer sb = new StringBuffer();
			sb.append(ReflectionUtil.getSimpleName(methodInfo[i][2]) + "  ");
			//sb.append(GuiUtility.getSimpleName(methodInfo[i][1] + "  "));
			sb.append(methodInfo[i][3]);
			sb.append(ReflectionUtil.adjustArgsFormatBySimpleName(methodInfo[i][4]));
			/*
			if(methodInfo[i][3] != null){
				sb.append(" ( ");
				if(methodInfo[i][3].length() != 0){
					String[] args = methodInfo[i][3].split(",");
					for(int j=0 ; j<args.length ; j++){
						sb.append(GuiUtility.getSimpleName(args[j]));
						if(j != args.length-1){
							sb.append(" arg" + (j+1) + " , ");
						}else{
							sb.append(" arg" + (j+1));
						}
					}
				}
				sb.append(" )");
			}*/
			str[1] = sb.toString();
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
		constructorInfo = null;
		methodInfo = null;
		searchTextField.setText("");
		switchSearchFunction();
	}

	public void selectExclusive(){
		methodTable.clearSelection();
	}

	public String[] getKeywords(){
		return searchTextField.getText().split(" ");
	}

	private void updateSearchResult(){
		String[] keywords = searchTextField.getText().split(" ");
		requestDispatcher.searchMethodInfoList(keywords);
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

	class MethodInfoAction extends MouseAdapter implements ListSelectionListener , KeyListener , ActionListener{
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
				if(methodTable.getSelectedRow() < constructorInfo.length){
					requestDispatcher.doubleClickedMethodInfoList(Integer.parseInt(constructorInfo[methodTable.getSelectedRow()][0]));
				}else{
					requestDispatcher.doubleClickedMethodInfoList(Integer.parseInt(methodInfo[methodTable.getSelectedRow()-constructorInfo.length][0]));
				}
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
