package br.com.studies.algorithms.m1.week1;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import br.com.studies.algorithms.utils.FileUtils;

public class Inversion {

	public static void main(String[] args) throws IOException, URISyntaxException {
		List<String> ls = FileUtils.readAllLines("br/com/studies/algorithms/week1/IntegerArray2.txt");
		List<Object> ints = new ArrayList<>();
		for (int i = 0; i < ls.size(); i++) {
			ints.add(Integer.parseInt(ls.get(i)));
		}

		Inversion ms = new Inversion();
		long count = ms.countInversions(ints);

		System.out.println("Inversion count: " + count);
	}

	public long countInversions(List<Object> ls) {
		int size = ls.size();
		long countLeft, countRight, countMerge;

		if (size == 1) {
			return 0;
		}

		int s1 = (size / 2) + (size % 2);
		List<Object> left = new ArrayList<>(ls.subList(0, s1));
		List<Object> right = new ArrayList<>(ls.subList(s1, size));

		countLeft = countInversions(left);
		countRight = countInversions(right);

		List<Object> result = new ArrayList<>();
		countMerge = merge(left, right, result);

		ls.clear();
		ls.addAll(result);

		System.out.println(countLeft + countRight + countMerge);
		return (countLeft + countRight + countMerge);
	}

	public long merge(List<Object> left, List<Object> right, List<Object> result) {
		int a = 0, b = 0;
		long count = 0;

		while ((a < left.size()) && (b < right.size())) {
			Comparable leftObj = (Comparable) left.get(a);
			if (leftObj.compareTo(right.get(b)) <= 0) {
				result.add(left.get(a++));
			} else {
				result.add(right.get(b++));
				count += left.size() - a;
			}
		}

		if (a == left.size()) {
			for (int i = b; i < right.size(); i++) {
				result.add(right.get(i));
			}
		} else {
			for (int i = a; i < left.size(); i++) {
				result.add(left.get(i));
			}
		}

		return count;
	}
}
