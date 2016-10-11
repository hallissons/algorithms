package br.com.studies.algorithms.m1.week4;

import br.com.studies.algorithms.GraphSearch;
import br.com.studies.algorithms.domain.graph.Edge;
import br.com.studies.algorithms.domain.graph.Graph;
import br.com.studies.algorithms.domain.graph.Vertex;
import br.com.studies.algorithms.domain.graph.VertexColor;
import br.com.studies.algorithms.domain.graph.VisitedVertex;

public class RecursiveDFS {

	public GraphSearch visit(Graph gr, Vertex initial) {
		GraphSearch search = new GraphSearch(gr, initial);

		// Visits all vertices
		for (VisitedVertex u : search.getVisited()) {
			if (VertexColor.WHITE.equals(u.getColor())) {
				dfsVisit(gr, u, search);
				search.incrementTrees();
			}
		}

		return search;
	}

	private void dfsVisit(Graph gr, VisitedVertex u, GraphSearch search) {
		search.incrementTime();
		u.setDiscovTime(search.getTime());
		u.setColor(VertexColor.GRAY);
		search.getParentheses().append(String.format("(%s", u.getVertex().getLabel()));
		for (Edge edge : u.getVertex().getEdges()) {
			VisitedVertex v = search.getVisitedVertex(edge.getTo());
			if (VertexColor.WHITE.equals(v.getColor())) {
				v.setParent(u.getVertex());
				dfsVisit(gr, v, search);
			}
		}

		search.incrementTime();
		u.setColor(VertexColor.BLACK);
		u.setFinishTime(search.getTime());
		u.setOrderLabel(search.getOrderLabel());
		search.decrementOrderLabel();
		search.getParentheses().append(String.format("%s)", u.getVertex().getLabel()));
	}
}
