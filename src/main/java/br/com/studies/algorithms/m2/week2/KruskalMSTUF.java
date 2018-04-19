package br.com.studies.algorithms.m2.week2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import br.com.studies.algorithms.domain.graph.Edge;
import br.com.studies.algorithms.domain.graph.Graph;
import br.com.studies.algorithms.domain.graph.MST;
import br.com.studies.algorithms.domain.graph.UF;
import br.com.studies.algorithms.domain.graph.Vertex;

public class KruskalMSTUF implements MST {

	private final Graph graph;
	private List<Edge> edgeTo;

	public KruskalMSTUF(Graph graph) {
		this.graph = graph;
	}

	@Override
	public Collection<Edge> getEdges() {
		return new LinkedList<>(edgeTo);
	}

	@Override
	public double getWeight() {
		double weight = 0.0;
		for (Edge e : getEdges()) {
			weight += e.getWeight();
		}
		return weight;
	}

	public void run(Vertex ini) {
		Graph gr = new Graph();
		edgeTo = new ArrayList<>();

		Collections.sort(graph.getEdges(), new EdgeWeightComparator());

		UF uf = new UF(graph.getVertices().size());
		for (Edge e : graph.getEdges()) {
			int u = e.getFrom().getIndex();
			int v = e.getTo().getIndex();
			Vertex from = gr.getVertex(e.getFrom().getLabel());
			Vertex to = gr.getVertex(e.getTo().getLabel());
			
			Edge egr = new Edge(from, to, e.getWeight(), gr);
			if(!uf.connected(u, v)){
				uf.union(u, v);
				edgeTo.add(egr);
			}
		}
	}


	private static class EdgeWeightComparator implements Comparator<Edge> {
		@Override
		public int compare(Edge e1, Edge e2) {
			return Double.valueOf(e1.getWeight()).compareTo(Double.valueOf(e2.getWeight()));
		}
	}

}
