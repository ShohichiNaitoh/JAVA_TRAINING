package ch24.ex24_01;

import java.util.Locale;
import java.util.ResourceBundle;

public class GlobalHello {

	public static void main(String[] args){
		Locale.setDefault(new Locale("ja" , "jp"));
		ResourceBundle res = ResourceBundle.getBundle("ch24.ex24_01.GlobalRes");

		String msg = null;
		if (args.length > 0){
			msg = res.getString(GlobalRes.GOODBYE);
		}else{
			msg = res.getString(GlobalRes.HELLO);
		}
		System.out.println(msg);
	}
}
