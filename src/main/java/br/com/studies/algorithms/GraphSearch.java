package br.com.studies.algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class GraphSearch {
	private final Map<Vertex, VisitedVertex> visitedVertexMap;
	private final List<VisitedVertex> visited;
	private final StringBuilder parentheses;
	private final Graph graph;
	private final List<Component> components;
	private VisitedVertex leader;
	private int orderLabel;
	private int time;
	private int trees;
	
	public GraphSearch(Graph graph){
		this(graph, null);
	}

	public GraphSearch(Graph graph, Vertex initial) {
		this.visitedVertexMap = new HashMap<>();
		this.visited = new LinkedList<>();
		this.parentheses = new StringBuilder();
		this.components = new ArrayList<>();
		this.graph = graph;
		this.time = 0;
		this.trees = 0;
		this.orderLabel = graph.getVertices().size();

		// A way to start from any node
		if (initial != null) {
			addVisitedVertex(initial);
		}

		// Initializes all "Visited Vertices"
		for (Vertex v : graph.getVertices().values()) {
			addVisitedVertex(v);
		}
	}

	public int getTime() {
		return time;
	}

	public void incrementTime() {
		this.time++;
	}

	public List<VisitedVertex> getVisited() {
		return visited;
	}

	public StringBuilder getParentheses() {
		return parentheses;
	}

	public int getTrees() {
		return trees;
	}

	public int getOrderLabel() {
		return orderLabel;
	}
	
	public Graph getGraph(){
		return graph;
	}

	public VisitedVertex getLeader() {
		return leader;
	}

	public void setLeader(VisitedVertex leader) {
		this.leader = leader;
	}
	
	public List<Component> getComponents(){
		return components;
	}
	
	public List<VisitedVertex> getReverseVisitedVertex(){
		List<VisitedVertex> reverse = new ArrayList<>(visited);
		Collections.reverse(reverse);
		return reverse;
	}

	public void addVisitedVertex(Vertex vertex) {
		if (visitedVertexMap.containsKey(vertex)) {
			return;
		}
		VisitedVertex vv = new VisitedVertex(vertex);
		visited.add(vv);
		visitedVertexMap.put(vertex, vv);
	}
	
	public VisitedVertex getVisitedVertex(Integer label){
		return getVisitedVertex(new Vertex(label));
	}

	public VisitedVertex getVisitedVertex(Vertex vertex) {
		if (visitedVertexMap.containsKey(vertex)) {
			return visitedVertexMap.get(vertex);
		}
		return null;
	}

	public void incrementTrees() {
		trees++;
	}

	public void decrementOrderLabel() {
		if (orderLabel > 0) {
			orderLabel--;
		}
	}
	
	public Component createComponent() {
		Component c = new Component(this);
		this.components.add(c);
		return c;
	}
	
	public void resetTime(){
		time = 0;
	}
	
	@Override
	public String toString(){
		return visited.toString();
	}
}
