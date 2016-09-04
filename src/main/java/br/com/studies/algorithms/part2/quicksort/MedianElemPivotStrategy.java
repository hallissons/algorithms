package br.com.studies.algorithms.part2.quicksort;

import java.util.List;

import br.com.studies.algorithms.part1.SortUtils;

public class MedianElemPivotStrategy implements PivotStrategy {

	@Override
	public Pivot choose(List<Object> ls, int low, int high) {
		int index = medianOfThree(ls, low, high - 1);
		return new Pivot(ls.get(index), index);
	}

	/**
	 * Returns the index of the median value of the first, middle, and last
	 * elements of a list. For lists with even number of elements, the index of
	 * number of elements/2 - 1 will be used as the middle index of the list.
	 * 
	 * @param ls
	 *            - a list of integers with at least one element and no
	 *            duplicate values.
	 * @param startIndex
	 *            - the start of the subset of the list to be used. Assumed that
	 *            0 < startIndex <= stopIndex
	 * @param endIndex
	 *            - the end of the subset of the list to be used. Assumed that
	 *            startIndex <= stopIndex < number of elements in list
	 * @return - index of the chosen median value
	 */
	public static int medianOfThree(List<Object> ls, int startIndex, int endIndex) {
		int aIndex = startIndex;
		int bIndex = (startIndex + endIndex) / 2;
		int cIndex = endIndex;

		Object a = ls.get(aIndex);
		Object b = ls.get(bIndex);
		Object c = ls.get(cIndex);

		if (SortUtils.toComparable(a).compareTo(b) > 0) {
			if (SortUtils.toComparable(c).compareTo(a) > 0) {
				return aIndex;
			} else if (SortUtils.toComparable(c).compareTo(b) < 0) {
				return bIndex;
			} else {
				return cIndex;
			}
		} else {
			if (SortUtils.toComparable(c).compareTo(a) < 0) {
				return aIndex;
			} else if (SortUtils.toComparable(c).compareTo(b) > 0) {
				return bIndex;
			} else {
				return cIndex;
			}
		}
	}
}
