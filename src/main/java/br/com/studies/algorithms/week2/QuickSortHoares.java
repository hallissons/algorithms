package br.com.studies.algorithms.week2;

import java.util.List;

import br.com.studies.algorithms.utils.ObjectUtils;

public class QuickSortHoares extends QuickSort {
	
	public QuickSortHoares(PivotStrategy pivotStrategy){
		super(pivotStrategy);
	}

	public int partition(List<Object> ls, int low, int high, Pivot pivot) {
		high = high -1;
		while (low <= high) {
			while (ObjectUtils.toComparable(ls.get(low)).compareTo(pivot.getObj()) < 0) {
				low++;
			}
			while (ObjectUtils.toComparable(ls.get(high)).compareTo(pivot.getObj()) > 0) {
				high--;
			}
			if (low <= high) {
				ObjectUtils.swap(ls, low, high);
				low++;
				high--;
			}
		}
		return low;
	}
}
