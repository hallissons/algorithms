package br.com.studies.algorithms.part1;

import java.util.List;

import br.com.studies.algorithms.part2.quicksort.Sort;

public class InsertionSort implements Sort {

	@Override
	public int sort(List<Object> ls) {
		int compares = 0;
		for (int j = 1; j < ls.size(); j++) {
			Object key = ls.get(j);
			// Insert a[j] into the sorted sequence a[0..j-1] .
			int i = j - 1;
			// a[i] is greater than key
			while (i >= 0 && SortUtils.toComparable(ls.get(i)).compareTo(key) > 0) {
				ls.set(i + 1, ls.get(i));
				i--;
				compares++;
			}
			ls.set(i + 1, key);
		}
		return compares;
	}

}
