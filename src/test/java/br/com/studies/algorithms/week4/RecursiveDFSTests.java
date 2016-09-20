package br.com.studies.algorithms.week4;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.studies.algorithms.Graph;
import br.com.studies.algorithms.GraphSearch;
import br.com.studies.algorithms.Vertex;
import br.com.studies.algorithms.utils.FileUtils;

public class RecursiveDFSTests {

	@Test
	public void testVistAllFromFirstNode() {
		List<String> lines = FileUtils.readAllLines("br/com/studies/algorithms/week4/graph_cormen.txt");
		Graph gr = Graph.build(lines, true);

		RecursiveDFS rdfs = new RecursiveDFS();
		Vertex firstV = gr.getVertex("u");
		GraphSearch search = rdfs.visit(gr, firstV);
		
		System.out.println(search.getVisited());
		System.out.println(search.getParentheses());
		System.out.println(search.getTrees());

		Assert.assertEquals(lines.size(), search.getVisited().size());
	}
	
	@Test
	public void testVistAllBigGraph() {
		List<String> lines = FileUtils.readAllLines("br/com/studies/algorithms/week4/graph_1.txt");
		Graph gr = Graph.build(lines, true);

		RecursiveDFS rdfs = new RecursiveDFS();
		GraphSearch search = rdfs.visit(gr, null);

		Assert.assertEquals(lines.size(), search.getVisited().size());
	}
}
