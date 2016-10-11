package br.com.studies.algorithms.domain.tree;

@SuppressWarnings("rawtypes")
public class Node {
	private Node parent;
	private Node left;
	private Node right;
	private NodeColor color;
	private Comparable data;
	private int n;

	public Node(Comparable data) {
		this.data = data;
		this.color = NodeColor.BLACK;
		this.n = 1;
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

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	@Override
	public String toString() {
		return data.toString();
	}
}
