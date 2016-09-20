package br.com.studies.algorithms.week4;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import br.com.studies.algorithms.Graph;
import br.com.studies.algorithms.Vertex;
import br.com.studies.algorithms.utils.FileUtils;

public class BasicBFSTests {

	@Test
	public void testVistAllFromFirstNode() {
		List<String> lines = FileUtils.readAllLines("br/com/studies/algorithms/week4/graph_small_test.txt");
		Graph gr = Graph.build(lines);

		BasicBFS bfs = new BasicBFS();
		Vertex firstV = gr.getVertices().values().iterator().next();
		Map<Vertex, Integer> visited = bfs.visit(gr, firstV);

		Assert.assertEquals(lines.size(), visited.size());
	}

	@Test
	public void testVisitAllFromRandomNode() {
		List<String> lines = FileUtils.readAllLines("br/com/studies/algorithms/week4/graph_small_test.txt");

		Graph gr = Graph.build(lines);

		Random rdm = new Random();
		Set<Integer> keys = gr.getVertices().keySet();
		List<Integer> vertices = new ArrayList<>(keys);
		Integer rdmKey = vertices.get(rdm.nextInt(vertices.size() - 1));
		Vertex rdmVertex = gr.getVertex(rdmKey);

		BasicBFS bfs = new BasicBFS();
		Map<Vertex, Integer> visited = bfs.visit(gr, rdmVertex);

		Assert.assertEquals(lines.size(), visited.size());
	}

	@Test
	public void testFindShortestPathFromSToE() {
		List<String> lines = FileUtils.readAllLines("br/com/studies/algorithms/week4/graph_small_test.txt");

		Graph gr = Graph.build(lines);

		BasicBFS bfs = new BasicBFS();
		Vertex from = gr.getVertex(0);
		Vertex to = gr.getVertex(5);
		Integer shortestPath = bfs.shortestPath(gr, from, to);

		Assert.assertEquals(Integer.valueOf(3), shortestPath);
	}

	@Test
	public void testFindConnectedComponents() {
		List<String> lines = FileUtils.readAllLines("br/com/studies/algorithms/week4/graph_three_connected.txt");

		Graph gr = Graph.build(lines);

		BasicBFS bfs = new BasicBFS();
		List<Map<Vertex, Integer>> components = bfs.findConnectedComponents(gr);

		Assert.assertEquals(3, components.size());
	}
}
