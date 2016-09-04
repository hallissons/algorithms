package br.com.studies.algorithms.part1;

import java.util.ArrayList;
import java.util.List;

public class MergeSort {

	public static void main(String[] args) {
		// List<String> ls = Arrays.asList(args);

		List<Object> ls = new ArrayList<>();
		ls.add(1);
		ls.add(9);
		ls.add(2);
		ls.add(3);
		ls.add(1);

		MergeSort ms = new MergeSort();
		List<Object> r = ms.sort(ls);

		System.out.println(r);
	}

	public List<Object> sort(List<Object> ls) {
		int size = ls.size();

		if (size == 1) {
			return ls;
		}

		int s1 = (size / 2) + (size % 2);
		List<Object> l1 = new ArrayList<>(ls.subList(0, s1));
		List<Object> l2 = new ArrayList<>(ls.subList(s1, size));

		l1 = sort(l1);
		l2 = sort(l2);

		return merge(l1, l2);
	}

	public List<Object> merge(List<Object> a, List<Object> b) {
		List<Object> ml = new ArrayList<>();
		while (!a.isEmpty() && !b.isEmpty()) {
			Comparable obj = (Comparable) a.get(0);
			if (obj.compareTo(b.get(0)) > 0) {
				ml.add(b.get(0));
				b.remove(0);
			} else {
				ml.add(obj);
				a.remove(obj);
			}
		}

		while (!a.isEmpty()) {
			ml.add(a.get(0));
			a.remove(0);
		}

		while (!b.isEmpty()) {
			ml.add(b.get(0));
			b.remove(0);
		}

		return ml;
	}
}
