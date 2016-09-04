package br.com.studies.algorithms.part2;

import java.util.ArrayList;
import java.util.List;

public class QuickSortC {

	public static void main(String[] args) {
		QuickSortC quickSort = new QuickSortC();

		int[] ls = { 1, 5, 3, 5, 2, 9, 29, 13, 12, 11, 10, 21, 6, 7, 8 };
		
		List<Object> lso = new ArrayList<>();
		for (int i = 0; i < ls.length; i++) {
			lso.add(ls[i]);
		}
		

		quickSort.sort(lso, 0, ls.length - 1);

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
		Object pivot = choosePivot(ls, low, high);
		while (low <= high) {
			while (toComparable(ls.get(low)).compareTo(pivot) < 0) {
				low++;
			}
			while (toComparable(ls.get(high)).compareTo(pivot) > 0) {
				high--;
			}
			if (low <= high) {
				swap(ls, low, high);
				low++;
				high--;
			}
		}

		return low;
	}
	
	private Object choosePivot(List<Object> ls, int low, int high){
		int index = low + (high - low) / 2;
		return ls.get(index);
	}

	private void swap(List<Object> ls, int i, int j) {
		Object tmp = ls.get(i);
		ls.set(i, ls.get(j));
		ls.set(j, tmp);
	}
	
	@SuppressWarnings("rawtypes")
	private Comparable toComparable(Object o){
		return (Comparable) o;
	}
}
