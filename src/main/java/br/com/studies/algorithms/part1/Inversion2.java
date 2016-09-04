package br.com.studies.algorithms.part1;

import java.util.ArrayList;
import java.util.List;

public class Inversion2 {
	
	private class SortedInversion {
		public int count;
		public List<Object> ls;
		
		public SortedInversion(){
			count = 0;
			ls = new ArrayList<>();
		}
	}

	public static void main(String[] args) {
		// List<String> ls = Arrays.asList(args);
		List<Object> ls = new ArrayList<>();
		ls.add(1);
		ls.add(3);
		ls.add(2);
		ls.add(4);
		ls.add(5);

		Inversion2 ms = new Inversion2();
		SortedInversion si = ms.new SortedInversion();
		ms.sortAndCountInversions(ls, si);

		System.out.println("Inversion count: " + si.count);
	}

	public SortedInversion sortAndCountInversions(List<Object> ls, SortedInversion si) {
		int size = ls.size();
		
		if (size == 1) {
			si.ls.addAll(ls);
			return si;
		}

		int s1 = (size / 2) + (size % 2);
		List<Object> l1 = new ArrayList<>(ls.subList(0, s1));
		List<Object> l2 = new ArrayList<>(ls.subList(s1, size));

		sortAndCountInversions(l1, si);
		sortAndCountInversions(l2, si);
		merge(l1, l2, si);
		
		return si;
	}

	public void merge(List<Object> a, List<Object> b, SortedInversion si) {
		while (!a.isEmpty() && !b.isEmpty()) {
			Comparable obj = (Comparable) a.get(0);
			if (obj.compareTo(b.get(0)) > 0) {
				si.ls.add(b.get(0));
				b.remove(0);
				si.count++;
			} else {
				si.ls.add(obj);
				a.remove(obj);
			}
		}

		while (!a.isEmpty()) {
			si.ls.add(a.get(0));
			a.remove(0);
		}

		while (!b.isEmpty()) {
			si.ls.add(b.get(0));
			b.remove(0);
		}

	}
}
