package br.com.studies.algorithms.week2;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import br.com.studies.algorithms.utils.FileUtils;
import br.com.studies.algorithms.week1.TestSortUtils;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class QuickSortTest {

	private QuickSort lomutos;
	private QuickSort hoares;
	private List<Object> unsorted;

	@Before
	public void setUp() {
		
		List<String> objs = FileUtils.readAllLines("br/com/studies/algorithms/week2/numbers-quick-sort.txt");
		unsorted = new ArrayList<>();
		for (String s : objs) {
			unsorted.add(Integer.parseInt(s));
		}

		lomutos = new QuickSortLomutos(new FirstElemPivotStrategy());
		hoares = new QuickSortHoares(new MedianElemPivotStrategy());
	}

	@Test
	public void quickSortLomutosFirstElPivot() {
		List sorted = new ArrayList<>(unsorted);
		int comp = lomutos.sort(sorted);
		System.out.println("Lomutos - Comparisons for Quicksort first pivot element: " + comp);

		Assert.assertTrue("List is not ordered", TestSortUtils.isSorted(sorted));
	}

	@Test
	public void quickSortLomutosLastElPivot() {
		lomutos.setPivotStrategy(new LastElemPivotStrategy());

		List sorted = new ArrayList<>(unsorted);
		int comp = lomutos.sort(sorted);
		System.out.println("Lomutos - Comparisons for Quicksort last pivot element: " + comp);

		Assert.assertTrue("List is not ordered", TestSortUtils.isSorted(sorted));
	}

	@Test
	public void quickSortLomutosMedianElPivot() {
		lomutos.setPivotStrategy(new MedianElemPivotStrategy());

		List sorted = new ArrayList<>(unsorted);
		int comp = lomutos.sort(sorted);
		System.out.println("Lomutos - Comparisons for Quicksort median element: " + comp);

		Assert.assertTrue("List is not ordered", TestSortUtils.isSorted(sorted));
	}

	@Test
	public void quickSortLomutosRandomElPivot() {
		lomutos.setPivotStrategy(new RandomPivotStrategy());

		List sorted = new ArrayList<>(unsorted);
		int comp = lomutos.sort(sorted);
		System.out.println("Lomutos - Comparisons for Quicksort random element: " + comp);

		Assert.assertTrue("List is not ordered", TestSortUtils.isSorted(sorted));
	}

	@Test
	@Ignore
	public void quickSortHoaresFirstElPivot() {
		List sorted = new ArrayList<>(unsorted);
		int comp = hoares.sort(sorted);
		System.out.println("Hoares - Comparisons for Quicksort first pivot element: " + comp);

		Assert.assertTrue("List is not ordered", TestSortUtils.isSorted(sorted));
	}
}
