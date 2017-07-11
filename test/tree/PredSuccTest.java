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
		assertEquals("((3-7-9)-10-(((-11-13)-15-)-20-30))",tree.toStringTree());
		assertEquals(11, tree.succ(10).key.intValue());
	}
	@Test
	public void testPredecessor() {
		Tree<Integer, Object> tree = new Tree<Integer, Object>();
		tree.insert(new Integer[] { 10, 7, 3, 9, 20, 15, 11, 13, 30 });
		assertEquals("((3-7-9)-10-(((-11-13)-15-)-20-30))",tree.toStringTree());
		assertEquals(9, tree.pred(tree.root.key).key.intValue());
	}
	@Test
	public void testSuccNoRightChild() {
		Tree<Integer, Object> tree = new Tree<Integer, Object>();
		tree.insert(new Integer[] { 10, 7, 3, 9, 20, 15, 11, 13, 30 });
		assertEquals("((3-7-9)-10-(((-11-13)-15-)-20-30))",tree.toStringTree());
		assertEquals(20, tree.succ(15).key.intValue());
	}
	@Test
	public void testSuccSimple() {
		Tree<Integer, Object> tree = new Tree<Integer, Object>();
		tree.insert(new Integer[] { 10, 7, 3, 9, 20, 15, 11, 13, 30 });
		assertEquals("((3-7-9)-10-(((-11-13)-15-)-20-30))",tree.toStringTree());
		assertEquals(13, tree.succ(11).key.intValue());
	}
	@Test
	public void testPredNoLeftChild() {
		Tree<Integer, Object> tree = new Tree<Integer, Object>();
		tree.insert(new Integer[] { 10, 7, 3, 9, 20, 15, 11, 13, 30 });
		assertEquals("((3-7-9)-10-(((-11-13)-15-)-20-30))",tree.toStringTree());
		assertEquals(10, tree.pred(11).key.intValue());
	}
	@Test
	public void testPredecessorIfNonExistent() {
		Tree<Integer, Object> tree = new Tree<Integer, Object>();
		tree.insert(new Integer[] { 10, 11 });
		assertEquals("(10,,(11,,))", tree.root.toString());
		assertEquals(null, tree.pred(tree.root.key));
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
		assertEquals(11, it.successor(5).key.intValue());
		assertEquals(5, it.successor(1).key.intValue());
		assertEquals(12, it.successor(11).key.intValue());
		assertEquals(20, it.successor(12).key.intValue());
		assertEquals(23, it.successor(20).key.intValue());
		assertEquals(28, it.successor(23).key.intValue());
	}

}
