package br.com.studies.algorithms;

import java.util.HashSet;
import java.util.Set;

public class Vertex implements Comparable<Vertex> {
	private final String label;
	private final Set<Edge> edges = new HashSet<Edge>();
	private int index;

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
	
	public int labelToInt(){
		return Integer.parseInt(label);
	}
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	@Override
	public String toString(){
		return label.toString();
	}

	@Override
	public int compareTo(Vertex o) {
		return label.compareTo(o.getLabel());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vertex other = (Vertex) obj;
		if (label == null) {
			if (other.label != null)
				return false;
		} else if (!label.equals(other.label))
			return false;
		return true;
	}
	
	
}
