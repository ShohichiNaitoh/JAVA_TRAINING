package ch16.ex16_09.Interpret.interpret.gui;
import interpret.util.GuiUtil;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.JFrame;


public class InterpretFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	private static final String windowTitle = "Interpret";
	private ObjectsTabPane objectsTabPane = new ObjectsTabPane();

	public InterpretFrame() {
        setTitle(windowTitle);
        Rectangle bounds = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        setBounds(bounds);
        setResizable(false);

        GridBagLayout gridBagLayout = new GridBagLayout();
        setLayout(gridBagLayout);
        Container contentPane = getContentPane();
        GuiUtil.addComponentByGridBagLayout(contentPane, gridBagLayout, objectsTabPane, 0, 0, 1, 1, 1, 1, GridBagConstraints.BOTH, GridBagConstraints.CENTER, new Insets(0,0,0,0));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
