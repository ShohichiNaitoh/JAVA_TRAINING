package interpret.gui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

public class ObjectsTabPane extends JTabbedPane {
	private static final long serialVersionUID = 1L;

	private ArrayList<ObjectPanel> objectPanelList = new ArrayList<ObjectPanel>();

	private String defaultTabName = "Object";
	private int currentLastIndexNumber = 5;
	private int	totalIndexNumber=5;


	public ObjectsTabPane() {
		for(int i=0 ; i<currentLastIndexNumber ; i++){
			addTabInternal(i , i);
		}

		AddTabIcon addTabIcon = new AddTabIcon(null);
		addTab("" , addTabIcon , null);

		addMouseListener(new AddTabListner(this));
		setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
	}

	private void addTabInternal(int objectNameIndex , int tabIndex){
		ObjectPanel objectPanel = new ObjectPanel();
		objectPanelList.add(objectPanel);
		addTab("" , null ,  objectPanel);

		JPanel tabPanel = new JPanel();
		tabPanel.setOpaque(false);
		tabPanel.add(new JLabel(defaultTabName+(objectNameIndex+1)+"     ") , BorderLayout.WEST);
		JLabel closeIconLabel = new JLabel(new CloseTabIcon(null));
		closeIconLabel.addMouseListener(new CloseTabListener(this));
		tabPanel.add(closeIconLabel , BorderLayout.EAST);

		setTabComponentAt(tabIndex, tabPanel);
	}

	public class CloseTabListener extends MouseAdapter{
		private JTabbedPane owner = null;

		public CloseTabListener(JTabbedPane owner){
			this.owner = owner;
		}

		public void mousePressed(MouseEvent e){
			Component tabComponent = ((Component)e.getSource()).getParent();
			int index = owner.indexOfTabComponent(tabComponent);
			if(index < 0){
				return ;
			}
			owner.remove(index);
			objectPanelList.get(index).closePanel();
			objectPanelList.remove(index);
			currentLastIndexNumber--;
			if(getSelectedIndex() == currentLastIndexNumber){
				setSelectedIndex(currentLastIndexNumber-1);
			}
		}
	}

	public class AddTabListner extends MouseAdapter{
		private JTabbedPane owner = null;

		public AddTabListner(JTabbedPane owner){
			this.owner = owner;
		}

		public void mousePressed(MouseEvent e){
			int index = getUI().tabForCoordinate(owner, e.getX(), e.getY());
			if(index < 0 || index != currentLastIndexNumber){
				return ;
			}
			owner.remove(index);
			addTabInternal(totalIndexNumber , currentLastIndexNumber);
			AddTabIcon addTabIcon = new AddTabIcon(null);
			addTab("" , addTabIcon , null);
			currentLastIndexNumber++;
			totalIndexNumber++;
			setSelectedIndex(currentLastIndexNumber-1);
		}
	}

	public class CloseTabIcon implements Icon {
	    private int xPosition = 0;
	    private int yPosition = 0;
	    private int width = 0;
	    private int height = 0;
	    private Icon icon = null;

	    public CloseTabIcon(Icon icon) {
	        this.icon=icon;
	        width=16;
	        height=16;
	    }

	    @Override
	    public void paintIcon(Component c, Graphics g, int x, int y) {
	        this.xPosition=x;
	        this.yPosition=y;
	        Color col=g.getColor();
	        g.setColor(Color.BLACK);
	        int yPos=y+2;
	        g.drawLine(x+1, yPos, x+12, yPos);
	        g.drawLine(x+1, yPos+13, x+12, yPos+13);
	        g.drawLine(x, yPos+1, x, yPos+12);
	        g.drawLine(x+13, yPos+1, x+13, yPos+12);
	        g.drawLine(x+3, yPos+3, x+10, yPos+10);
	        g.drawLine(x+3, yPos+4, x+9, yPos+10);
	        g.drawLine(x+4, yPos+3, x+10, yPos+9);
	        g.drawLine(x+10, yPos+3, x+3, yPos+10);
	        g.drawLine(x+10, yPos+4, x+4, yPos+10);
	        g.drawLine(x+9, yPos+3, x+3, yPos+9);
	        g.setColor(col);
	        if(icon != null) {
	            icon.paintIcon(c, g, x+width, yPos);
	        }
	    }

	    @Override
	    public int getIconWidth() {
	        return icon != null ? width + icon.getIconWidth() : width;
	    }

	    @Override
	    public int getIconHeight() {
	        return height;
	    }
	    public Rectangle getBounds() {
	        return new Rectangle(xPosition, yPosition, width, height);
	    }
	}

	public class AddTabIcon implements Icon {
	    private int xPosition = 0;
	    private int yPosition = 0;
	    private int width = 0;
	    private int height = 0;
	    private Icon icon = null;

	    public AddTabIcon(Icon icon) {
	        this.icon=icon;
	        width=18;
	        height=18;
	    }

	    @Override
	    public void paintIcon(Component c, Graphics g, int x, int y) {
	        this.xPosition=x;
	        this.yPosition=y;
	        Color col=g.getColor();
	        g.setColor(Color.RED);
	        int yPos=y+1;
	        g.drawLine(x+7, yPos, x+7, yPos+16);
	        g.drawLine(x+8, yPos, x+8, yPos+16);
	        g.drawLine(x+9, yPos, x+9, yPos+16);
	        g.drawLine(x, yPos+7, x+16, yPos+7);
	        g.drawLine(x, yPos+8, x+16, yPos+8);
	        g.drawLine(x, yPos+9, x+16, yPos+9);
	        g.setColor(col);
	        if(icon != null) {
	            icon.paintIcon(c, g, x+width, yPos);
	        }
	    }

	    @Override
	    public int getIconWidth() {
	        return icon != null ? width + icon.getIconWidth() : width;
	    }

	    @Override
	    public int getIconHeight() {
	        return height;
	    }
	    public Rectangle getBounds() {
	        return new Rectangle(xPosition, yPosition, width, height);
	    }
	}
}
