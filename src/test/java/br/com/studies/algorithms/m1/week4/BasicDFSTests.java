package br.com.studies.algorithms.m1.week4;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.studies.algorithms.domain.graph.Graph;
import br.com.studies.algorithms.domain.graph.Vertex;
import br.com.studies.algorithms.m1.week4.BasicDFS;
import br.com.studies.algorithms.utils.FileUtils;

public class BasicDFSTests {

	@Test
	public void testVistAllFromFirstNode() {
		List<String> lines = FileUtils.readAllLines("graphs/graph_small_test2.txt");
		Graph gr = Graph.build(lines, true);

		BasicDFS bfs = new BasicDFS();
		Vertex firstV = gr.getVertex("s");
		List<Vertex> visited = bfs.visit(gr, firstV);

		Assert.assertEquals(lines.size(), visited.size());
	}
}
