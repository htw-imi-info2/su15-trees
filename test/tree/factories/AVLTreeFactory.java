package tree.factories;

import tree.AVLTree;
import tree.Tree;

public class AVLTreeFactory<K extends Comparable<K>,V> implements TreeFactory<K, V> {

	@Override
	public  Tree<K, V> createTree() {
		return new AVLTree<K,V>();
	}

	@Override
	public boolean isAVLTree() {
		return true;
	}

}
