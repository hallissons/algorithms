package br.com.studies.algorithms;

public class VisitedVertex implements Comparable<VisitedVertex> {
	private final Vertex vertex;
	private Integer discovTime;
	private Integer finishTime;
	private int orderLabel;
	private VertexColor color;
	private Vertex parent;
	private VisitedVertex leader;

	public VisitedVertex(Vertex vertex) {
		this.vertex = vertex;
		this.color = VertexColor.WHITE;
	}

	public Vertex getVertex() {
		return vertex;
	}

	public Integer getDiscovTime() {
		return discovTime;
	}

	public void setDiscovTime(Integer discovTime) {
		this.discovTime = discovTime;
	}

	public Integer getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Integer finishTime) {
		this.finishTime = finishTime;
	}

	public Vertex getParent() {
		return parent;
	}

	public void setParent(Vertex parent) {
		this.parent = parent;
	}

	public VertexColor getColor() {
		return color;
	}

	public void setColor(VertexColor color) {
		this.color = color;
	}

	public int getOrderLabel() {
		return orderLabel;
	}

	public void setOrderLabel(int orderLabel) {
		this.orderLabel = orderLabel;
	}

	public VisitedVertex getLeader() {
		return leader;
	}

	public void setLeader(VisitedVertex leader) {
		this.leader = leader;
	}

	@Override
	public String toString() {
		return String.format("| Vertex: %s | Color: %s | D: %s | F: %s | Order label: %s |", vertex, color, discovTime, finishTime,
				orderLabel);
	}

	@Override
	public int compareTo(VisitedVertex o) {
		return vertex.compareTo(o.getVertex());
	}
}
