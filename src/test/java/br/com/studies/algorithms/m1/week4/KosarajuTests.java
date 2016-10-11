package br.com.studies.algorithms.m1.week4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import br.com.studies.algorithms.GraphSearch;
import br.com.studies.algorithms.domain.graph.Component;
import br.com.studies.algorithms.domain.graph.Graph;
import br.com.studies.algorithms.utils.FileUtils;

public class KosarajuTests {

	@Test
	public void testFile1() {
		buildGraphAndCompare("br/com/studies/algorithms/m1/week4/SCC_test1.txt", "[3,3,3]");
	}

	@Test
	public void testFile2() {
		buildGraphAndCompare("br/com/studies/algorithms/m1/week4/SCC_test2.txt", "[3,3,2]");
	}

	@Test
	public void testFile3() {
		buildGraphAndCompare("br/com/studies/algorithms/m1/week4/SCC_test3.txt", "[3,3,1,1]");
	}

	@Test
	public void testFile4() {
		buildGraphAndCompare("br/com/studies/algorithms/m1/week4/SCC_test4.txt", "[7,1]");
	}

	@Test
	public void testFile5() {
		buildGraphAndCompare("br/com/studies/algorithms/m1/week4/SCC_test5.txt", "[6,3,2,1]");
	}
	
	@Test
	@Ignore
	public void testBigFile(){
		buildGraphAndCompare("br/com/studies/algorithms/m1/week4/SCC.txt", null);
	}
	
	private void buildGraphAndCompare(String path, String expected) {
		List<String> lines = FileUtils.readAllLines(path);
		Graph gr = Graph.build(lines, true);
		List<Integer> compSizes = new ArrayList<>();

		KosarajuAlgorithm rdfs = new KosarajuAlgorithm();
		GraphSearch search = rdfs.visit(gr);
		
		for (Component c : search.getComponents()) {
			compSizes.add(c.getSize());
		}

		int size = compSizes.size() >= 5 ? 5 : compSizes.size();
		Collections.sort(compSizes, Collections.reverseOrder());
		compSizes = compSizes.subList(0, size);
		
		String result = compSizes.toString().replaceAll("\\s+","");
		
		System.out.println(result);
		if (expected != null) {
			Assert.assertEquals(expected, result.toString());
		}
	}

}
