package br.com.studies.algorithms.m1.week3;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import br.com.studies.algorithms.domain.graph.Edge;
import br.com.studies.algorithms.domain.graph.Graph;
import br.com.studies.algorithms.domain.graph.Vertex;
import br.com.studies.algorithms.utils.FileUtils;

public class MinCutTests {

	@Test
	@Ignore
	public void testMinCutBigGraph() {
		MinCut minCut = new MinCut();
		List<String> lines = FileUtils.readAllLines("br/com/studies/algorithms/m1/week3/graph_1.txt");
		final int minimum = 17;

		Map<Integer, Integer> statistics = new LinkedHashMap<Integer, Integer>();

		int size = lines.size();
		int min = size;
		int iter = size * size;

		for (int i = 0; i < iter; i++) {
			Graph gr = Graph.build(lines);
			int currMin = minCut.compute(gr);
			min = Math.min(min, currMin);

			Integer counter = statistics.get(currMin);
			if (counter == null) {
				counter = 0;
			}
			statistics.put(currMin, counter + 1);
		}
		System.out.println("Min: " + min + " stat: " + (statistics.get(min) * 100 / iter) + "%");

		Assert.assertTrue(min == minimum);
	}

	@Test
	public void testMinCut() throws URISyntaxException, IOException {
		final int minimum = 1;
		List<String> lines = FileUtils.readAllLines("br/com/studies/algorithms/m1/week3/graph_small_test.txt");
		MinCut minCut = new MinCut();
		
		Graph gr = Graph.build(lines);
		
		int min = minCut.compute(gr);
		
		Assert.assertTrue(min == minimum);
	}

	private static void printGraph(Graph gr) {
		System.out.println("Printing graph");
		for (Vertex v : gr.getVertices().values()) {
			System.out.print(v.getLabel() + ":");
			for (Edge edge : v.getEdges()) {
				System.out.print(" " + edge.getOppositeVertex(v).getLabel());
			}
			System.out.println();
		}
	}
	
	private int[][] getArray(List<String> lines) {
		Map<Integer, List<Integer>> vertices = new LinkedHashMap<Integer, List<Integer>>();
		for (String line : lines) {

			String[] split = line.trim().split("(\\s)+");
			List<Integer> adjList = new ArrayList<Integer>();
			for (int i = 1; i < split.length; i++) {
				adjList.add(Integer.parseInt(split[i]) - 1);
			}
			vertices.put(Integer.parseInt(split[0]) - 1, adjList);
		}

		int[][] array = new int[vertices.size()][];
		for (Map.Entry<Integer, List<Integer>> entry : vertices.entrySet()) {
			List<Integer> adjList = entry.getValue();
			int[] adj = new int[adjList.size()];
			for (int i = 0; i < adj.length; i++) {
				adj[i] = adjList.get(i);
			}
			array[entry.getKey()] = adj;
		}
		return array;
	}

	// Adj format to visualize in
	// http://www.cs.rpi.edu/research/groups/pb/graphdraw/headpage.html
	private static void toAdjFormat(int[][] arr) {
		System.out.println(arr.length);
		for (int[] adj : arr) {
			System.out.print(adj.length);
			for (int i : adj) {
				System.out.print(" " + i);
			}
			System.out.println();
		}
	}

}
