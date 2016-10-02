package br.com.studies.algorithms;

@SuppressWarnings("rawtypes")
public class TreeBST {
	protected final Tree tree;

	public TreeBST() {
		this(new Tree());
	}

	public TreeBST(Tree tree) {
		this.tree = tree;
	}

	public Node put(Comparable data) {
		Node root = put(tree.getRoot(), data);
		tree.setRoot(root);
		return root;
	}

	private Node put(Node x, Comparable data) {
		if (x == null) {
			return new Node(data);
		}
		int cmp = data.compareTo(x.getData());
		if (cmp < 0) {
			x.setLeft(put(x.getLeft(), data));
		} else if (cmp > 0) {
			x.setRight(put(x.getRight(), data));
		} else {
			x.setData(data);
		}
		x.setN(1 + size(x.getLeft()) + size(x.getRight()));
		return x;
	}
	
	public void delete(Comparable data) {
		Node z = search(data);
		if (z == null) {
			return;
		}
		delete(z);
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

		z.setN(1 + size(z.getLeft()) + size(z.getRight()));
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
		return size(tree.getRoot());
	}

	/**
	 * Return the kth smallest key in the symbol table.
	 *
	 * @param k
	 *            the order statistic
	 * @return the kth smallest key in the symbol table
	 * @throws IllegalArgumentException
	 *             unless <tt>k</tt> is between 0 and <em>N</em> &minus; 1
	 */
	public Comparable select(int k) {
		if (k < 0 || k >= size()) {
			throw new IllegalArgumentException();
		}
		Node x = select(tree.getRoot(), k);
		return x.getData();
	}

	// Return key of rank k.
	private Node select(Node x, int k) {
		if (x == null) {
			return null;
		}
		int t = size(x.getLeft());
		if (t > k) {
			return select(x.getLeft(), k);
		} else if (t < k) {
			return select(x.getRight(), k - t - 1);
		} else {
			return x;
		}
	}

	// return number of key-value pairs in BST rooted at x
	protected int size(Node x) {
		if (x == null) {
			return 0;
		} else {
			return x.getN();
		}
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
