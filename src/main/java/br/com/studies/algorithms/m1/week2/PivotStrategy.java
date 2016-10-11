package br.com.studies.algorithms.m1.week2;

import java.util.List;

public interface PivotStrategy {
	Pivot choose(List<Object> ls, int low, int high);
}
