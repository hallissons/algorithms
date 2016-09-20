package br.com.studies.algorithms;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {

	private final Map<Integer, Vertex> vertices = new HashMap<Integer, Vertex>();
	private final List<Edge> edges = new ArrayList<Edge>();

	public Map<Integer, Vertex> getVertices() {
		return vertices;
	}

	public List<Edge> getEdges() {
		return edges;
	}

	public void addVertex(Vertex v) {
		vertices.put(v.getLabel(), v);
	}

	public Vertex getVertex(Integer lbl) {
		Vertex v = vertices.get(lbl);
		if (v == null) {
			v = new Vertex(lbl);
			addVertex(v);
		}
		return v;
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
			Vertex v = gr.getVertex(Integer.parseInt(key.trim()));

			// Each other column is an end point from the first column
			for (int i = 1; i < newEntry.length; i++) {
				String adj = newEntry[i];
				Vertex vAdj = gr.getVertex(Integer.parseInt(adj.trim()));
				Edge edge = v.getEdgeTo(vAdj);
				if (edge == null) {
					edge = new Edge(v, vAdj);
					v.addEdge(edge);
					if (!directed) {
						vAdj.addEdge(edge);
					}
					gr.getEdges().add(edge);
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
				Edge eRev = new Edge(vRevTo, vRev);
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
