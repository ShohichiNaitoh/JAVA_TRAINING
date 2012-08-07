package interpret.gui.util;

import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

public class GuiUtility {

	public static void addComponentByGridBagLayout(Container container , GridBagLayout layout ,Component comp , int x , int y , int width , int height , int fill , int anchor){
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = width;
        gbc.gridheight = height;
        gbc.weightx=0.5;
        gbc.weighty=0.5;
        gbc.fill = fill;
        gbc.anchor = anchor;
        layout.setConstraints(comp, gbc);
        container.add(comp);
	}
}
