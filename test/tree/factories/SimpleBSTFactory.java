package tree.factories;

import tree.Tree;

public class SimpleBSTFactory<K extends Comparable<K>,V> implements TreeFactory<K, V> {

	@Override
	public  Tree<K, V> createTree() {
		return new Tree<K,V>();
	}
	@Override
	public boolean isAVLTree() {
		return false;
	}
}
