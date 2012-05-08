package ch04.ex04_02;

public interface SortHarness <T extends Comparable<?>>{

	T probe(int i);

	int compare(int i , int j);

	void swap(int i , int j);

}
