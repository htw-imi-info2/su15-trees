package tree;


/**
 * 
 * Entry/Node in the Tree. Could be extended to implement Map.Entry<K,V>
 * 
 * @see java.util.Map.Entry
 */
public class Node<K,V> {
	K key;
	V value;
	Node<K,V> parent;
	Node<K,V> leftChild;
	Node<K,V> rightChild;

	public Node(K key, V value) {
		this.key = key;
		this.value = value;
	}

	public String toString() {
		return "(" + key.toString() + "," + ((leftChild == null) ? "" : leftChild.toString()) + ","
				+ ((rightChild == null) ? "" : rightChild.toString()) + ")";
	}

	public String toStringBalanceFactor() {
		return "(" + key.toString() + "[" + balanceFactor + "],"
				+ ((leftChild == null) ? "" : leftChild.toStringBalanceFactor()) + ","
				+ ((rightChild == null) ? "" : rightChild.toStringBalanceFactor()) + ")";
	}

	public int size() {
		int l = 0, r = 0;
		if (leftChild != null)
			l = leftChild.size();
		if (rightChild != null)
			r = rightChild.size();
		return l + 1 + r;

	}

	public int height() {
		int lh = -1, rh = -1;
		if (leftChild != null)
			lh = 1 + leftChild.height();
		if (rightChild != null)
			rh = 1 + rightChild.height();
		return Math.max(lh, rh);
	}

	int balanceFactor;

	public int computeBalanceFactor() {
		int lh = -1, rh = -1;
		if (leftChild != null)
			lh = 1 + leftChild.height();
		if (rightChild != null)
			rh = 1 + rightChild.height();
		return balanceFactor = lh - rh;
	}

	public Node<K,V> min() {
		if (leftChild == null)
			return this;
		else
			return leftChild.min();
	}

	public Node<K,V> max() {
		if (rightChild == null)
			return this;
		else
			return rightChild.max();
	}

	public Node<K,V> pred() {
		if (leftChild != null)
			return leftChild.max();
		Node<K,V> up = this.parent;
		Node<K,V> succ = this;
		while ((up != null) && succ == up.leftChild) {
			succ = up;
			up = up.parent;
		}
		return up;
	}
	public Node<K,V> succ() {
		if (rightChild != null)
			return rightChild.min();
		Node<K,V> up = this.parent;
		Node<K,V> succ = this;
		while ((up != null) && succ == up.rightChild) {
			succ = up;
			up = up.parent;
		}
		return up;
	}

}