package tree;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class AVLNodeTest {
	AVLTree<Integer, Object> tree = new AVLTree<Integer, Object>();

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void computeHeightAndBalanceFactorLeaf() {
		Node<Integer, Object> node = new Node<>(4, null);
		assertEquals(-1, node.height());
	}

	@Test
	public void computeHeightAndBalanceFactorOneChild() {
		Node<Integer, Object> a = new Node<>(4, null);
		Node<Integer, Object> b = new Node<>(5, null);
		a.rightChild = b;
		assertEquals(0, a.height());
		a.computeBalanceFactor();
		assertEquals(-1, a.balanceFactor);
	}

	@Test
	public void computeHeightAndBalanceFactorTwoChilds() {
		Node<Integer, Object> a = new Node<>(4, null);
		Node<Integer, Object> b = new Node<>(5, null);
		Node<Integer, Object> c = new Node<>(3, null);
		a.leftChild = b;
		a.rightChild = c;
		assertEquals(0, a.height());
		a.computeBalanceFactor();
		assertEquals(0, a.balanceFactor);
	}

	@Test
	public void computeHeightAndBalanceFactorTwo() {
		Node<Integer, Object> a = new Node<>(4, null);
		Node<Integer, Object> b = new Node<>(5, null);
		Node<Integer, Object> c = new Node<>(3, null);
		Node<Integer, Object> d = new Node<>(2, null);
		a.leftChild = b;
		a.rightChild = c;
		c.leftChild = d;
		assertEquals(1, a.height());
		a.computeBalanceFactor();
		assertEquals(-1, a.balanceFactor);
	}

	@Test
	public void computeHeightAndBalanceFactorThree() {
		Node<Integer, Object> a = new Node<>(4, null);
		Node<Integer, Object> b = new Node<>(5, null);
		Node<Integer, Object> c = new Node<>(3, null);
		Node<Integer, Object> d = new Node<>(6, null);
		Node<Integer, Object> e = new Node<>(7, null);
		a.leftChild = b;
		a.rightChild = c;
		c.rightChild = d;
		d.rightChild = e;
		assertEquals(2, a.height());
		a.computeBalanceFactor();
		assertEquals(-2, a.balanceFactor);
	}

	@Test
	public void computeHeightAndBalanceFactorThree2() {
		Node<Integer, Object> a = new Node<>(4, null);
		Node<Integer, Object> b = new Node<>(5, null);
		Node<Integer, Object> c = new Node<>(3, null);
		Node<Integer, Object> d = new Node<>(6, null);
		Node<Integer, Object> e = new Node<>(7, null);
		a.leftChild = b;
		b.leftChild = c;
		c.leftChild = d;
		d.leftChild = e;
		assertEquals(3, a.height());
		a.computeBalanceFactor();
		assertEquals(4, a.balanceFactor);
	}

}
