package interpret.gui;
import interpret.gui.section.ClassInfoPanel;
import interpret.gui.section.FieldInfoPanel;
import interpret.gui.section.MethodInfoPanel;
import interpret.gui.section.ObjectArrayInfoPanel;
import interpret.gui.section.ResultOutputPanel;
import interpret.gui.util.GuiUtility;
import interpret.logic.RequestDispatcher;

import java.awt.Container;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.JFrame;


public class InterpretFrameMain extends JFrame {
	private static final long serialVersionUID = 1L;
	public static final int MAX_ARRAY_SIZE = 10;

	private RequestDispatcher requestDispatcher = new RequestDispatcher();

	private static final String windowTitle = "Interpret program";
	private ClassInfoPanel classInfoPanel = new ClassInfoPanel(requestDispatcher);
	private ObjectArrayInfoPanel objectArrayInfoPanel = new ObjectArrayInfoPanel(requestDispatcher);
	private FieldInfoPanel fieldInfoPanel = new FieldInfoPanel(requestDispatcher);
	private MethodInfoPanel methodInfoPanel = new MethodInfoPanel(requestDispatcher);
	private ResultOutputPanel resultOutputPanel = new ResultOutputPanel(requestDispatcher);

	public InterpretFrameMain() {
        requestDispatcher.setPanel(this, classInfoPanel, objectArrayInfoPanel, fieldInfoPanel, methodInfoPanel, resultOutputPanel);

        setTitle(windowTitle);
        Rectangle bounds = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        setBounds(bounds);
        setResizable(false);

        GridBagLayout gridBagLayout = new GridBagLayout();
        setLayout(gridBagLayout);
        Container contentPane = getContentPane();
        GuiUtility.addComponentByGridBagLayout(contentPane, gridBagLayout, classInfoPanel, 0, 0, 3, 1, GridBagConstraints.VERTICAL, GridBagConstraints.CENTER);
        GuiUtility.addComponentByGridBagLayout(contentPane, gridBagLayout, objectArrayInfoPanel, 0, 1, 1, 2, GridBagConstraints.BOTH, GridBagConstraints.CENTER);
        GuiUtility.addComponentByGridBagLayout(contentPane, gridBagLayout, fieldInfoPanel, 1, 1, 1, 2, GridBagConstraints.BOTH, GridBagConstraints.CENTER);
        GuiUtility.addComponentByGridBagLayout(contentPane, gridBagLayout, methodInfoPanel, 2, 1, 1, 2, GridBagConstraints.BOTH, GridBagConstraints.CENTER);
        GuiUtility.addComponentByGridBagLayout(contentPane, gridBagLayout, resultOutputPanel, 0, 3, 3, 1, GridBagConstraints.BOTH, GridBagConstraints.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String [] args) {
       InterpretFrameMain interpretFrame = new InterpretFrameMain();
       interpretFrame.setVisible(true);
    }
}
