package br.com.studies.algorithms.week5;

import org.junit.Assert;
import org.junit.Test;

import br.com.studies.algorithms.TreeBST;
import br.com.studies.algorithms.TreeRB;

public class TreeTests {

	@Test
	public void testCreateSimpleTreeDS() {
		TreeBST t = new TreeBST();

		t.insert(1);
		t.insert(2);
		t.insert(3);
		t.insert(4);

		Assert.assertEquals(4, t.size());
	}

	@Test
	public void testGetMinNode() {
		TreeBST t = new TreeBST();

		t.insert(10);
		t.insert(12);
		t.insert(35);
		t.insert(4);
		t.insert(1);
		t.insert(90);
		t.insert(0);

		Assert.assertEquals(0, t.min().getData());
	}

	@Test
	public void testGetMaxNode() {
		TreeBST t = new TreeBST();

		t.insert(10);
		t.insert(12);
		t.insert(35);
		t.insert(4);
		t.insert(1);
		t.insert(90);
		t.insert(0);

		Assert.assertEquals(90, t.max().getData());
	}

	@Test
	public void testSearch() {
		TreeBST t = new TreeBST();

		t.insert(10);
		t.insert(12);
		t.insert(35);
		t.insert(4);
		t.insert(1);
		t.insert(90);
		t.insert(0);

		Assert.assertEquals(0, t.search(0).getData());
	}

	@Test
	public void testDelete() {
		TreeBST t = new TreeBST();

		t.insert(10);
		t.insert(12);
		t.insert(35);
		t.insert(4);
		t.insert(1);
		t.insert(90);
		t.insert(0);

		t.delete(10);

		Assert.assertEquals(6, t.size());
	}

	@Test
	public void testInOrder() {
		TreeBST t = new TreeBST();

		t.insert(10);
		t.insert(12);
		t.insert(35);
		t.insert(4);
		t.insert(1);
		t.insert(90);
		t.insert(0);

		Assert.assertEquals("0 1 4 10 12 35 90", t.inOrderWalk());
	}

	@Test
	public void testInsertTreeRB() {
		TreeRB t = new TreeRB();

		t.insert(1);
		t.insert(2);
		t.insert(3);
		t.insert(4);
		t.insert(5);
		t.insert(6);

		Assert.assertEquals("1 2 3 4 5 6", t.inOrderWalk());
	}

	@Test
	public void testDeleteTreeRB() {
		TreeRB t = new TreeRB();

		t.insert(1);
		t.insert(2);
		t.insert(3);
		t.insert(4);
		t.insert(5);
		t.insert(6);

		t.delete(4);
		t.delete(1);
		t.delete(6);

		Assert.assertEquals("2 3 5", t.inOrderWalk());
	}
}
