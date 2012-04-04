package ch01.ex01_15;

public class SimpleLookup implements Lookup2 {
	private String[] names;
	private Object[] values;

	public SimpleLookup(int size){
		names = new String[size];
		values = new Object[size];

		for(int i=0 ; i<size ; i++){
			names[i] = "";
			values[i] = "";
		}
	}


	@Override
	public Object find(String name) {
		if(name == null || name.equals("")){
			throw new IllegalArgumentException();
		}

		for(int i=0 ; i<names.length ; i++){
			if(names[i].equals(name)){
				return values[i];
			}
		}
		return null;
	}

	@Override
	public Object add(String name , Object obj) {
		if(name == null || name.equals("") ||  obj == null){
			throw new IllegalArgumentException();
		}

		for(int i=0 ; i<names.length ; i++){
			if(names[i].equals(name)){
				values[i] = obj;
				return values[i];
			}
		}

		for(int i=0 ; i<names.length ; i++){
			if(names[i].equals("")){
				names[i] = name;
				values[i] = obj;
				return values[i];
			}
		}

		return null;
	}


	@Override
	public Object remove(String name) {
		if(name == null || name.equals("")){
			throw new IllegalArgumentException();
		}

		for(int i=0 ; i<names.length ; i++){
			if(names[i].equals(name)){
				names[i] = "";
				values[i] = "";
			}
		}

		return null;
	}

	public String toString(){
		StringBuffer sb = new StringBuffer();
		for(int i=0 ; i<names.length ; i++){
			sb.append("name:" + names[i] + " , " + "value:" + values[i] + '\n');
		}
		return sb.toString();
	}
}
