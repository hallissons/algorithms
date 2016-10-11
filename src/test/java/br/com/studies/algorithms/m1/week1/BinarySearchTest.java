package br.com.studies.algorithms.m1.week1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import br.com.studies.algorithms.m1.week1.BinarySearch;

public class BinarySearchTest {

	@Test
	public void testSimpleSearch() {
		BinarySearch search = new BinarySearch();
		int max = 500000;

		List ls = new ArrayList<>(Arrays.asList(new Integer[] { 1, 2, 3, 4, 5, 6 }));

		for (int i = 0; i < max; i++) {
			Random r = new Random();
			ls.add(r.nextInt(max));
		}
		Collections.sort(ls);
		
		Random r = new Random();
		int index = r.nextInt(ls.size());
		
		int i = search.run(ls, (Comparable) ls.get(index));

		Assert.assertTrue(i >= 0);
	}
}
