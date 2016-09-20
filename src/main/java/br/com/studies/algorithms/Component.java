package br.com.studies.algorithms;

import java.util.ArrayList;
import java.util.List;

public class Component {

	private final List<VisitedVertex> vertices;
	private final GraphSearch search;
	
	public Component(GraphSearch search){
		this.search = search;
		vertices = new ArrayList<>();
	}
	
	public void addVertex(VisitedVertex v){
		vertices.add(v);
	}
	
	public Integer getSize(){
		return vertices.size();
	}
	
	public GraphSearch getGraphSearch(){
		return search;
	}

	@Override
	public String toString() {
		return "Components: "+getSize();
	}
}
