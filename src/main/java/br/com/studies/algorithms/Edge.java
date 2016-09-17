package br.com.studies.algorithms;

import java.util.ArrayList;
import java.util.List;

public class Edge {
	private final List<Vertex> ends = new ArrayList<Vertex>();
	
	public Vertex getFrom(){
		return ends.get(0);
	}
	
	public Vertex getTo(){
		return ends.get(1);
	}

	public Edge(Vertex fst, Vertex snd) {
		if (fst == null || snd == null) {
			throw new IllegalArgumentException("Both vertices are required");
		}
		ends.add(fst);
		ends.add(snd);
	}

	public boolean contains(Vertex v1, Vertex v2) {
		return ends.contains(v1) && ends.contains(v2);
	}

	public Vertex getOppositeVertex(Vertex v) {
		if (!ends.contains(v)) {
			throw new IllegalArgumentException("Vertex " + v.getLabel());
		}
		return ends.get(1 - ends.indexOf(v));
	}

	public void replaceVertex(Vertex oldV, Vertex newV) {
		if (!ends.contains(oldV)) {
			throw new IllegalArgumentException("Vertex " + oldV.getLabel());
		}
		ends.remove(oldV);
		ends.add(newV);
	}
	
	@Override
	public String toString(){
		return String.format("From: %s To: %s", getFrom(), getTo());
	}
}
