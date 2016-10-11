package br.com.studies.algorithms.m2.week1;

import java.util.List;

import org.junit.Test;

import br.com.studies.algorithms.domain.graph.Graph;
import br.com.studies.algorithms.utils.FileUtils;

public class PrimMSTTests {
	
	@Test
	public void testRunBasicPrim(){
		List<String> lines = FileUtils.readAllLines("br/com/studies/algorithms/m2/week1/mst1.txt");
		Graph g = Graph.build(lines, false);
		
		PrimMST prim = new PrimMST(g);
		prim.run(g.getVertex("a"));
		
		System.out.println(prim.getEdges());
	}
}
