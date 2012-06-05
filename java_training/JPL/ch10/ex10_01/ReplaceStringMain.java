package ch10.ex10_01;

public class ReplaceStringMain {

	public String replace(String str){
		String replacedStr = "";
		for(int i=0 ; i<str.length() ; i++){
			char ch = str.charAt(i);
			if(ch == '\n'){
				replacedStr += "\\\n";
			}else if(ch == '\t'){
				replacedStr += "\\\t";
			}else if(ch == '\b'){
				replacedStr += "\\\b";
			}else if(ch == '\r'){
				replacedStr += "\\\r";
			}else if(ch == '\f'){
				replacedStr += "\\\f";
			}else if(ch == '\\'){
				replacedStr += "\\\\";
			}else if(ch == '\''){
				replacedStr += "\\\'";
			}else if(ch == '\"'){
				replacedStr += "\\\"";
			}else{
				replacedStr += ch;
			}
		}
		return replacedStr ;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ReplaceStringMain replaceString = new ReplaceStringMain();
		System.out.println(replaceString.replace("aaaa\t \' \""));
	}
}
