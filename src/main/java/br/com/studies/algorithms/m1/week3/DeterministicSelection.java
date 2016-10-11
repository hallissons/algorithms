package br.com.studies.algorithms.m1.week3;

import java.util.ArrayList;
import java.util.List;

import br.com.studies.algorithms.m1.week2.Pivot;
import br.com.studies.algorithms.utils.ObjectUtils;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class DeterministicSelection implements Select {

	@Override
	public Object select(List ls, int index) {
		return select(ls, 0, ls.size() - 1, index);
	}

	private Object select(List ls, int low, int high, int index) {
		if (low == high) {
			return ls.get(low);
		}

		if (index == 0) {
			return -1;
		}

		int n = high - low + 1; // number of elements in the subarray

		// Divide the sublist into ceil(n / GROUP_SIZE) groups,
		// and find the median of each group by insertion sorting
		// the group and picking the median from the sorted list.
		final int GROUP_SIZE = 5; // size of each group
		int groups; // how many groups
		if (n % GROUP_SIZE == 0) {
			groups = n / GROUP_SIZE;
		} else {
			groups = (n / GROUP_SIZE) + 1;
		}

		// Create an list of medians.
		List medians = new ArrayList<>();

		// Fill in medians to contain the medians of the groups.
		for (int groupStart = low; groupStart <= high; groupStart += GROUP_SIZE) {
			int thisGroupSize = Math.min(high - groupStart + 1, GROUP_SIZE);
			insertionSortSubarray(ls, groupStart, thisGroupSize);
			medians.add(ls.get(groupStart + ((thisGroupSize - 1) / 2)));
		}

		// Recursively find the median of the medians.
		Object theMedian = select(medians, 0, groups - 1, (groups + 1) / 2);

		// We need to figure out where in the array the median of
		// the medians is. Go through the array, comparing
		// elements to theMedian until we find they're equal. We
		// are guaranteed that we will find an element equal to
		// the median, so we do not need to check for running off
		// the end of the sublist. Because we are doing no
		// arithmetic on the elements, it is safe to compare the
		// elements even if they are floating-point values. Note
		// also that running through the sublist does not
		// increase the asymptotic running time.
		int medianIndex = low;
		while (ObjectUtils.toComparable(theMedian).compareTo(ls.get(medianIndex)) != 0) {
			medianIndex++;
		}

		// Partition the input array around the median of the
	    // medians.
		ObjectUtils.swap(ls, high, medianIndex);
		Pivot pivot = new Pivot(ls.get(high), high);
		int mid = partition(ls, low, high, pivot);
		int length = (mid - low) + 1;

		if (index == length) { // the pivot value is the answer
			return ls.get(mid);
		} else if (index < length) {
			return select(ls, low, mid - 1, index);
		}

		return select(ls, mid + 1, high, index - length);
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

	/**
	 * Sorts a small sublist using insertion sort.
	 *
	 * @param array
	 *            The array containing the sublist to be sorted.
	 * @param start
	 *            Index of the start of the sublist.
	 * @param size
	 *            Number of elements in the sublist.
	 */
	private void insertionSortSubarray(List ls, int start, int size) {
		int end = start + size - 1;

		for (int j = start + 1; j <= end; j++) {
			Comparable k = ObjectUtils.toComparable(ls.get(j));

			// Insert array[j] into the sorted sequence array[start..j-1].
			int i = j - 1;

			while (i >= start && ObjectUtils.toComparable(ls.get(i)).compareTo(k) > 0) {
				ls.set(i + 1, ls.get(i));
				i--;
			}

			ls.set(i + 1, k);
		}
	}
}
