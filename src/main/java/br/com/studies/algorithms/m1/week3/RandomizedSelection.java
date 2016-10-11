package br.com.studies.algorithms.m1.week3;

import java.util.List;
import java.util.Random;

import br.com.studies.algorithms.m1.week2.Pivot;
import br.com.studies.algorithms.utils.ObjectUtils;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class RandomizedSelection implements Select {

	@Override
	public Object select(List ls, int index) {
		return randomSelect(ls, 0, ls.size() - 1, index);
	}

	private Object randomSelect(List ls, int low, int high, int index) {
		if (low == high) {
			return ls.get(low);
		}
		
		if (index == 0) {
			return -1;
		}

		int mid = randomPartition(ls, low, high);
		int length = (mid - low) + 1;

		if (index == length) { // the pivot value is the answer
			return ls.get(mid);
		} else if (index < length) {
			return randomSelect(ls, low, mid - 1, index);
		}

		return randomSelect(ls, mid + 1, high, index - length);
	}

	private int randomPartition(List ls, int low, int high) {
		Pivot pivot = getRandomIndex(ls, low, high);
		ObjectUtils.swap(ls, high, pivot.getIndex());
		return partition(ls, low, high, pivot);
	}

	private Pivot getRandomIndex(List ls, int low, int high) {
		Random random = new Random();
		int index = random.nextInt((high - low) + 1) + low;
		return new Pivot(ls.get(index), index);
	}

	private int partition(List<Object> ls, int low, int high, Pivot pivot) {
		int i = low - 1;
		for (int j = low; j < high; j++) {
			if (ObjectUtils.toComparable(ls.get(j)).compareTo(pivot.getObj()) < 0) {
				i++;
				ObjectUtils.swap(ls, j, i);
			}
		}
		ObjectUtils.swap(ls, high, i + 1);
		return i + 1;
	}
}
