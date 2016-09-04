package br.com.studies.algorithms;

import java.util.List;

public final class SortUtils {

	private SortUtils() {
	}

	public static void swap(List<Object> ls, int i, int j) {
		Object tmp = ls.get(i);
		ls.set(i, ls.get(j));
		ls.set(j, tmp);
	}
	
	public static <T> Comparable<T> toComparable(Object o) {
		return (Comparable<T>) o;
	}
}
