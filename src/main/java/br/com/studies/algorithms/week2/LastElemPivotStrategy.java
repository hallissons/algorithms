package br.com.studies.algorithms.week2;

import java.util.List;

public class LastElemPivotStrategy implements PivotStrategy {

	@Override
	public Pivot choose(List<Object> ls, int low, int high) {
		int index = high - 1;
		return new Pivot(ls.get(index), index);
	}
}
