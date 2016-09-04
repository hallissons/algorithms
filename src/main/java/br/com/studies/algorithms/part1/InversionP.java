package br.com.studies.algorithms.part1;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class InversionP {

	public static void main(String[] args) throws IOException, URISyntaxException {
		Path p = Paths.get(InversionP.class.getResource("IntegerArray2.txt").toURI());
		List<String> ls = Files.readAllLines(p);
		List<Object> ints = new ArrayList<>();
		for (int i = 0; i < ls.size(); i++) {
			ints.add(Integer.parseInt(ls.get(i)));
		}

		InversionP ms = new InversionP();
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
