package br.com.studies.algorithms;

import java.util.HashSet;
import java.util.Set;

public class Vertex {
	private final String label;
	private final Set<Edge> edges = new HashSet<Edge>();

	public Vertex(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public Set<Edge> getEdges() {
		return edges;
	}

	public void addEdge(Edge edge) {
		edges.add(edge);
	}

	public Edge getEdgeTo(Vertex v2) {
		for (Edge edge : edges) {
			if (edge.contains(this, v2)) {
				return edge;
			}
		}
		return null;
	}
	
	@Override
	public String toString(){
		return label;
	}
}
