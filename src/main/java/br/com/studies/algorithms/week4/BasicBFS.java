package br.com.studies.algorithms.week4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import br.com.studies.algorithms.Edge;
import br.com.studies.algorithms.Graph;
import br.com.studies.algorithms.Vertex;

public class BasicBFS {

	public int shortestPath(Graph gr, Vertex from, Vertex to) {
		Map<Vertex, Integer> visited = visit(gr, from, to);
		return visited.get(to);
	}

	public Map<Vertex, Integer> visit(Graph gr, Vertex initial) {
		return visit(gr, initial, null);
	}
	
	public List<Map<Vertex, Integer>> findConnectedComponents(Graph gr){
		List<Map<Vertex, Integer>> components = new ArrayList<>();
		Map<Vertex, Integer> comp = new HashMap<>();
		for (Vertex v : gr.getVertices().values()) {
			if(!containsVertex(components, v)){
				comp = visit(gr, v);
				components.add(comp);
			}
		}
		
		return components;
	}

	public Map<Vertex, Integer> visit(Graph gr, Vertex initial, Vertex end) {
		Queue<Vertex> q = new LinkedList<>();
		Map<Vertex, Integer> visited = new HashMap<>();
		q.add(initial);
		visited.put(initial, 0);

		while (!q.isEmpty()) {
			Vertex v = q.poll();
			for (Edge edge : v.getEdges()) {
				Vertex vDest = edge.getTo();
				if (!visited.containsKey(vDest)) {
					Integer vDist = visited.get(v);
					visited.put(vDest, vDist + 1);
					q.add(vDest);
				}

				if (end != null && visited.containsKey(end)) {
					return visited;
				}
			}
		}
		return visited;
	}
	
	private boolean containsVertex(List<Map<Vertex, Integer>> components, Vertex v){
		for (Map<Vertex, Integer> map : components) {
			if(map.containsKey(v)){
				return true;
			}
		}
		return false;
	}
}
