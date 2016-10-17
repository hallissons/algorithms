package br.com.studies.algorithms.m2.week1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.studies.algorithms.domain.activity.Activity;
import br.com.studies.algorithms.domain.activity.WeightedActivity;
import br.com.studies.algorithms.m2.week1.WeightedActivitySelector.DifferenceComparator;
import br.com.studies.algorithms.m2.week1.WeightedActivitySelector.RatioComparator;
import br.com.studies.algorithms.utils.FileUtils;

public class ActivityPriorityTests {

	private Collection<Activity> activities;

	@Before
	public void setUp() {
		activities = new ArrayList<>();

		activities.add(new Activity(2, 14));
		activities.add(new Activity(12, 16));
		activities.add(new Activity(3, 9));
		activities.add(new Activity(8, 12));
		activities.add(new Activity(6, 10));
		activities.add(new Activity(1, 4));
		activities.add(new Activity(3, 5));
		activities.add(new Activity(8, 11));
		activities.add(new Activity(0, 6));
		activities.add(new Activity(5, 7));
		activities.add(new Activity(5, 9));
	}

	@Test
	public void testPrioritize() {
		ActivitySelector as = new ActivitySelector(activities);
		Assert.assertEquals(4, as.getMutuallyCompatible().size());
	}

	@Test
	public void testWeightedActivitySelector() {
		List<String> lines = FileUtils.readAllLines("br/com/studies/algorithms/m2/week1/jobs_1.txt");

		List<WeightedActivity> actv = new ArrayList<>();
		for (int i = 1; i < lines.size(); i++) {
			String[] wa = lines.get(i).split("\\s");
			actv.add(new WeightedActivity(i, Integer.parseInt(wa[0]), Integer.parseInt(wa[1])));
		}

		WeightedActivitySelector was = new WeightedActivitySelector(actv);

		Assert.assertEquals(31814, was.sum(new DifferenceComparator()));
		Assert.assertEquals(31814, was.sum(new RatioComparator()));
	}
}
