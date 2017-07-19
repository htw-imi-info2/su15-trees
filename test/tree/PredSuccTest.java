package tree;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import tree.factories.AVLTreeFactory;
import tree.factories.SimpleBSTFactory;
import tree.factories.TreeFactory;

@RunWith(Parameterized.class)
public class PredSuccTest {
	Tree<Integer, Object> tree;
	boolean isAVLTree;

	public PredSuccTest(TreeFactory<Integer, Object> factory) {
		this.tree = factory.createTree();
		this.isAVLTree = factory.isAVLTree();
	}

	@Parameters
	public static Collection<Object> data() {
		return Arrays.asList(
				new Object[] { new SimpleBSTFactory<Integer, Object>(), new AVLTreeFactory<Integer, Object>() });
	}

	@Test
	public void testToStringTree() {
		fillTree();
		
		String stringRep = !isAVLTree ? "((3-7-9)-10-(((-11-13)-15-)-20-30))" : "((3-7-9)-10-((-11-13)-15-(-20-30)))";
		assertEquals(stringRep, tree.toStringTree());
	}

	@Test
	public void testSuccessor2() {
		fillTree();
		assertEquals(11, tree.succ(10).key.intValue());
	}

	private void fillTree() {
		tree.insert(new Integer[] { 10, 7, 3, 9, 20, 15, 11, 13, 30 });
	}

	@Test
	public void testPredecessor() {
		fillTree();
		assertEquals(9, tree.pred(tree.root.key).key.intValue());
	}

	@Test
	public void testSuccNoRightChild() {
		fillTree();
		assertEquals(20, tree.succ(15).key.intValue());
	}

	@Test
	public void testSuccSimple() {
		fillTree();
		assertEquals(13, tree.succ(11).key.intValue());
	}

	@Test
	public void testPredNoLeftChild() {
		fillTree();
		assertEquals(10, tree.pred(11).key.intValue());
	}

	@Test
	public void testPredecessorIfNonExistent() {
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
