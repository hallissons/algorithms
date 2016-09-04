package br.com.studies.algorithms.part2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuickSort {

	public static void main(String[] args) {
		QuickSort quickSort = new QuickSort();

		int[] ls = { 1, 5, 3, 5, 2, 9, 29, 13, 12, 11, 10, 21, 6, 7, 8, 3, 21, 14, 78954564, 12, 1, -1 };

		List<Object> lso = new ArrayList<>();
		for (int i = 0; i < ls.length; i++) {
			lso.add(ls[i]);
		}

		quickSort.sort(lso, 0, ls.length);

		Arrays.sort(ls);

		/*for (int i = 0; i < ls.length; i++) {
			if (!lso.get(i).equals(ls[i])) {
				throw new IllegalArgumentException("Array not sorted");
			}
		}*/

		System.out.println(lso);

	}

	public void sort(List<Object> ls, int low, int high) {
		int index = partition(ls, low, high);
		if (low < index - 1) {
			sort(ls, low, index - 1);
		}
		if (index < high) {
			sort(ls, index, high);
		}
	}

	@SuppressWarnings("unchecked")
	private int partition(List<Object> ls, int low, int high) {
		Pivot pivot = choosePivot(ls, low, high);
		int i = low + 1;
		for (int j = low + 1; j < high; j++) {
			if (toComparable(ls.get(j)).compareTo(pivot.getObj()) < 0) {
				swap(ls, j, i);
				i++;
			}
		}

		swap(ls, low, i - 1);

		return i;
	}

	private Pivot choosePivot(List<Object> ls, int low, int high) {
		//int index = low;
		int index = low + (high - low) / 2;
		Object obj = ls.get(index);
		return new Pivot(obj, index);
	}

	private void swap(List<Object> ls, int i, int j) {
		Object tmp = ls.get(i);
		ls.set(i, ls.get(j));
		ls.set(j, tmp);
	}

	@SuppressWarnings("rawtypes")
	private Comparable toComparable(Object o) {
		return (Comparable) o;
	}

	class Pivot {
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
}
