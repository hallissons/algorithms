package br.com.studies.algorithms.part1;

import java.util.List;

public class BinarySearch implements Search {

	@Override
	public int run(List<Object> ls, Comparable element) {
		int max = ls.size() - 1;
		int min = 0;
		while (min <= max) {
			int mid = min + ((max - min) / 2);
			int comp = element.compareTo(ls.get(mid));

			if (element.equals(ls.get(mid))) {
				return mid;
			}

			if (comp < 0) {
				max = mid - 1;
			} else {
				min = mid + 1;
			}
		}
		return -(min);
	}

}
