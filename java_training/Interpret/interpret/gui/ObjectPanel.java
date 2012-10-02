package interpret.gui;

import interpret.dispatcher.RequestDispatcher;
import interpret.gui.section.FieldInfoPanel;
import interpret.gui.section.MethodInfoPanel;
import interpret.gui.section.ResultOutputPanel;
import interpret.gui.section.UserInputPanel;
import interpret.gui.section.VariableInfoPanel;
import interpret.util.GuiUtil;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class ObjectPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public static final int MAX_ARRAY_SIZE = 10;
	private RequestDispatcher requestDispatcher = new RequestDispatcher();

	private UserInputPanel userInputPanel = new UserInputPanel(requestDispatcher);
	private VariableInfoPanel variableInfoPanel = new VariableInfoPanel(requestDispatcher);
	private FieldInfoPanel fieldInfoPanel = new FieldInfoPanel(requestDispatcher);
	private MethodInfoPanel methodInfoPanel = new MethodInfoPanel(requestDispatcher);
	private ResultOutputPanel resultOutputPanel = new ResultOutputPanel(requestDispatcher);

	public ObjectPanel() {
		requestDispatcher.setPanel(userInputPanel, variableInfoPanel, fieldInfoPanel, methodInfoPanel, resultOutputPanel);

		GridBagLayout gridBagLayout = new GridBagLayout();
        setLayout(gridBagLayout);
        GuiUtil.addComponentByGridBagLayout(this, gridBagLayout, userInputPanel, 0, 0, 3, 1, 1, 0.1, GridBagConstraints.VERTICAL, GridBagConstraints.CENTER, new Insets(0,0,0,0));
        GuiUtil.addComponentByGridBagLayout(this, gridBagLayout, variableInfoPanel, 0, 1, 1, 1, 0.5, 1, GridBagConstraints.BOTH, GridBagConstraints.WEST , new Insets(5,0,5,5));
        GuiUtil.addComponentByGridBagLayout(this, gridBagLayout, fieldInfoPanel, 1, 1, 1, 1, 1, 1, GridBagConstraints.BOTH, GridBagConstraints.CENTER, new Insets(5,5,5,5));
        GuiUtil.addComponentByGridBagLayout(this, gridBagLayout, methodInfoPanel, 2, 1, 1, 1, 1, 1, GridBagConstraints.BOTH, GridBagConstraints.EAST, new Insets(5,5,5,0));
        GuiUtil.addComponentByGridBagLayout(this, gridBagLayout, resultOutputPanel, 0, 2, 3, 1, 1, 0.5, GridBagConstraints.BOTH, GridBagConstraints.CENTER, new Insets(0,0,0,0));
	}

	public void closePanel(){
		requestDispatcher.removeRequestDispatcerFromList();
		requestDispatcher = null;
	}
}
