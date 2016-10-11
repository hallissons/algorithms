package br.com.studies.algorithms.m2.week1;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.studies.algorithms.domain.activity.Activity;

public class ActivityPriorityTests {
	
	private Collection<Activity> activities;
	
	@Before
	public void setUp(){
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
	public void testPrioritize(){
		ActivitySelector as = new ActivitySelector(activities);
		Assert.assertEquals(4, as.getMutuallyCompatible().size());
	}
}
