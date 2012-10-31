package ch21.ex21_04;

import java.util.ListIterator;
import java.util.NoSuchElementException;

public class ShortStrings implements ListIterator<String> {
	private ListIterator<String> strings;
	private String nextShort;
	private String previousShort;
	private final int maxLen;

	public ShortStrings(ListIterator<String> strings , int maxLen){
		this.strings = strings;
		this.maxLen = maxLen;
		nextShort = null;
		previousShort = null;
	}

	@Override
	public boolean hasNext() {
		if(nextShort != null){
			return true;
		}
		while(strings.hasNext()){
			nextShort = strings.next();
			if(nextShort.length() <= maxLen){
				return true;
			}
		}
		nextShort = null;
		return false;
	}

	@Override
	public String next() {
		if(nextShort == null && !hasNext()){
			throw new NoSuchElementException();
		}
		String n = nextShort;
		nextShort = null;
		return n;
	}

	@Override
	public boolean hasPrevious() {
		if(previousShort != null){
			return true;
		}
		while(strings.hasPrevious()){
			previousShort = strings.previous();
			if(previousShort.length() <= maxLen){
				return true;
			}
		}
		previousShort = null;
		return false;
	}

	@Override
	public String previous() {
		if(previousShort == null && !hasPrevious()){
			throw new NoSuchElementException();
		}
		String n = previousShort;
		previousShort = null;
		return n;
	}

	@Override
	public int nextIndex() {
		if(nextShort == null && !hasNext()){
			return strings.nextIndex();
		}
		while(strings.hasNext()){
			int index = strings.nextIndex();
			nextShort = strings.next();
			if(nextShort.length() <= maxLen){
				return index;
			}
		}
		return strings.nextIndex();
	}

	@Override
	public int previousIndex() {
		if(previousShort == null && !hasPrevious()){
			throw new NoSuchElementException();
		}
		while(strings.hasPrevious()){
			int index = strings.previousIndex();
			previousShort = strings.previous();
			if(previousShort.length() <= maxLen){
				return index;
			}
		}
		return strings.previousIndex();
	}

	@Override
	public void remove() {

	}

	@Override
	public void set(String e) {

	}

	@Override
	public void add(String e) {
		strings.add(e);
	}

}
