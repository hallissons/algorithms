package br.com.studies.algorithms.week2;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import br.com.studies.algorithms.week1.TestSortUtils;

public class QuickSortTest {

	private QuickSort lomutos;
	private QuickSort hoares;
	private List<Object> unsorted;

	@Before
	public void setUp() throws URISyntaxException, IOException {
		Path path = Paths.get(QuickSortTest.class.getResource("numbers-quick-sort.txt").toURI());
		List<String> objs = Files.readAllLines(path);
		unsorted = new ArrayList<>();
		for (String s : objs) {
			unsorted.add(Integer.parseInt(s));
		}
		
		lomutos = new QuickSortLomutos(new FirstElemPivotStrategy());
		hoares = new QuickSortHoares(new MedianElemPivotStrategy());
	}

	@Test
	public void quickSortLomutosFirstElPivot() throws IOException, URISyntaxException {
		List sorted = new ArrayList<>(unsorted);
		int comp = lomutos.sort(sorted);
		System.out.println("Lomutos - Comparisons for Quicksort first pivot element: " + comp);

		Assert.assertTrue("List is not ordered", TestSortUtils.isSorted(sorted));
	}

	@Test
	public void quickSortLomutosLastElPivot() throws IOException, URISyntaxException {
		lomutos.setPivotStrategy(new LastElemPivotStrategy());
		
		List sorted = new ArrayList<>(unsorted);
		int comp = lomutos.sort(sorted);
		System.out.println("Lomutos - Comparisons for Quicksort last pivot element: " + comp);

		Assert.assertTrue("List is not ordered", TestSortUtils.isSorted(sorted));
	}

	@Test
	public void quickSortLomutosMedianElPivot() throws IOException, URISyntaxException{
		//objs = new ArrayList<>(Arrays.asList(new String[]{ "8", "2", "4", "5", "7", "1" }));
		lomutos.setPivotStrategy(new MedianElemPivotStrategy());
		
		List sorted = new ArrayList<>(unsorted);
		int comp = lomutos.sort(sorted);
		System.out.println("Lomutos - Comparisons for Quicksort median element: "+ comp);
		
		Assert.assertTrue("List is not ordered", TestSortUtils.isSorted(sorted));
	}
	
	@Test
	public void quickSortLomutosRandomElPivot() throws IOException, URISyntaxException{
		//objs = new ArrayList<>(Arrays.asList(new String[]{ "8", "2", "4", "5", "7", "1" }));
		lomutos.setPivotStrategy(new RandomPivotStrategy());
		
		List sorted = new ArrayList<>(unsorted);
		int comp = lomutos.sort(sorted);
		System.out.println("Lomutos - Comparisons for Quicksort random element: "+ comp);
		
		Assert.assertTrue("List is not ordered", TestSortUtils.isSorted(sorted));
	}
	
	@Test
	@Ignore
	public void quickSortHoaresFirstElPivot() throws IOException, URISyntaxException {
		//unordered = new ArrayList<>(Arrays.asList(new Integer[]{ 8, 2, 4, 5, 7, 1 }));
		List sorted = new ArrayList<>(unsorted);
		int comp = hoares.sort(sorted);
		System.out.println("Hoares - Comparisons for Quicksort first pivot element: " + comp);

		Assert.assertTrue("List is not ordered", TestSortUtils.isSorted(sorted));
	}
}
