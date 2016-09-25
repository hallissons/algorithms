package br.com.studies.algorithms.week5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.studies.algorithms.Graph;
import br.com.studies.algorithms.Vertex;
import br.com.studies.algorithms.VertexDistance;
import br.com.studies.algorithms.utils.FileUtils;

public class DijkstraTests {
	
	@Test
	public void testRunBasicDijkstraCormen() {
		List<String> lines = FileUtils.readAllLines("br/com/studies/algorithms/week5/cormen_test.txt");
		Graph gr = Graph.build(lines, true);

		Vertex s = gr.getVertex("s");
		Vertex x = gr.getVertex("x");
		DijkstraAlgorithm shortPath = new DijkstraAlgorithm(gr);
		shortPath.run(s);
		
		String path = shortPath.printPath(s, x);
		Assert.assertEquals("s -> y -> t -> x", path);
	}
	
	@Test
	public void testRunBasicDijkstra() {
		List<String> lines = FileUtils.readAllLines("br/com/studies/algorithms/week5/stanford_test.txt");
		Graph gr = Graph.build(lines, true);

		Vertex s = gr.getVertex("s");
		Vertex x = gr.getVertex("t");
		DijkstraAlgorithm shortPath = new DijkstraAlgorithm(gr);
		shortPath.run(s);
		
		String path = shortPath.printPath(s, x);
		System.out.println(path);
		Assert.assertEquals("s -> v -> w -> t", path);
	}
	
	@Test
	public void testRunBasicDijkstraLongestPath() {
		List<String> lines = FileUtils.readAllLines("br/com/studies/algorithms/week5/stanford_test.txt");
		Graph gr = Graph.build(lines, true);

		Vertex s = gr.getVertex("s");
		Vertex x = gr.getVertex("t");
		DijkstraAlgorithmLongestPath longestPath = new DijkstraAlgorithmLongestPath(gr);
		longestPath.run(s);
		
		String path = longestPath.printPath(s, x);
		System.out.println(path);
		Assert.assertEquals("s -> v -> t", path);
	}

	@Test
	public void testRunBasicDijkstraStanford() {
		List<String> lines = FileUtils.readAllLines("br/com/studies/algorithms/week5/stanford_test.txt");
		Graph gr = Graph.build(lines, true);

		DijkstraAlgorithm shortPath = new DijkstraAlgorithm(gr);
		shortPath.run(gr.getVertex("s"));
		
		Assert.assertEquals("[|s:0.0|, |t:6.0|, |v:1.0|, |w:3.0|]", shortPath.getDistTo().values().toString());
	}

	@Test
	public void testRunBasicDijkstra1() {
		List<String> lines = FileUtils.readAllLines("br/com/studies/algorithms/week5/dijkstra1.txt");
		Graph gr = Graph.build(lines, true);

		DijkstraAlgorithm shortPath = new DijkstraAlgorithm(gr);
		shortPath.run(gr.getVertex("1"));

		Assert.assertEquals("[|1:0.0|, |2:1.0|, |3:3.0|, |4:2.0|, |5:Infinity|]",
				shortPath.getDistTo().values().toString());
	}

	@Test
	public void testRunQuiz() {
		List<String> lines = FileUtils.readAllLines("br/com/studies/algorithms/week5/dijkstraData.txt");
		Graph gr = Graph.build(lines, true);

		DijkstraAlgorithm shortPath = new DijkstraAlgorithm(gr);
		shortPath.run(gr.getVertex("1"));

		List<Integer> dists = new ArrayList<>();
		List<Integer> vertices = Arrays.asList(7, 37, 59, 82, 99, 115, 133, 165, 188, 197);

		for (Integer i : vertices) {
			VertexDistance vd = shortPath.getDistTo().get(gr.getVertex(i.toString()));
			dists.add(Double.valueOf(vd.getDistance()).intValue());
		}

		Assert.assertEquals("[2599, 2610, 2947, 2052, 2367, 2399, 2029, 2442, 2505, 3068]", dists.toString());
	}
}
