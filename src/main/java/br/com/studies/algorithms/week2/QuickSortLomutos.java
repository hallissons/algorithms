package br.com.studies.algorithms.week2;

import java.util.List;

import br.com.studies.algorithms.SortUtils;

public class QuickSortLomutos extends QuickSort {
	
	public QuickSortLomutos(PivotStrategy pivotStrategy) {
		super(pivotStrategy);
	}

	@Override
	public int partition(List<Object> ls, int low, int high, Pivot pivot) {
		int i = low + 1;
		for (int j = low + 1; j < high; j++) {
			if (SortUtils.toComparable(ls.get(j)).compareTo(pivot.getObj()) < 0) {
				SortUtils.swap(ls, j, i);
				i++;
			}
		}
		SortUtils.swap(ls, low, i - 1);
		return i;
	}
}
