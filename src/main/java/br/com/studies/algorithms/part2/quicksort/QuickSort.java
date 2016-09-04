package br.com.studies.algorithms.part2.quicksort;

import java.util.List;

import br.com.studies.algorithms.part1.SortUtils;

public abstract class QuickSort implements Sort {

	private PivotStrategy pivotStrategy;

	public QuickSort(PivotStrategy pivotStrategy) {
		this.pivotStrategy = pivotStrategy;
	}

	public PivotStrategy getPivotStrategy() {
		return pivotStrategy;
	}

	public void setPivotStrategy(PivotStrategy pivotStrategy) {
		if(pivotStrategy == null){
			throw new IllegalArgumentException("Pivot strategy should not be null");
		}
		this.pivotStrategy = pivotStrategy;
	}

	@Override
	public int sort(List<Object> ls) {
		return sort(ls, 0, ls.size());
	}

	private int sort(List<Object> ls, int low, int high) {
		int length = (high - low);
		if (length <= 0) {
			return 0;
		}
		Pivot pivot = pivotStrategy.choose(ls, low, high);
		SortUtils.swap(ls, low, pivot.getIndex());
		int index = partition(ls, low, high, pivot);
		return (length - 1) + sort(ls, low, index - 1) + sort(ls, index, high);
	}

	public abstract int partition(List<Object> ls, int low, int high, Pivot pivot);
}
