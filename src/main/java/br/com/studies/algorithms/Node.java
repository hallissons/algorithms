package br.com.studies.algorithms;

@SuppressWarnings("rawtypes")
public class Node {
	private Node parent;
	private Node left;
	private Node right;
	private NodeColor color;
	private Comparable data;

	public Node(Comparable data) {
		this.data = data;
		this.color = NodeColor.BLACK;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	public Comparable getData() {
		return data;
	}

	public void setData(Comparable data) {
		this.data = data;
	}

	public NodeColor getColor() {
		return color;
	}

	public void setColor(NodeColor color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return data.toString();
	}
	
	protected static enum NodeColor {
		RED,
		BLACK
	}
}
