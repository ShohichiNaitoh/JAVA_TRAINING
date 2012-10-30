package ch20.ex20_06;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.util.Iterator;

public class Properties {

	enum Operation{
		EQUAL , PLUS , MINUS,
	}

	public static Attributed readAttrs(Reader source) throws IOException{
		StreamTokenizer in = new StreamTokenizer(source);
		AttributedImpl attrs = new AttributedImpl();
		Attr attr = null;
		in.commentChar('#');
		in.ordinaryChar('/');

		Operation op = null;
		while(in.nextToken() != StreamTokenizer.TT_EOF){
			if(in.ttype == StreamTokenizer.TT_WORD){
				if(attr != null){
					attr.setValue(in.sval);
					attr = null;
				}else{
					attr = new Attr(in.sval);
				}
			}else if(in.ttype == '='){
				if(attr == null){
					throw new IOException("misplaced '='");
				}
				op = Operation.EQUAL;
			}else if(in.ttype == '+'){
				if(attr == null){
					throw new IOException("misplaced '='");
				}
				op = Operation.PLUS;
			}else if(in.ttype == '-'){
				if(attr == null){
					throw new IOException("misplaced '='");
				}
				op = Operation.MINUS;
			}else{
				if(attr == null){
					throw new IOException("bad Attr name");
				}
				if(op == null){
					throw new IOException("bad operation");
				}
				attr.setValue(new Double(in.nval));
				applyOperation(attrs , attr , op);
				attr = null;
				op = null;
			}
		}
		return attrs;
	}

	private static void applyOperation(AttributedImpl attrs , Attr attr , Operation op){
		switch(op){
		case EQUAL:
			attrs.add(attr);
			break;
		case PLUS:
			Attr temp = attrs.find(attr.getName());
			if(attrs.find(attr.getName()) != null){
				temp.setValue((Double)temp.getValue() + (Double)attr.getValue());
				attrs.add(temp);
			}else{
				attrs.add(attr);
			}
			break;
		case MINUS:
			Attr temp1 = attrs.find(attr.getName());
			if(temp1 != null){
				temp1.setValue((Double)temp1.getValue() - (Double)attr.getValue());
				attrs.add(temp1);
			}else{
				attr.setValue(-(Double)attr.getValue());
				attrs.add(attr);
			}
			break;
		default:
			throw new AssertionError("Unexpected Error Occured.");
		}
	}

	public static void main(String[] args){
		try {
			Attributed attrs = readAttrs(new FileReader(new File("./JPL/ch20/ex20_06/properties.txt")));
			for(Iterator ite = attrs.attrs() ; ite.hasNext() ; ){
				System.out.println(ite.next());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
