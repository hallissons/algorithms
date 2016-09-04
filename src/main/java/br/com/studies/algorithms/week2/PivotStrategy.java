package br.com.studies.algorithms.week2;

import java.util.List;

public interface PivotStrategy {
	Pivot choose(List<Object> ls, int low, int high);
}
