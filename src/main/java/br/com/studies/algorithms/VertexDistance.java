package br.com.studies.algorithms;

public class VertexDistance implements Comparable<VertexDistance> {
	private final Vertex vertex;
	private double distance;
	private VertexDistance predecessor;
	
	public VertexDistance(Vertex vertex, double distance) {
		super();
		this.vertex = vertex;
		this.distance = distance;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public Vertex getVertex() {
		return vertex;
	}

	public VertexDistance getPredecessor() {
		return predecessor;
	}

	public void setPredecessor(VertexDistance predecessor) {
		this.predecessor = predecessor;
	}

	@Override
	public int compareTo(VertexDistance vd) {
		return Double.compare(distance, vd.getDistance());
	}

	@Override
	public String toString() {
		return String.format("|%s:%s|", vertex.getLabel(), distance);
	}
}
