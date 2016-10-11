package br.com.studies.algorithms.m2.week1;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;

import br.com.studies.algorithms.domain.graph.Edge;
import br.com.studies.algorithms.domain.graph.Graph;
import br.com.studies.algorithms.domain.graph.MST;
import br.com.studies.algorithms.domain.graph.Vertex;
import br.com.studies.algorithms.domain.graph.VertexDistance;

public class PrimMST implements MST {

	private Map<Vertex, VertexDistance> distTo;
	private Map<Vertex, Edge> edgeTo;
	private Map<Vertex, Boolean> marked;
	private Graph graph;
	private PriorityQueue<VertexDistance> pq;

	public PrimMST(Graph graph) {
		this.graph = graph;
	}

	public Map<Vertex, VertexDistance> getDistTo() {
		return distTo;
	}

	@Override
	public Collection<Edge> getEdges() {
		Collection<Edge> mst = new LinkedList<>();
		for (Edge e : edgeTo.values()) {
			mst.add(e);
		}
		return mst;
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
		distTo = new HashMap<>();
		edgeTo = new HashMap<>();
		marked = new HashMap<>();
		pq = new PriorityQueue<>();

		for (Vertex v : graph.getVertices().values()) {
			distTo.put(v, new VertexDistance(v, Double.POSITIVE_INFINITY));
		}

		for (Vertex v : graph.getVertices().values()) {
			if (!marked.containsKey(v)) {
				prim(v);
			}
		}
	}

	private void prim(Vertex v) {
		VertexDistance vd = distTo.get(v);
		vd.setDistance(0.0d);
		pq.add(vd);

		while (!pq.isEmpty()) {
			VertexDistance x = pq.poll();
			scan(x);
		}
	}

	private void scan(VertexDistance vd) {
		marked.put(vd.getVertex(), true);

		for (Edge e : vd.getVertex().getEdges()) {
			VertexDistance w = distTo.get(e.getTo());
			if (marked.containsKey(w.getVertex())) {
				continue;
			}
			if (e.getWeight() < w.getDistance()) {
				edgeTo.put(w.getVertex(), e);
				w.setDistance(e.getWeight());
				pq.add(w);
			}
		}
	}
}
