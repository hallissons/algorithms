package br.com.studies.algorithms.part2.quicksort;

import java.util.List;
import java.util.Random;

public class RandomPivotStrategy implements PivotStrategy {

	@Override
	public Pivot choose(List<Object> ls, int low, int high) {
		Random random = new Random();
		int index = random.nextInt(high - low) + low;
		return new Pivot(ls.get(index), index);
	}
}
