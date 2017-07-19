package tree.factories;

import tree.Tree;

public interface TreeFactory<K extends Comparable<K>,V> {
	public Tree<K,V> createTree();
	public boolean isAVLTree();
}
