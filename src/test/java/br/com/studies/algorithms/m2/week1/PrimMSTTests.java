package br.com.studies.algorithms.m2.week1;

import java.util.List;
import java.util.Queue;

import org.junit.Assert;
import org.junit.Test;

import br.com.studies.algorithms.domain.graph.Edge;
import br.com.studies.algorithms.domain.graph.Graph;
import br.com.studies.algorithms.utils.FileUtils;

public class PrimMSTTests {
	
	@Test
	public void testRunBasicPrim(){
		List<String> lines = FileUtils.readAllLines("br/com/studies/algorithms/m2/week1/mst1.txt");
		Graph g = Graph.build(lines);
		
		PrimMST prim = new PrimMST(g);
		prim.run(g.getVertex("a"));
		
		Assert.assertEquals(7d, prim.getWeight(), 0d);
	}
	
	@Test
	public void testRunBasicPrimCormen(){
		List<String> lines = FileUtils.readAllLines("br/com/studies/algorithms/m2/week1/mst_cormen.txt");
		Graph g = Graph.build(lines);
		
		PrimMST prim = new PrimMST(g);
		prim.run(g.getVertex("a"));
		
		Queue<Edge> edges = prim.getEdges();
		while (!edges.isEmpty()) {
			Edge e = edges.poll();
			System.out.println(e);
		}
		
		Assert.assertEquals(37d, prim.getWeight(), 0d);
	}
}
