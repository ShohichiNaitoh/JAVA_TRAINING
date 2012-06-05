package ch10.ex10_02;

public class ReplaceStringMain {

	public String replace(String str){
		String replacedStr = "";
		for(int i=0 ; i<str.length() ; i++){
			switch(str.charAt(i)){
			case '\n':
				replacedStr += "\\\n";
				break;
			case '\t':
				replacedStr += "\\\t";
				break;
			case '\b':
				replacedStr += "\\\b";
				break;
			case '\r':
				replacedStr += "\\\r";
				break;
			case '\f':
				replacedStr += "\\\f";
				break;
			case '\\':
				replacedStr += "\\\\";
				break;
			case '\'':
				replacedStr += "\\\'";
				break;
			case '\"':
				replacedStr += "\\\"";
				break;
			default:
				replacedStr += str.charAt(i);
				break;
			}
		}
		return replacedStr;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ReplaceStringMain replaceString = new ReplaceStringMain();
		System.out.println(replaceString.replace("aaaa\t \' \""));
	}
}
