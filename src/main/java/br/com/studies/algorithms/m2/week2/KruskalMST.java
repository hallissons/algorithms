package br.com.studies.algorithms.m2.week2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import br.com.studies.algorithms.domain.graph.Edge;
import br.com.studies.algorithms.domain.graph.Graph;
import br.com.studies.algorithms.domain.graph.MST;
import br.com.studies.algorithms.domain.graph.Vertex;

public class KruskalMST implements MST {

	private final Graph graph;
	private List<Edge> edgeTo;

	public KruskalMST(Graph graph) {
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

		for (Edge e : graph.getEdges()) {
			Vertex from = gr.getVertex(e.getFrom().getLabel());
			Vertex to = gr.getVertex(e.getTo().getLabel());
			Edge egr = new Edge(from, to, e.getWeight(), gr);
			gr.addEdge(egr);
			if (hasCycle(from)) {
				gr.removeEdge(egr);
			} else {
				edgeTo.add(egr);
			}
		}
	}

	private boolean hasCycle(Vertex ini) {
		Stack<Vertex> stack = new Stack<>();
		List<Vertex> visited = new LinkedList<>();

		stack.push(ini);
		visited.add(ini);

		while (!stack.isEmpty()) {
			Vertex v = stack.pop();
			for (Edge edge : v.getEdges()) {
				Vertex to = edge.getOppositeVertex(v);
				if (!visited.contains(to)) {
					visited.add(to);
					stack.push(to);
				} else if (stack.contains(to)) {
					return true;
				}
			}
		}
		return false;
	}

	private static class EdgeWeightComparator implements Comparator<Edge> {
		@Override
		public int compare(Edge e1, Edge e2) {
			return Double.valueOf(e1.getWeight()).compareTo(Double.valueOf(e2.getWeight()));
		}
	}

}
