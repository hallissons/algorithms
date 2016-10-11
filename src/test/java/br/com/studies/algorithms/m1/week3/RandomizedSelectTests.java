package br.com.studies.algorithms.m1.week3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import br.com.studies.algorithms.m1.week3.RandomizedSelection;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class RandomizedSelectTests {

	private static final int MAX = 10;

	@Test
	public void selectRandom() {
		RandomizedSelection rs = new RandomizedSelection();

		List ls = new ArrayList<>();
		for (int i = 0; i < MAX; i++) {
			Random r = new Random();
			int n = r.nextInt(MAX);
			if (!ls.contains(n)) {
				ls.add(n);
			}
		}

		Random r = new Random();
		int index = r.nextInt(ls.size() - 1) + 1;
		Object obj = rs.select(ls, index);
		
		Collections.sort(ls);
		Object expected = ls.get(index - 1);
		
		Assert.assertEquals(expected, obj);
	}
}
