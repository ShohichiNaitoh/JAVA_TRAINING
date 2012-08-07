package interpret.gui.section;
import interpret.gui.InterpretFrameMain;
import interpret.gui.util.GuiUtility;
import interpret.logic.RequestDispatcher;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;


public class ClassInfoPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private static final String sectionName = "User Input Section";

	private static final String classInputLabelName = "Class Name";
	private JLabel classNameInputLabel = null;
	private JTextField classNameInputField = null;

	private static final String sizeLabelName = "Number of Elements";
	private JLabel sizeLabel = null;
	private JComboBox sizeComboBox = null;

	private static final String okButtonName = "     Create Objects !!     ";
	private JButton okButton = null;

	private RequestDispatcher requestDispatcher = null;


	public ClassInfoPanel(RequestDispatcher requestDispatcher){
		this.requestDispatcher = requestDispatcher;
        GridBagLayout gridBagLayout = new GridBagLayout();
        setLayout(gridBagLayout);

		classNameInputLabel = new JLabel(classInputLabelName);
		classNameInputField = new JTextField(30);

		GuiUtility.addComponentByGridBagLayout(this , gridBagLayout , new JLabel("        ") , 0 , 1 , 1 , 1 , GridBagConstraints.NONE, GridBagConstraints.WEST);
        GuiUtility.addComponentByGridBagLayout(this , gridBagLayout , classNameInputLabel , 1 , 1 , 1 , 1 , GridBagConstraints.BOTH, GridBagConstraints.EAST);
        GuiUtility.addComponentByGridBagLayout(this , gridBagLayout , classNameInputField , 3 , 1 , 2 , 1 , GridBagConstraints.BOTH, GridBagConstraints.WEST);
        GuiUtility.addComponentByGridBagLayout(this , gridBagLayout , new JLabel("        ") , 2 , 2 , 1 , 1 , GridBagConstraints.NONE, GridBagConstraints.WEST);

        sizeLabel = new JLabel(sizeLabelName);
        String[] combodata = new String[InterpretFrameMain.MAX_ARRAY_SIZE];
        for(int i=0 ; i<InterpretFrameMain.MAX_ARRAY_SIZE ; i++){
        	combodata[i] = String.valueOf(i+1);
        }
        sizeComboBox = new JComboBox(combodata);
        sizeComboBox.setSelectedIndex(0);
        GuiUtility.addComponentByGridBagLayout(this , gridBagLayout , sizeLabel , 1 , 3 , 1 , 1 , GridBagConstraints.BOTH, GridBagConstraints.EAST);
        GuiUtility.addComponentByGridBagLayout(this , gridBagLayout , sizeComboBox , 3 , 3 , 1 , 1 , GridBagConstraints.NONE, GridBagConstraints.WEST);

        okButton = new JButton(okButtonName);
        okButton.addActionListener(new ClassInfoAction());
        GuiUtility.addComponentByGridBagLayout(this , gridBagLayout , new JLabel("        ") , 6 , 1 , 1 , 1 , GridBagConstraints.NONE, GridBagConstraints.WEST);
        GuiUtility.addComponentByGridBagLayout(this , gridBagLayout , okButton , 3 , 5 , 1 , 1 , GridBagConstraints.NONE, GridBagConstraints.CENTER);
        //GuiUtility.addComponentByGridBagLayout(this , gridBagLayout , new JLabel("        ") , 6 , 6 , 1 , 1 , GridBagConstraints.NONE, GridBagConstraints.WEST);

        TitledBorder titleBorder = new TitledBorder(new EtchedBorder(), sectionName);
        titleBorder.setTitleColor(Color.BLUE);
        setBorder(titleBorder);
	}

	class ClassInfoAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == okButton){
				String className = classNameInputField.getText();
				int size = Integer.parseInt((String)sizeComboBox.getSelectedItem());
				requestDispatcher.pushedCreateObjects(className , size);
			}
		}

	}

}
