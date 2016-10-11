package br.com.studies.algorithms.m1.week4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.com.studies.algorithms.GraphSearch;
import br.com.studies.algorithms.domain.graph.Component;
import br.com.studies.algorithms.domain.graph.Edge;
import br.com.studies.algorithms.domain.graph.Graph;
import br.com.studies.algorithms.domain.graph.VertexColor;
import br.com.studies.algorithms.domain.graph.VisitedVertex;

public class KosarajuAlgorithm {

	public GraphSearch visit(Graph gr) {
		Graph grRev = gr.reverse();
		GraphSearch search = new GraphSearch(gr);
		GraphSearch searchRev = new GraphSearch(grRev);
		List<VisitedVertex> vs = new ArrayList<>();
		List<VisitedVertex> reversed = searchRev.getReverseVisitedVertex();

		// Visits all vertices on the reversed graph from highest to lowest: n,
		// n-1, n-2...1
		for (VisitedVertex u : reversed) {
			if (VertexColor.WHITE.equals(u.getColor())) {
				Component c = searchRev.createComponent();
				dfs(gr, u, searchRev, c);
			}
			vs.add(u);
		}

		Collections.sort(vs, new ReverseTimeComparator());

		// Visits all vertices on the original graph but using the sequence
		// computed on the first visit while processing the reverse graph.
		for (VisitedVertex u : vs) {
			VisitedVertex vv = search.getVisitedVertex(u.getVertex().getLabel());
			if (!VertexColor.WHITE.equals(vv.getColor())) {
				continue;
			}
			search.setLeader(vv);
			Component c = search.createComponent();
			dfs(gr, vv, search, c);
		}
		
		return search;
	}

	private void dfs(Graph gr, VisitedVertex u, GraphSearch search, Component c) {
		u.setDiscovTime(search.getTime());
		u.setColor(VertexColor.GRAY);
		u.setLeader(search.getLeader());
		c.addVertex(u);
		for (Edge edge : u.getVertex().getEdges()) {
			VisitedVertex v = search.getVisitedVertex(edge.getTo());
			if (!VertexColor.WHITE.equals(v.getColor())) {
				continue;
			}
			dfs(gr, v, search, c);
		}

		search.incrementTime();
		u.setColor(VertexColor.BLACK);
		u.setFinishTime(search.getTime());
	}

	private static class ReverseTimeComparator implements Comparator<VisitedVertex> {
		@Override
		public int compare(VisitedVertex o1, VisitedVertex o2) {
			return o2.getFinishTime().compareTo(o1.getFinishTime());
		}
	}
}
