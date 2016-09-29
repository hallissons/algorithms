package br.com.studies.algorithms;

@SuppressWarnings("rawtypes")
public class TreeBST {
	protected final Tree tree;
	protected int size;

	public TreeBST() {
		this(new Tree());
	}

	public TreeBST(Tree tree) {
		this.tree = tree;
	}

	public Node insert(Comparable data) {
		Node y = null;
		Node z = new Node(data);
		Node x = tree.getRoot();

		while (x != null) {
			y = x;
			if (z.getData().compareTo(x.getData()) < 0) {
				x = x.getLeft();
			} else {
				x = x.getRight();
			}
		}
		z.setParent(y);

		// Tree is empty
		if (y == null) {
			tree.setRoot(z);
		} else if (z.getData().compareTo(y.getData()) < 0) {
			y.setLeft(z);
		} else {
			y.setRight(z);
		}

		size++;

		return z;
	}

	public void delete(Comparable data) {
		Node z = search(data);
		if (z == null) {
			return;
		}
		delete(z);
		size--;
	}

	public void delete(Node z) {
		if (z.getLeft() == null) {
			transplant(z, z.getRight());
		} else if (z.getRight() == null) {
			transplant(z, z.getLeft());
		} else {
			Node y = min(z.getRight());
			if (!z.equals(y.getParent())) {
				transplant(y, y.getRight());
				y.setRight(z.getRight());
				y.getRight().setParent(y);
			}
			transplant(z, y);
			y.setLeft(z.getLeft());
			y.getLeft().setParent(y);
		}
	}

	public Node successor(Node t) {
		if (t == null) {
			return null;
		} else if (t.getRight() != null) {
			Node p = t.getRight();
			while (p.getLeft() != null) {
				p = p.getLeft();
			}
			return p;
		} else {
			Node p = t.getParent();
			Node ch = t;
			while (p != null && ch == p.getRight()) {
				ch = p;
				p = p.getParent();
			}
			return p;
		}
	}

	public String inOrderWalk() {
		StringBuilder w = new StringBuilder();
		inOrderWalk(tree.getRoot(), w);
		return w.toString().trim();
	}

	public Node search(Comparable k) {
		Node x = tree.getRoot();
		while (x != null && !k.equals(x.getData())) {
			if (k.compareTo(x.getData()) < 0) {
				x = x.getLeft();
			} else {
				x = x.getRight();
			}
		}
		return x;
	}

	public Node min() {
		return min(tree.getRoot());
	}

	public Node max() {
		return max(tree.getRoot());
	}

	public int size() {
		return size;
	}

	protected Node min(Node x) {
		while (x.getLeft() != null) {
			x = x.getLeft();
		}
		return x;
	}

	protected Node max(Node x) {
		while (x.getRight() != null) {
			x = x.getRight();
		}
		return x;
	}

	private void inOrderWalk(Node x, StringBuilder r) {
		if (x != null) {
			inOrderWalk(x.getLeft(), r);
			r.append(String.format("%s ", x.getData()));
			inOrderWalk(x.getRight(), r);
		}
	}

	protected void transplant(Node u, Node v) {
		if (u.getParent() == null) {
			tree.setRoot(v);
		} else if (u.equals(u.getParent().getLeft())) {
			u.getParent().setLeft(v);
		} else {
			u.getParent().setRight(v);
		}

		if (v != null) {
			v.setParent(u.getParent());
		}
	}
}
