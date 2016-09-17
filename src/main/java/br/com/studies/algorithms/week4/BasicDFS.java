package br.com.studies.algorithms.week4;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import br.com.studies.algorithms.Edge;
import br.com.studies.algorithms.Graph;
import br.com.studies.algorithms.Vertex;

public class BasicDFS {
	
	public List<Vertex> visit(Graph gr, Vertex initial) {
		Stack<Vertex> stack = new Stack<>();
		List<Vertex> visited = new LinkedList<>();
		stack.push(initial);
		visited.add(initial);

		while (!stack.isEmpty()) {
			Vertex v = stack.pop();
			for (Edge edge : v.getEdges()) {				
				Vertex to = edge.getTo();
				if (!visited.contains(to)) {
					visited.add(to);
					stack.push(to);
				}
			}
		}
		return visited;
	}
}
