package br.com.studies.algorithms.m1.week6;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.studies.algorithms.TreeBST;
import br.com.studies.algorithms.utils.FileUtils;

public class MedianMaintenceTests {

	@Test
	public void testSumMedians1() {		
		List<String> entities = FileUtils.readAllLines("br/com/studies/algorithms/m1/week6/median-maintence-tests1.txt");
		long sum = sumMedians(entities);
		Assert.assertEquals(2, sum);
	}
	
	@Test
	public void testSumMedians2() {		
		List<String> entities = FileUtils.readAllLines("br/com/studies/algorithms/m1/week6/median-maintence-tests2.txt");
		long sum = sumMedians(entities);
		Assert.assertEquals(5, sum);
	}
	
	@Test
	public void testSumMediansF() {		
		List<String> entities = FileUtils.readAllLines("br/com/studies/algorithms/m1/week6/Median.txt");
		long sum = sumMedians(entities);
		Assert.assertEquals(1213, sum);
	}
	
	private long sumMedians(List<String> entities){
		TreeBST tree = new TreeBST();
		long sum = 0;
		int count = 0;
		for (String ent : entities) {
			tree.put(Long.parseLong(ent));
			count ++;
			int pos = count % 2 == 0 ? count / 2 : (count + 1 ) / 2;
			sum += (Long) tree.select(pos-1);
		}
		return sum % count;
	}
}
