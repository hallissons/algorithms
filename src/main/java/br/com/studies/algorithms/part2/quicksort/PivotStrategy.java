package br.com.studies.algorithms.part2.quicksort;

import java.util.List;

public interface PivotStrategy {
	Pivot choose(List<Object> ls, int low, int high);
}
