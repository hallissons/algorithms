package br.com.studies.algorithms.m1.week6;

import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import br.com.studies.algorithms.m1.week6.TwoSumInterval;
import br.com.studies.algorithms.utils.FileUtils;

public class TwoSumIntervalTests {

	private static int BEGIN_INTER = -10000;
	private static int END_INTER = 10000;
	
	@Test
	public void testBasicInterval(){
		List<String> entities = FileUtils.readAllLines("br/com/studies/algorithms/m1/week6/algo1-programming_prob-2sum_simple.txt");
		TwoSumInterval twoSumInterval = new TwoSumInterval(entities, BEGIN_INTER, END_INTER);
		
		Assert.assertEquals(0, twoSumInterval.countTarget());
	}
	
	
	@Test
	@Ignore
	public void testInterval(){
		List<String> entities = FileUtils.readAllLines("br/com/studies/algorithms/m1/week6/algo1-programming_prob-2sum.txt");
		TwoSumInterval twoSumInterval = new TwoSumInterval(entities, BEGIN_INTER, END_INTER);
		
		System.out.println(twoSumInterval.countTarget());
	}
}
