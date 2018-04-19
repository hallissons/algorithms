package br.com.studies.algorithms.domain.graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {

	private final Map<String, Vertex> vertices = new HashMap<String, Vertex>();
	private final List<Edge> edges = new ArrayList<Edge>();
	private final boolean directed;
	
	public Graph() {
		this(false);
	}

	public Graph(boolean directed) {
		super();
		this.directed = directed;
	}

	public Map<String, Vertex> getVertices() {
		return vertices;
	}

	public List<Edge> getEdges() {
		return edges;
	}

	public void addVertex(Vertex v) {
		vertices.put(v.getLabel(), v);
	}

	public Vertex getVertex(String lbl) {
		Vertex v = vertices.get(lbl);
		if (v == null) {
			v = new Vertex(lbl);
			v.setIndex(vertices.size());
			addVertex(v);
		}
		return v;
	}
	
	public void addEdge(Edge e){
		e.getFrom().addEdge(e);
		e.getTo().addEdge(e);
		edges.add(e);
	}
	
	public void removeEdge(Edge e){
		edges.remove(e);
		e.getFrom().removeEdge(e);
		e.getTo().removeEdge(e);
	}
	
	public boolean isDirected() {
		return directed;
	}

	@Override
	public String toString() {
		return String.format("Vertices: %s\nEdges: %s", vertices.keySet(), edges);
	}

	public static Graph build(List<String> lines) {
		return build(lines, false);
	}

	public static Graph build(List<String> lines, boolean directed) {
		Graph gr = new Graph();
		for (String line : lines) {
			String[] newEntry = line.split("\\s");

			// First column is the vertex
			String key = newEntry[0];
			Vertex v = gr.getVertex(key.trim());

			// Each other column is an end point from the first column
			for (int i = 1; i < newEntry.length; i++) {
				String adj = newEntry[i];
				String[] weighted = adj.split("\\,");
				double weight = 0.0d;
				if(weighted.length > 1){
					adj = weighted[0];
					weight = Double.parseDouble(weighted[1]);
				}
				Vertex vAdj = gr.getVertex(adj.trim());
				Edge edge = v.getEdgeTo(vAdj);
				if (edge == null) {
					edge = new Edge(v, vAdj, weight, gr);
					v.addEdge(edge);
					gr.getEdges().add(edge);
				}
				if (!directed) {
					vAdj.addEdge(edge);
				}
			}
		}
		return gr;
	}

	public Graph reverse() {
		Graph gRev = new Graph();
		for (Vertex v : getVertices().values()) {
			Vertex vRev = gRev.getVertex(v.getLabel());
			gRev.addVertex(vRev);
			for (Edge e : v.getEdges()) {
				Vertex vRevTo = gRev.getVertex(e.getTo().getLabel());
				Edge eRev = new Edge(vRevTo, vRev, gRev);
				vRevTo.addEdge(eRev);
				gRev.getEdges().add(eRev);
			}
		}

		return gRev;
	}
}

class VertexComparator implements Comparator<Integer> {
	@Override
	public int compare(Integer label, Integer label2) {
		return label.compareTo(label2);
	}
}
