package br.com.studies.algorithms.week5;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import br.com.studies.algorithms.Edge;
import br.com.studies.algorithms.Graph;
import br.com.studies.algorithms.Vertex;
import br.com.studies.algorithms.VertexDistance;
import br.com.studies.algorithms.utils.IndexMinPQ;

public class DijkstraAlgorithmHeap {

	private Map<Integer, VertexDistance> distTo;
	private Map<Vertex, Edge> edgeTo;
	private Graph graph;
	private IndexMinPQ<Double> pq;

	public DijkstraAlgorithmHeap(Graph graph) {
		this.graph = graph;
	}

	public Map<Integer, VertexDistance> getDistTo() {
		return distTo;
	}

	public void run(Vertex ini) {
		distTo = new HashMap<>();
		edgeTo = new HashMap<>();
		pq = new IndexMinPQ<>(graph.getVertices().size());

		int i = 0;
		for (Vertex v : graph.getVertices().values()) {
			v.setIndex(i);
			distTo.put(i, new VertexDistance(v, Double.POSITIVE_INFINITY));
			i++;
		}

		VertexDistance vd = distTo.get(ini.getIndex());
		vd.setDistance(0.0d);
		pq.insert(vd.getVertex().getIndex(), vd.getDistance());

		while (!pq.isEmpty()) {
			Integer index = pq.delMin();
			// System.out.println("**** Processing vertex: " + u.getVertex());
			for (Edge e : distTo.get(index).getVertex().getEdges()) {
				// System.out.println();
				relax(e);
			}
			// System.out.println();
		}
	}

	public boolean hasPathTo(Vertex v) {
		if (distTo == null || distTo.isEmpty()) {
			return false;
		}
		return distTo.get(v.getIndex()).getDistance() < Double.POSITIVE_INFINITY;
	}

	public Stack<Edge> pathTo(Vertex v) {
		if (!hasPathTo(v)) {
			return null;
		}

		Stack<Edge> path = new Stack<>();
		for (Edge e = edgeTo.get(v); e != null; e = edgeTo.get(e.getFrom())) {
			path.push(e);
		}
		return path;
	}

	public String printPath(Vertex s, Vertex x) {
		Stack<Edge> path = pathTo(x);
		StringBuilder pp = new StringBuilder();
		if (path == null) {
			pp.append("no path found");
			return pp.toString();
		}

		pp.append(s.getLabel() + " -> ");
		while (!path.isEmpty()) {
			Edge e = path.pop();
			pp.append(e.getTo());
			if (!path.isEmpty()) {
				pp.append(" -> ");
			}
		}

		return pp.toString();
	}

	// Relax edge e and add destination vertex if the distance is "relaxed"
	private void relax(Edge e) {
		VertexDistance v = distTo.get(e.getFrom().getIndex());
		VertexDistance w = distTo.get(e.getTo().getIndex());
		// System.out.println("** Processing edge: " + e);
		// System.out.println("Distance to " + w + ": " +
		// distTo.get(w).getDistance());
		// System.out.println("Distance to " + v + " + weight: " +
		// (distTo.get(v).getDistance() + e.getWeight()));
		if (w.getDistance() > v.getDistance() + e.getWeight()) {
			w.setDistance(v.getDistance() + e.getWeight());
			edgeTo.put(w.getVertex(), e);
			if (pq.contains(w.getVertex().getIndex())) {
				pq.decreaseKey(w.getVertex().getIndex(), w.getDistance());
			} else {
				pq.insert(w.getVertex().getIndex(), w.getDistance());
			}
			v.setPredecessor(w);
		}
	}
}
