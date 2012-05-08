package ch03.ex03_12;

public interface SortHarness <T extends Comparable<?>>{

	T probe(int i);

	int compare(int i , int j);

	void swap(int i , int j);

}
