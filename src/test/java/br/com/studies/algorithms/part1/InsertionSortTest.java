package br.com.studies.algorithms.part1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

public class InsertionSortTest {

	@Test
	public void testInsertionSort() {

		InsertionSort ins = new InsertionSort();
		int max = 6;

		int[] a = { 1, 2, 3, 4, 5, 6 };
		List<Object> ls = new ArrayList<>();

		for (int i = 0; i < max; i++) {
			//Random r = new Random();
			//ls.add(r.nextInt(max));
			ls.add(a[i]);
		}
		int comparisons = ins.sort(ls);
		
		System.out.println("Comparisons: "+comparisons);

		Assert.assertTrue(TestSortUtils.isOrdered(ls));
	}
}
