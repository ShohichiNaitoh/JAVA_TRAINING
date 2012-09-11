package interpret.gui.util;

import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.lang.reflect.Modifier;

public class GuiUtility {

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

	public static String joinArgs(String[] args){
		StringBuffer sb = new StringBuffer();
		if(args != null){
			for(int i=0 ; i<args.length ; i++){
				if(i != args.length-1){
					sb.append(args[i].toString() +  ",");
				}else{
					sb.append(args[i].toString());
				}
			}
			return sb.toString();
		}else{
			return null;
		}
	}

	public static String adjustArgsFormat(String joinedArg){
		StringBuffer sb = new StringBuffer();
		if(joinedArg != null){
			sb.append(" ( ");
			if(joinedArg.length() != 0){
				String[] args = joinedArg.split(",");
				for(int i=0 ; i<args.length ; i++){
					sb.append(GuiUtility.getSimpleName(args[i]));
					if(i != args.length-1){
						sb.append(" arg" + (i+1) + " , ");
					}else{
						sb.append(" arg" + (i+1));
					}
				}
			}
			sb.append(" )");
		}
		return sb.toString();
	}

	public static String getSimpleName(String canonicalName){
		int index = canonicalName.lastIndexOf(".");
		if(index != -1){
			return  canonicalName.substring(index + 1);
		}
		return canonicalName;
	}

	public static String convertModifiers(int mod){
		StringBuilder sb = new StringBuilder();

		if(Modifier.isPublic(mod)){
			sb.append("public ");
		}else if(Modifier.isProtected(mod)){
			sb.append("protected ");
		}else if(Modifier.isPrivate(mod)){
			sb.append("private ");
		}

		if(Modifier.isStatic(mod)){
			sb.append("static ");
		}

		if(Modifier.isFinal(mod)){
			sb.append("final ");
		}

		if(Modifier.isAbstract(mod)){
			sb.append("abstract ");
		}

		if(Modifier.isInterface(mod)){
			sb.append("interface ");
		}

		if(Modifier.isSynchronized(mod)){
			sb.append("synchronized ");
		}

		if(Modifier.isVolatile(mod)){
			sb.append("volatile ");
		}

		return sb.toString();
	}

}
