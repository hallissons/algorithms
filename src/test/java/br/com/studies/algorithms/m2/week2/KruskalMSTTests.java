package br.com.studies.algorithms.m2.week2;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.studies.algorithms.domain.graph.Edge;
import br.com.studies.algorithms.domain.graph.Graph;
import br.com.studies.algorithms.domain.graph.Vertex;
import br.com.studies.algorithms.utils.FileUtils;

public class KruskalMSTTests {
	@Test
	public void testRunBasicKruskal(){
		List<String> lines = FileUtils.readAllLines("mst1.txt");
		Graph g = Graph.build(lines);
		
		KruskalMST prim = new KruskalMST(g);
		prim.run(g.getVertex("a"));
		
		Assert.assertEquals(7d, prim.getWeight(), 0d);
	}
	
	@Test
	public void testRunBasicKruskalCormen(){
		List<String> lines = FileUtils.readAllLines("mst_cormen.txt");
		Graph g = Graph.build(lines);
		
		KruskalMST prim = new KruskalMST(g);
		prim.run(g.getVertex("a"));
		
		Assert.assertEquals(37d, prim.getWeight(), 0d);
	}
	
	@Test
	public void testRunKruskal1(){
		Graph g = buildGraph("kruskalmst_1.txt");
		
		KruskalMST prim = new KruskalMST(g);
		prim.run(g.getVertices().values().iterator().next());
		
		Assert.assertEquals(4d, prim.getWeight(), 0d);
	}
	
	@Test
	public void testRunKruskal2(){
		Graph g = buildGraph("kruskalmst_2.txt");
		
		KruskalMST prim = new KruskalMST(g);
		prim.run(g.getVertices().values().iterator().next());
		
		Assert.assertEquals(16d, prim.getWeight(), 0d);
	}
	
	@Test
	public void testRunKruskal3(){
		Graph g = buildGraph("kruskalmst_3.txt");
		
		KruskalMST prim = new KruskalMST(g);
		prim.run(g.getVertices().values().iterator().next());
		
		Assert.assertEquals(-3d, prim.getWeight(), 0d);
	}
	
	@Test
	public void testRunKruskal4(){
		Graph g = buildGraph("edges.txt");
		
		KruskalMST prim = new KruskalMST(g);
		prim.run(g.getVertices().values().iterator().next());
		
		Assert.assertEquals(-3612829d, prim.getWeight(), 0d);
	}
	
	
	@Test
	public void testRunKruskal5(){
		Graph g = buildGraph("edges.txt");
		
		KruskalMSTUF prim = new KruskalMSTUF(g);
		prim.run(g.getVertices().values().iterator().next());
		
		Assert.assertEquals(-3612829d, prim.getWeight(), 0d);
	}
	
	private Graph buildGraph(String name){
		List<String> lines = FileUtils.readAllLines(""+ name);
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
