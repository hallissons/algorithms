package br.com.studies.algorithms.week2;

import java.util.List;

public class FirstElemPivotStrategy implements PivotStrategy {

	@Override
	public Pivot choose(List<Object> ls, int low, int high) {
		return new Pivot(ls.get(low), low);
	}

}
