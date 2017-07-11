package tree;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PredSuccTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testSuccessor2() {
		Tree<Integer, Object> tree = new Tree<Integer, Object>();
		tree.insert(new Integer[] { 10, 7, 3, 9, 20, 15, 11, 13, 30 });
		assertEquals(11, tree.succ(tree.root).key.intValue());
	}
	@Test
	public void testPredecessor() {
		Tree<Integer, Object> tree = new Tree<Integer, Object>();
		tree.insert(new Integer[] { 10, 7, 3, 9, 20, 15, 11, 13, 30 });
		assertEquals(9, tree.pred(tree.root).key.intValue());
	}
	@Test
	public void testPredecessorIfNonExistent() {
		Tree<Integer, Object> tree = new Tree<Integer, Object>();
		tree.insert(new Integer[] { 10, 11 });
		assertEquals("(10,,(11,,))", tree.root.toString());
		assertEquals(null, tree.pred(tree.root));
	}

	@Test
	public void testSuccessor() {
		Tree<Integer, Object> it = new Tree<Integer, Object>();
		it.insert(12);
		it.insert(5);
		it.insert(20);
		it.insert(11);
		it.insert(23);
		it.insert(28);
		it.insert(1);
		assertEquals("1, 5, 11, 12, 20, 23, 28", it.toString());
		assertEquals("((1-5-11)-12-(-20-(-23-28)))", it.toStringTree());
		assertEquals(Integer.valueOf(11), it.successor(5).key);
		assertEquals(Integer.valueOf(5), it.successor(1).key);
		assertEquals(Integer.valueOf(12), it.successor(11).key);
		assertEquals(Integer.valueOf(20), it.successor(12).key);
		assertEquals(Integer.valueOf(23), it.successor(20).key);
		assertEquals(Integer.valueOf(28), it.successor(23).key);
	}

}
