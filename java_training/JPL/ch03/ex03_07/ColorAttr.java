package ch03.ex03_07;

public class ColorAttr extends Attr {

	private ScreenColor myColor;

	public ColorAttr(String name , Object value){
		super(name , value);
		decodeColor();
	}

	public ColorAttr(String name){
		this(name , "transparent");
	}

	public ColorAttr(String name , ScreenColor value){
		super(name , value.toString());
		myColor = value;
	}

	public Object setValue(Object newValue){
		Object retval = super.setValue(newValue);
		decodeColor();
		return retval;
	}

	public ScreenColor setValue(ScreenColor newValue){
		super.setValue(newValue.toString());
		ScreenColor oldValue = myColor;
		myColor = newValue;
		return oldValue;
	}

	public ScreenColor getColor(){
		return myColor;
	}

	protected void decodeColor(){
		if(getValue() == null){
			myColor = null;
		}else{
			myColor = new ScreenColor(getValue());
		}
	}

	public boolean equals(Object obj){
		if( !(obj instanceof ColorAttr) ){
			return false;
		}

		ColorAttr ca = (ColorAttr) obj;
		if( this.getName().equals(ca.getName()) && this.myColor.equals(ca.getColor()) ){
			return true;
		}else{
			return false;
		}
	}

	public int hashCode(){
		return getName().hashCode() * 31 + myColor.hashCode();
	}
}
