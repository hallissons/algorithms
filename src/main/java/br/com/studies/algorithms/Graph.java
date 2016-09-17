package br.com.studies.algorithms;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Graph {

	private final Map<String, Vertex> vertices = new TreeMap<String, Vertex>(new VertexComparator());
	private final List<Edge> edges = new ArrayList<Edge>();

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
			addVertex(v);
		}
		return v;
	}

	@Override
	public String toString() {
		return String.format("Vertices: %s\nEdges: %s", vertices.keySet(), edges);
	}

	public static Graph build(List<String> lines) {
		Graph gr = new Graph();
		for (String line : lines) {
			String[] newEntry = line.split("\\s");
			// First column is the vertex
			String key = newEntry[0];
			Vertex v = gr.getVertex(key);
			
			//Each other column is an end point from the first column
			for (int i = 1; i < newEntry.length; i++) {
				String adj = newEntry[i];
				Vertex vAdj = gr.getVertex(adj);
				Edge edge = vAdj.getEdgeTo(v);
				if(edge == null){
					edge = new Edge(v, vAdj);
					v.addEdge(edge);
					vAdj.addEdge(edge);
					gr.getEdges().add(edge);
				}
			}
		}
		return gr;
	}
}

class VertexComparator implements Comparator<String> {
	@Override
	public int compare(String label, String label2) {
		return label.compareTo(label2);
	}

}
