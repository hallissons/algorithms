package br.com.studies.algorithms.m1.week5;

import org.junit.Assert;
import org.junit.Test;

import br.com.studies.algorithms.TreeBST;
import br.com.studies.algorithms.TreeRB;

public class TreeTests {

	@Test
	public void testCreateSimpleTreeDS() {
		TreeBST t = new TreeBST();

		t.put(1);
		t.put(2);
		t.put(3);
		t.put(4);

		Assert.assertEquals(4, t.size());
	}

	@Test
	public void testGetMinNode() {
		TreeBST t = new TreeBST();

		t.put(10);
		t.put(12);
		t.put(35);
		t.put(4);
		t.put(1);
		t.put(90);
		t.put(0);

		Assert.assertEquals(0, t.min().getData());
	}

	@Test
	public void testGetMaxNode() {
		TreeBST t = new TreeBST();

		t.put(10);
		t.put(12);
		t.put(35);
		t.put(4);
		t.put(1);
		t.put(90);
		t.put(0);

		Assert.assertEquals(90, t.max().getData());
	}

	@Test
	public void testSearch() {
		TreeBST t = new TreeBST();

		t.put(10);
		t.put(12);
		t.put(35);
		t.put(4);
		t.put(1);
		t.put(90);
		t.put(0);

		Assert.assertEquals(0, t.search(0).getData());
	}

	@Test
	public void testInOrder() {
		TreeBST t = new TreeBST();

		t.put(10);
		t.put(12);
		t.put(35);
		t.put(4);
		t.put(1);
		t.put(90);
		t.put(0);

		Assert.assertEquals("0 1 4 10 12 35 90", t.inOrderWalk());
	}

	@Test
	public void testInsertTreeRB() {
		TreeRB t = new TreeRB();

		t.put(1);
		t.put(2);
		t.put(3);
		t.put(4);
		t.put(5);
		t.put(6);

		Assert.assertEquals("1 2 3 4 5 6", t.inOrderWalk());
	}

	@Test
	public void testDeleteTreeRB() {
		TreeRB t = new TreeRB();

		t.put(1);
		t.put(2);
		t.put(3);
		t.put(4);
		t.put(5);
		t.put(6);

		t.delete(4);
		t.delete(1);
		t.delete(6);

		Assert.assertEquals("2 3 5", t.inOrderWalk());
	}
}
