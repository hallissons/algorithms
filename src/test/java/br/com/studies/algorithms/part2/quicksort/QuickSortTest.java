package br.com.studies.algorithms.part2.quicksort;

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

import br.com.studies.algorithms.part1.TestSortUtils;

public class QuickSortTest {

	private QuickSort lomutos;
	private QuickSort hoares;
	private List<Object> unordered;

	@Before
	public void setUp() throws URISyntaxException, IOException {
		Path path = Paths.get(QuickSortTest.class.getResource("numbers-quick-sort.txt").toURI());
		List<String> objs = Files.readAllLines(path);
		unordered = new ArrayList<>();
		for (String s : objs) {
			unordered.add(Integer.parseInt(s));
		}
		
		lomutos = new QuickSortLomutos(new FirstElemPivotStrategy());
		hoares = new QuickSortHoares(new MedianElemPivotStrategy());
	}

	@Test
	public void quickSortLomutosFirstElPivot() throws IOException, URISyntaxException {
		List ordered = new ArrayList<>(unordered);
		int comp = lomutos.sort(ordered);
		System.out.println("Lomutos - Comparisons for Quicksort first pivot element: " + comp);

		Assert.assertTrue("List is not ordered", TestSortUtils.isOrdered(ordered));
	}

	@Test
	public void quickSortLomutosLastElPivot() throws IOException, URISyntaxException {
		lomutos.setPivotStrategy(new LastElemPivotStrategy());
		
		List ordered = new ArrayList<>(unordered);
		int comp = lomutos.sort(ordered);
		System.out.println("Lomutos - Comparisons for Quicksort last pivot element: " + comp);

		Assert.assertTrue("List is not ordered", TestSortUtils.isOrdered(ordered));
	}

	@Test
	public void quickSortLomutosMedianElPivot() throws IOException, URISyntaxException{
		//objs = new ArrayList<>(Arrays.asList(new String[]{ "8", "2", "4", "5", "7", "1" }));
		lomutos.setPivotStrategy(new MedianElemPivotStrategy());
		
		List ordered = new ArrayList<>(unordered);
		int comp = lomutos.sort(ordered);
		System.out.println("Lomutos - Comparisons for Quicksort median element: "+ comp);
		
		Assert.assertTrue("List is not ordered", TestSortUtils.isOrdered(ordered));
	}
	
	@Test
	public void quickSortLomutosRandomElPivot() throws IOException, URISyntaxException{
		//objs = new ArrayList<>(Arrays.asList(new String[]{ "8", "2", "4", "5", "7", "1" }));
		lomutos.setPivotStrategy(new RandomPivotStrategy());
		
		List ordered = new ArrayList<>(unordered);
		int comp = lomutos.sort(ordered);
		System.out.println("Lomutos - Comparisons for Quicksort random element: "+ comp);
		
		Assert.assertTrue("List is not ordered", TestSortUtils.isOrdered(ordered));
	}
	
	@Test
	@Ignore
	public void quickSortHoaresFirstElPivot() throws IOException, URISyntaxException {
		//unordered = new ArrayList<>(Arrays.asList(new Integer[]{ 8, 2, 4, 5, 7, 1 }));
		List ordered = new ArrayList<>(unordered);
		int comp = hoares.sort(ordered);
		System.out.println("Hoares - Comparisons for Quicksort first pivot element: " + comp);

		Assert.assertTrue("List is not ordered", TestSortUtils.isOrdered(ordered));
	}
}
