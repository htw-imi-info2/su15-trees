package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Simple Binary Search Tree Implementation. Algorithms are adaptations of those
 * in Cormen, Leiserson, and Rivest's <em>Introduction to Algorithms.
 * 
 * @author kleinen
 * 
 * @param <K>
 * @param <V>
 */
public class Tree<K extends Comparable<K>, V> implements Iterable<K> {
	private static final String DELIM = ", ";
	protected Node<K,V> root;



	public Node<K,V> find(K key) {
		Node<K,V> currentNode = root;
		while (currentNode != null) {
			int c = key.compareTo(currentNode.key);
			if (c == 0)
				return currentNode;
			else {
				if (c < 0)
					currentNode = currentNode.leftChild;
				else
					currentNode = currentNode.rightChild;
			}
		}
		// didn't find it
		return null;
	}

	public Node<K,V> findRecursive(K key) throws Exception {
		return findRecursive(key, root);
	}

	public Node<K,V> findRecursive(K key, Node<K,V> currentNode) {
		if (currentNode == null)
			return null;
		int c = key.compareTo(currentNode.key);
		if (c == 0)
			return currentNode;
		else {
			if (c < 0)
				return findRecursive(key, root.leftChild);
			else
				return findRecursive(key, root.rightChild);
		}
	}

	public Node<K,V> insert(K key) {
		return insert(key, null);
	}

	public void insert(K[] keys) {
		for (K key : keys) {
			insert(key);
		}
	}

	public Node<K,V> insert(K key, V value) {
		return insert(new Node<K,V>(key, value));
	}

	public Node<K,V> insert(Node<K,V> node) {
		if (root == null) {
			root = node;
			return node;
		}
		Node<K,V> current = root, parent = null;
		while (current != null) {
			parent = current;
			if (node.key.compareTo(current.key) < 0) {
				current = current.leftChild;
				if (current == null) {
					parent.leftChild = node;
					node.parent = parent;
				}
			} else {
				current = current.rightChild;
				if (current == null) {
					parent.rightChild = node;
					node.parent = parent;
				}
			}
		}
		return node;
	}

	public boolean isEmpty() {
		return root == null;
	}

	public Node<K,V> getRoot() {
		return root;
	}

	public String toString() {
		return traverseInOrderToString(root)
				.replaceAll("(" + DELIM + ")+", DELIM)
				.replaceAll("^" + DELIM, "").replaceAll(DELIM + "$", "");
	}

	private String traverseInOrderToString(Node<K,V> node) {
		if (node == null)
			return "";
		else
			return traverseInOrderToString(node.leftChild) + DELIM
					+ node.key.toString() + DELIM
					+ traverseInOrderToString(node.rightChild);
	}

	public List<Node<K,V>> delete(K key) {
		List<Node<K,V>> rebalance = new ArrayList<Node<K,V>>();

		Node<K,V> node = find(key);
		if (node == null)
			return null;
		if (node.leftChild == null) {
			if (node.parent == null)
				// hier wird root geändert
				root = node.rightChild;
			else if (node.parent.leftChild == node)
				// change
				node.parent.leftChild = node.rightChild;
			else
				// change
				node.parent.rightChild = node.rightChild;
			if (node.rightChild != null)
				// change
				node.rightChild.parent = node.parent;
			rebalance.add(node.rightChild == null ? node.parent
					: node.rightChild);
			return rebalance;
		}
		if (node.rightChild == null) {
			if (node.parent == null)
				// change
				root = node.leftChild;
			else if (node.parent.leftChild == node)
				// change
				node.parent.leftChild = node.leftChild;
			else
				// change
				node.parent.rightChild = node.leftChild;
			if (node.leftChild != null)
				node.leftChild.parent = node.parent;
			rebalance.add(node.leftChild);
			return rebalance;
		}
		// node has 2 childs.
		Node<K,V> succ = successor(node);
		if (succ == node.rightChild) {
			transplant(node, succ);
			succ.leftChild = node.leftChild;
			succ.leftChild.parent = succ;
		} else {
			if (succ.rightChild == null)
				rebalance.add(succ.parent);
			else
				rebalance.add(succ.rightChild);
			transplant(succ, succ.rightChild);
			replace(node, succ);
		}
		rebalance.add(succ);
		return rebalance;
	}

	public Node<K,V> successor(K key) {
		return successor(find(key));
	}

	public Node<K,V> successor(Node<K,V> node) {
		if (node.rightChild != null)
			return minimum(node.rightChild);
		Node<K,V> up = node.parent;
		Node<K,V> succ = node;
		while ((up != null) && succ == up.rightChild) {
			succ = up;
			up = up.parent;
		}
		return up;
	}

	public String toStringTree() {
		if (root == null)
			return "()";
		return toStringTree(root);
	}

	public String toStringTree(Node<K,V> node) {
		String left = "(", right = ")", middle = "-";
		if ((node.leftChild == null) && (node.rightChild == null)) {
			left = right = middle = "";
		}
		String result = left;
		if (node.leftChild != null) {
			result += toStringTree(node.leftChild);
		}
		result += middle;
		result += node.key.toString();
		result += middle;
		if (node.rightChild != null) {
			result += toStringTree(node.rightChild);
		}
		result += right;
		return result;
	}

	public Node<K,V> minimum() {
		return minimum(root);
	}

	public Node<K,V> minimum(Node<K,V> node) {
		while (node.leftChild != null)
			node = node.leftChild;
		return node;
	}

	/**
	 * moves subtree starting at b to a's position.
	 * 
	 * @param a
	 * @param b
	 */
	public void transplant(Node<K,V> a, Node<K,V> b) {
		if (a.parent == null) {
			root = b;
			if (b != null)
				b.parent = null;
		} else {
			if (b != null) {
				if (b == b.parent.leftChild)
					b.parent.leftChild = null;
				else
					b.parent.rightChild = null;

				b.parent = a.parent;
			}
			if (a == a.parent.leftChild)
				a.parent.leftChild = b;
			else
				a.parent.rightChild = b;
			a.parent = null;
		}

	}

	/**
	 * replaces node a with node b.
	 * 
	 * @param a
	 * @param b
	 */
	public void replace(Node<K,V> a, Node<K,V> b) {
		if (a.parent == null)
			root = b;
		else {
			if (a.parent.leftChild == a)
				a.parent.leftChild = b;
			else
				a.parent.rightChild = b;
		}
		b.parent = a.parent;
		a.parent = null;
		b.leftChild = a.leftChild;
		if (b.leftChild != null)
			b.leftChild.parent = b;
		b.leftChild.parent = b;
		b.rightChild = a.rightChild;
		if (b.rightChild != null)
			b.rightChild.parent = b;
	}

	public int size() {
		if (root == null)
			return 0;
		return root.size();
	}

	public enum Cursor {
		LEFT, THIS, RIGHT, DONE
	};

	public class Iterator implements java.util.Iterator<K> {
		private Cursor c;
		private Node<K,V> node;
		private Node<K,V> max;

		public Iterator(Node<K,V> node) {
			if (node == null)
				c = Cursor.RIGHT;
			else {
				this.node = node;
				this.c = Cursor.LEFT;
				max = node;
				while (max.rightChild != null)
					max = max.rightChild;
			}

		}

		@Override
		public boolean hasNext() {
			return !((node == max) && c == Cursor.RIGHT);
		}

		@Override
		public K next() {
			switch (c) {
			case LEFT:
				if (node.leftChild == null) {
					c = Cursor.THIS;
					return next();
				} else {
					node = node.leftChild;
					return next();
				}
			case THIS:
				c = Cursor.RIGHT;
				return node.key;

			case RIGHT:
				if (node.rightChild == null) {
					c = Cursor.DONE;
					return next();
				} else {
					c = Cursor.LEFT;
					node = node.rightChild;
					return next();
				}
			case DONE:
				if (node.parent == null)
					throw new NoSuchElementException();
				else {
					if (node.parent.leftChild == node)
						c = Cursor.THIS;
					else
						c = Cursor.DONE;
				}
				node = node.parent;
				return next();
			}
			// should never reach this;
			throw new NoSuchElementException();
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();

		}

	}

	@Override
	public java.util.Iterator<K> iterator() {
		return this.new Iterator(root);
	}

	public Node<K,V> min() {
		return root.min();
	}

	public Node<K,V> max() {
		return root.max();
	}

	public Node<K,V> succ(Node<K,V> node) {
		return node.rightChild.min();
	}

	public Node<K,V> pred(Node<K,V> node) {
		return node.pred();
	}

}
