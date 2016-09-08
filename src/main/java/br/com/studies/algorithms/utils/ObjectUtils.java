package br.com.studies.algorithms.utils;

import java.util.List;

public final class ObjectUtils {

	private ObjectUtils() {
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
