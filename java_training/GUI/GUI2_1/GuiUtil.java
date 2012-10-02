package GUI2_1;

import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

public class GuiUtil {

	public static void addComponentByGridBagLayout(Container container , GridBagLayout layout ,Component comp , int x , int y , int gridwidth , int gridheight , double weightx , double weighty , int fill , int anchor , Insets insets){
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = gridwidth;
        gbc.gridheight = gridheight;
        gbc.weightx = weightx;
        gbc.weighty=weighty;
        gbc.fill = fill;
        gbc.anchor = anchor;
        gbc.insets = insets;
        layout.setConstraints(comp, gbc);
        container.add(comp);
	}

}
