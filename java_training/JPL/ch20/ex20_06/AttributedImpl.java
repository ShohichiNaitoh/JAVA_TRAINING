package ch20.ex20_06;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class AttributedImpl implements Attributed, Iterable<Attr> {
	protected Map<String , Attr> attrTable = new HashMap<String , Attr>();

	@Override
	public void add(Attr newAttr) {
		attrTable.put(newAttr.getName() , newAttr);
	}

	@Override
	public Attr find(String attrName) {
		return attrTable.get(attrName);
	}

	@Override
	public Attr remove(String attrName) {
		return attrTable.remove(attrName);
	}

	@Override
	public Iterator<Attr> attrs() {
		return attrTable.values().iterator();
	}

	@Override
	public Iterator<Attr> iterator() {
		return attrs();
	}

}
