package br.com.studies.algorithms.week3;

import java.util.Iterator;
import java.util.Random;

import br.com.studies.algorithms.Edge;
import br.com.studies.algorithms.Graph;
import br.com.studies.algorithms.Vertex;

public class MinCut {

	/*
    * Karger's algorithm / Contraction algorithm (minimum cut problem)
    * 1) while there are > 2 vertices
    * 2) pick the remaining edge(u,v) uniformly at random
    * 3) merge u and v in a single vertex
    * 4)    remove self loop
    * 5) return cut represented by 2 final vertices
    */
	public int compute(Graph gr) {
		Random rnd = new Random();

		while (gr.getVertices().size() > 2) {
			Edge edge = gr.getEdges().remove(rnd.nextInt(gr.getEdges().size()));
			Vertex v1 = cleanVertex(gr, edge.getFrom(), edge);
			Vertex v2 = cleanVertex(gr, edge.getTo(), edge);
			// contract
			String newLbl = String.format("%s:%s", v1.getLabel(), v2.getLabel());
			Vertex mergedVertex = new Vertex(newLbl);
			redirectEdges(gr, v1, mergedVertex);
			redirectEdges(gr, v2, mergedVertex);
			gr.addVertex(mergedVertex);
		}
		return gr.getEdges().size();
	}

	private Vertex cleanVertex(Graph gr, Vertex v, Edge e) {
		gr.getVertices().remove(v.getLabel());
		v.getEdges().remove(e);
		return v;
	}

	private void redirectEdges(Graph gr, Vertex fromV, Vertex toV) {
		for (Iterator<Edge> it = fromV.getEdges().iterator(); it.hasNext();) {
			Edge edge = it.next();
			it.remove();
			if (edge.getOppositeVertex(fromV) == toV) {
				// remove self-loop
				toV.getEdges().remove(edge);
				gr.getEdges().remove(edge);
			} else {
				edge.replaceVertex(fromV, toV);
				toV.addEdge(edge);
			}
		}
	}

}