package br.com.studies.algorithms.week2;

import java.util.List;

import br.com.studies.algorithms.SortUtils;

public class QuickSortHoares extends QuickSort {
	
	public QuickSortHoares(PivotStrategy pivotStrategy){
		super(pivotStrategy);
	}

	public int partition(List<Object> ls, int low, int high, Pivot pivot) {
		high = high -1;
		while (low <= high) {
			while (SortUtils.toComparable(ls.get(low)).compareTo(pivot.getObj()) < 0) {
				low++;
			}
			while (SortUtils.toComparable(ls.get(high)).compareTo(pivot.getObj()) > 0) {
				high--;
			}
			if (low <= high) {
				SortUtils.swap(ls, low, high);
				low++;
				high--;
			}
		}
		return low;
	}
}
