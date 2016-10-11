package br.com.studies.algorithms.m1.week2;

public class Pivot {
	
	private final Object obj;
	private final int index;

	public Pivot(Object obj, int index) {
		this.obj = obj;
		this.index = index;
	}

	public Object getObj() {
		return obj;
	}

	public int getIndex() {
		return index;
	}
	
	public String toString() {
		return String.format("Object: %s, Index: %s", obj, index);
	}
}
