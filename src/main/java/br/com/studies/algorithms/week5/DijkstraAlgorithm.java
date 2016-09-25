package br.com.studies.algorithms.week5;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;

import br.com.studies.algorithms.Edge;
import br.com.studies.algorithms.Graph;
import br.com.studies.algorithms.Vertex;
import br.com.studies.algorithms.VertexDistance;

public class DijkstraAlgorithm {

	private Map<Vertex, VertexDistance> distTo;
	private Map<Vertex, Edge> edgeTo;
	private Graph graph;
	private PriorityQueue<VertexDistance> pq;

	public DijkstraAlgorithm(Graph graph) {
		this.graph = graph;
	}

	public Map<Vertex, VertexDistance> getDistTo() {
		return distTo;
	}

	public void run(Vertex ini) {
		distTo = new HashMap<>();
		edgeTo = new HashMap<>();
		pq = new PriorityQueue<>();

		for (Vertex v : graph.getVertices().values()) {
			distTo.put(v, new VertexDistance(v, Double.POSITIVE_INFINITY));
		}

		VertexDistance distIni = new VertexDistance(ini, 0.0d);
		distTo.put(ini, distIni);
		pq.add(distIni);

		while (!pq.isEmpty()) {
			VertexDistance u = pq.poll();
			//System.out.println("**** Processing vertex: " + u.getVertex());
			for (Edge e : u.getVertex().getEdges()) {
				//System.out.println();
				relax(e);
			}
			//System.out.println();
		}
	}
	
	public boolean hasPathTo(Vertex v){
		if(distTo == null || distTo.isEmpty()){
			return false;
		}
		return distTo.get(v).getDistance() < Double.POSITIVE_INFINITY; 
	}
	
	public Stack<Edge> pathTo(Vertex v){
		if(!hasPathTo(v)){
			return null;
		}
		
		Stack<Edge> path = new Stack<>();
		for(Edge e = edgeTo.get(v); e != null; e = edgeTo.get(e.getFrom())){
			path.push(e);
		}
		return path;
	}
	
	public String printPath(Vertex s, Vertex x){
		Stack<Edge> path = pathTo(x);
		StringBuilder pp = new StringBuilder();
		if(path == null){
			pp.append("no path found");
			return pp.toString();
		}

		pp.append(s.getLabel()+" -> ");
		while(!path.isEmpty()){
			Edge e = path.pop();
			pp.append(e.getTo());
			if(!path.isEmpty()){
				pp.append(" -> ");
			}
		}
		
		return pp.toString();
	}

	// Relax edge e and add destination vertex if the distance is "relaxed"
	private void relax(Edge e) {
		VertexDistance v = distTo.get(e.getFrom());
		VertexDistance w = distTo.get(e.getTo());
		//System.out.println("** Processing edge: " + e);
		//System.out.println("Distance to " + w + ": " + distTo.get(w).getDistance());
		//System.out.println("Distance to " + v + " + weight: " + (distTo.get(v).getDistance() + e.getWeight()));
		if (w.getDistance() > v.getDistance() + e.getWeight()) {
			w.setDistance(v.getDistance() + e.getWeight());
			edgeTo.put(w.getVertex(), e);
			pq.add(w);
			v.setPredecessor(w);
		}
	}
}
