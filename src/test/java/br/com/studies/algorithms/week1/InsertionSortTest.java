package br.com.studies.algorithms.week1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

public class InsertionSortTest {

	@Test
	public void testInsertionSort() {

		InsertionSort ins = new InsertionSort();
		int max = 10000;

		List<Object> ls = new ArrayList<>();

		for (int i = 0; i < max; i++) {
			Random r = new Random();
			ls.add(r.nextInt(max));
		}
		int comparisons = ins.sort(ls);

		System.out.println("Comparisons: " + comparisons);

		Assert.assertTrue(TestSortUtils.isSorted(ls));
	}
}
