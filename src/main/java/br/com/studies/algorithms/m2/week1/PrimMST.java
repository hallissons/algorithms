package br.com.studies.algorithms.m2.week1;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import br.com.studies.algorithms.domain.graph.Edge;
import br.com.studies.algorithms.domain.graph.Graph;
import br.com.studies.algorithms.domain.graph.MST;
import br.com.studies.algorithms.domain.graph.Vertex;
import br.com.studies.algorithms.domain.graph.VertexDistance;
import br.com.studies.algorithms.utils.IndexMinPQ;

public class PrimMST implements MST {

	private Map<Integer, VertexDistance> distTo;
	private Map<Vertex, Edge> edgeTo;
	private Set<Integer> marked;
	private Graph graph;
	private IndexMinPQ<Double> pq;

	public PrimMST(Graph graph) {
		this.graph = graph;
	}

	@Override
	public Queue<Edge> getEdges() {
		Queue<Edge> mst = new LinkedList<>();
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
		marked = new HashSet<>();
		pq = new IndexMinPQ<>(graph.getVertices().size());

		int i = 0;
		for (Vertex v : graph.getVertices().values()) {
			v.setIndex(i);
			distTo.put(i, new VertexDistance(v, Double.POSITIVE_INFINITY));
			i++;
		}

		for (VertexDistance vd : distTo.values()) {
			if (!marked.contains(vd.getVertex().getIndex())) {
				prim(vd);
			}
		}
	}

	private void prim(VertexDistance vd) {
		pq.insert(vd.getVertex().getIndex(), vd.getDistance());

		while (!pq.isEmpty()) {
			Integer index = pq.delMin();
			scan(distTo.get(index));
		}
	}

	private void scan(VertexDistance vd) {
		marked.add(vd.getVertex().getIndex());

		for (Edge e : vd.getVertex().getEdges()) {
			VertexDistance w = distTo.get(e.getOppositeVertex(vd.getVertex()).getIndex());
			if (marked.contains(w.getVertex().getIndex())) {
				continue;
			}
			if (e.getWeight() < w.getDistance()) {
				edgeTo.put(w.getVertex(), e);
				w.setDistance(e.getWeight());

				if (pq.contains(w.getVertex().getIndex())) {
					pq.decreaseKey(w.getVertex().getIndex(), w.getDistance());
				} else {
					pq.insert(w.getVertex().getIndex(), w.getDistance());
				}
			}
		}
	}
}
