package br.com.studies.algorithms;

import br.com.studies.algorithms.domain.tree.Node;
import br.com.studies.algorithms.domain.tree.NodeColor;

//TODO Implement rank + order statistics
@SuppressWarnings("rawtypes")
public class TreeRB extends TreeBST {

	private void rotateLeft(Node x) {
		if (x == null) {
			return;
		}

		// turn y’s left subtree into x’s right subtree
		Node y = x.getRight();
		x.setRight(y.getLeft());
		if (y.getLeft() != null) {
			y.getLeft().setParent(x);
		}

		// link x’s parent to y
		y.setParent(x.getParent());

		if (x.getParent() == null) {
			tree.setRoot(y);
		} else if (x.equals(x.getParent().getLeft())) {
			x.getParent().setLeft(y);
		} else {
			x.getParent().setRight(y);
		}

		// put x on y’s left
		y.setLeft(x);
		x.setParent(y);

		//FIXME Not computing the right n for each node
		// y.setN(x.getN());
		// x.setN(size(x.getLeft()) + size(x.getRight()) + 1);
	}

	private void rotateRight(Node x) {
		if (x == null) {
			return;
		}

		// turn y’s left subtree into x’s left subtree
		Node y = x.getLeft();
		x.setLeft(y.getRight());
		if (y.getRight() != null) {
			y.getRight().setParent(x);
		}
		// link x’sparent to y
		y.setParent(x.getParent());

		if (x.getParent() == null) {
			tree.setRoot(y);
		} else if (x.equals(x.getParent().getRight())) {
			x.getParent().setRight(y);
		} else {
			x.getParent().setLeft(y);
		}

		// put x on y’s right
		y.setRight(x);
		x.setParent(y);

		//FIXME Not computing the right n for each node
		// y.setN(x.getN());
		// x.setN(size(x.getLeft()) + size(x.getRight()) + 1);
	}

	public Node put(Comparable data) {
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
			//y.setN(1 + size(y.getLeft()) + size(y.getRight()));
		}
		z.setParent(y);

		// Tree is empty
		if (y == null) {
			tree.setRoot(z);
			//z.setN(1);
			return z;
		} else if (z.getData().compareTo(y.getData()) < 0) {
			y.setLeft(z);
		} else {
			y.setRight(z);
		}
		z.setLeft(null);
		z.setRight(null);
		z.setColor(NodeColor.RED);
		insertFixup(z);
		
		return z;
	}

	public void delete(Node p) {
		// If strictly internal, copy successor's element to p and then make p
		// point to successor.
		if (p.getLeft() != null && p.getRight() != null) {
			Node s = successor(p);
			p.setData(s.getData());
			p = s;
		} // p has 2 children

		// Start fixup at replacement node, if it exists.
		Node replacement = (p.getLeft() != null ? p.getLeft() : p.getRight());

		if (replacement != null) {
			// Link replacement to parent
			replacement.setParent(p.getParent());
			if (p.getParent() == null) {
				tree.setRoot(replacement);
			} else if (p.equals(p.getParent().getLeft())) {
				p.getParent().setLeft(replacement);
			} else {
				p.getParent().setRight(replacement);
			}

			// Null out links so they are OK to use by fixAfterDeletion.
			p.setLeft(null);
			p.setRight(null);
			p.setParent(null);

			// Fix replacement
			if (NodeColor.BLACK.equals(p.getColor())) {
				deleteFixup(replacement);
			}
		} else if (p.getParent() == null) { // return if we are the only
											// node.
			tree.setRoot(null);
		} else { // No children. Use self as phantom replacement and unlink.
			if (NodeColor.BLACK.equals(p.getColor())) {
				deleteFixup(p);
			}

			if (p.getParent() != null) {
				if (p.equals(p.getParent().getLeft())) {
					p.getParent().setLeft(null);
				} else if (p.equals(p.getParent().getRight())) {
					p.getParent().setRight(null);
				}
				p.setParent(null);

			}
		}
	}

	//TODO Implement null safe checker for each operation
	private void deleteFixup(Node x) {
		while (!tree.getRoot().equals(x) && NodeColor.BLACK.equals(x.getColor())) {
			if (x.equals(x.getParent().getLeft())) {
				Node w = x.getParent().getRight();
				if (NodeColor.RED.equals(w.getColor())) {
					w.setColor(NodeColor.BLACK);
					x.getParent().setColor(NodeColor.RED);
					rotateLeft(x.getParent());
					w = x.getParent().getRight();
				}
				if (NodeColor.BLACK.equals(colorOf(w.getLeft())) && NodeColor.BLACK.equals(colorOf(w.getRight()))) {
					w.setColor(NodeColor.RED);
					x = x.getParent();
				} else {
					if (NodeColor.BLACK.equals(w.getRight().getColor())) {
						w.getLeft().setColor(NodeColor.BLACK);
						w.setColor(NodeColor.RED);
						rotateRight(w);
						w = x.getParent().getRight();
					}
				}
				w.setColor(x.getParent().getColor());
				x.getParent().setColor(NodeColor.BLACK);
				if (w.getRight() != null) {
					w.getRight().setColor(NodeColor.BLACK);
				}
				rotateLeft(x.getParent());
				x = tree.getRoot();
			} else {
				Node w = x.getParent().getLeft();
				if (NodeColor.RED.equals(w.getColor())) {
					w.setColor(NodeColor.BLACK);
					x.getParent().setColor(NodeColor.RED);
					rotateRight(x.getParent());
					w = x.getParent().getLeft();
				}
				if (NodeColor.BLACK.equals(colorOf(w.getRight())) && NodeColor.BLACK.equals(colorOf(w.getLeft()))) {
					w.setColor(NodeColor.RED);
					x = x.getParent();
				} else {
					if (NodeColor.BLACK.equals(w.getLeft().getColor())) {
						w.getRight().setColor(NodeColor.BLACK);
						w.setColor(NodeColor.RED);
						rotateLeft(w);
						w = x.getParent().getLeft();
					}
				}
				w.setColor(x.getParent().getColor());
				x.getParent().setColor(NodeColor.BLACK);
				w.getLeft().setColor(NodeColor.BLACK);
				rotateRight(x.getParent());
				x = tree.getRoot();
			}
		}
		x.setColor(NodeColor.BLACK);
	}

	//TODO Implement null safe checker for each operation
	private void insertFixup(Node z) {
		while (z.getParent() != null && NodeColor.RED.equals(colorOf(z.getParent()))) {
			if (z.getParent().equals(z.getParent().getParent().getLeft())) {
				Node y = z.getParent().getParent().getRight();
				if (NodeColor.RED.equals(colorOf(y))) {
					z.getParent().setColor(NodeColor.BLACK);
					y.setColor(NodeColor.BLACK);
					z.getParent().getParent().setColor(NodeColor.RED);
					z = z.getParent().getParent();
				} else {
					if (z.equals(z.getParent().getRight())) {
						z = z.getParent();
						rotateLeft(z);
					}
					z.getParent().setColor(NodeColor.BLACK);
					z.getParent().getParent().setColor(NodeColor.RED);
					rotateRight(z.getParent().getParent());
				}

			} else {
				Node y = z.getParent().getParent().getLeft();
				if (y != null && NodeColor.RED.equals(colorOf(y))) {
					z.getParent().setColor(NodeColor.BLACK);
					y.setColor(NodeColor.BLACK);
					z.getParent().getParent().setColor(NodeColor.RED);
					z = z.getParent().getParent();
				} else {
					if (z.equals(z.getParent().getLeft())) {
						z = z.getParent();
						rotateRight(z);
					}
					z.getParent().setColor(NodeColor.BLACK);
					z.getParent().getParent().setColor(NodeColor.RED);
					rotateLeft(z.getParent().getParent());
				}
			}
		}
		//tree.getRoot().setN(size(tree.getRoot().getLeft()) + size(tree.getRoot().getRight()) + 1);
		tree.getRoot().setColor(NodeColor.BLACK);
	}

	private NodeColor colorOf(Node p) {
		return (p == null ? NodeColor.BLACK : p.getColor());
	}
}
