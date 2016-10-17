package br.com.studies.algorithms.m2.week1;

import java.util.List;
import java.util.Queue;

import org.junit.Assert;
import org.junit.Test;

import br.com.studies.algorithms.domain.graph.Edge;
import br.com.studies.algorithms.domain.graph.Graph;
import br.com.studies.algorithms.domain.graph.Vertex;
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
	
	
	@Test
	public void testRunPrim1(){
		Graph g = buildGraph("primsmst_1.txt");
		
		PrimMST prim = new PrimMST(g);
		prim.run(g.getVertices().values().iterator().next());
		
		Assert.assertEquals(4d, prim.getWeight(), 0d);
	}
	
	@Test
	public void testRunPrim2(){
		Graph g = buildGraph("primsmst_2.txt");
		
		PrimMST prim = new PrimMST(g);
		prim.run(g.getVertices().values().iterator().next());
		
		Assert.assertEquals(16d, prim.getWeight(), 0d);
	}
	
	@Test
	public void testRunPrim3(){
		Graph g = buildGraph("primsmst_3.txt");
		
		PrimMST prim = new PrimMST(g);
		prim.run(g.getVertices().values().iterator().next());
		
		Assert.assertEquals(-3d, prim.getWeight(), 0d);
	}
	
	
	private Graph buildGraph(String name){
		List<String> lines = FileUtils.readAllLines("br/com/studies/algorithms/m2/week1/"+ name);
		Graph gr = new Graph();
		for (int i = 1; i < lines.size(); i++) {
			String line = lines.get(i);
			String[] newEntry = line.split("\\s");

			Vertex v = gr.getVertex(newEntry[0].trim());
			Vertex vAdj = gr.getVertex(newEntry[1].trim());
			double weight = Double.parseDouble(newEntry[2]);
			
			Edge edge = v.getEdgeTo(vAdj);
			if (edge == null) {
				edge = new Edge(v, vAdj, weight, gr);
				v.addEdge(edge);
				gr.getEdges().add(edge);
			}
			vAdj.addEdge(edge);
		}
		return gr;
	}
}
