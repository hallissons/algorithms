package br.com.studies.algorithms;

public class Tree {
	private Node root;
	
	public Tree(){
	}
	
	public Tree(Node root){
		this.root = root;
	}

	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}
}