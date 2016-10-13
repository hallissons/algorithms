package br.com.studies.algorithms.domain.graph;

import java.util.ArrayList;
import java.util.List;

public class Edge {
	private final List<Vertex> ends = new ArrayList<Vertex>();
	private final double weight;
	private final Graph graph;

	public Edge(Vertex fst, Vertex snd, Graph graph) {
		this(fst, snd, 0.0d, graph);
	}

	public Edge(Vertex fst, Vertex snd, double weight, Graph graph) {
		if (fst == null || snd == null) {
			throw new IllegalArgumentException("Both vertices are required");
		}
		ends.add(fst);
		ends.add(snd);
		this.weight = weight;
		this.graph = graph;
	}

	public Vertex getFrom() {
		return ends.get(0);
	}

	public Vertex getTo() {
		return ends.get(1);
	}

	public double getWeight() {
		return weight;
	}

	public boolean contains(Vertex v1, Vertex v2) {
		if (graph.isDirected()) {
			return getFrom().equals(v1) && getTo().equals(v2);
		}
		return ends.contains(v1) && ends.contains(v2);
	}

	public Vertex getOppositeVertex(Vertex v) {
		if (!ends.contains(v)) {
			throw new IllegalArgumentException("Vertex " + v.getLabel());
		}
		if (v.equals(getFrom())) {
			return getTo();
		}
		return getFrom();
	}

	public void replaceVertex(Vertex oldV, Vertex newV) {
		if (!ends.contains(oldV)) {
			throw new IllegalArgumentException("Vertex " + oldV.getLabel());
		}
		ends.remove(oldV);
		ends.add(newV);
	}

	@Override
	public String toString() {
		return String.format("From: %s To: %s D: %s", getFrom(), getTo(), getWeight());
	}
}
