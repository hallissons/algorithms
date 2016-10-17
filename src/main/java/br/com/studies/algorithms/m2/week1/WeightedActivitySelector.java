package br.com.studies.algorithms.m2.week1;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.com.studies.algorithms.domain.activity.WeightedActivity;

public class WeightedActivitySelector {
	private final List<WeightedActivity> activities;

	public WeightedActivitySelector(List<WeightedActivity> activities) {
		this.activities = activities;
	}

	public long sum(Comparator<WeightedActivity> comp) {
		Collections.sort(activities, comp);
		
		long sumLength = 0;
		long sumWeighted = 0;

		for (WeightedActivity job : activities) {
			sumLength += job.getLength();
			sumWeighted += (sumLength * job.getWeight());
		}

		return sumWeighted;
	}

	static class DifferenceComparator implements Comparator<WeightedActivity> {
		@Override
		public int compare(WeightedActivity a1, WeightedActivity a2) {
			int comp = a2.difference().compareTo(a1.difference());
			if (comp == 0) {
				return a2.getWeight().compareTo(a1.getWeight());
			}
			return comp;
		}
	}
	
	static class RatioComparator implements Comparator<WeightedActivity> {
		@Override
		public int compare(WeightedActivity a1, WeightedActivity a2) {
			int comp = a2.ratio().compareTo(a1.ratio());
			if (comp == 0) {
				return a2.getWeight().compareTo(a1.getWeight());
			}
			return comp;
		}
	}
}
