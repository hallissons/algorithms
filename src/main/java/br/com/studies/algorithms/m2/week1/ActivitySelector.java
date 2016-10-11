package br.com.studies.algorithms.m2.week1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.PriorityQueue;

import br.com.studies.algorithms.domain.activity.Activity;

public class ActivitySelector {
	private final PriorityQueue<Activity> activities;
	private Collection<Activity> mutuallyCompatible;
	
	public ActivitySelector(Collection<Activity> activities){
		this.activities = new PriorityQueue<>(new ActivityFinishTimeComparator());
		this.activities.addAll(activities);
		this.mutuallyCompatible = new ArrayList<>();
	}
	
	public Collection<Activity> getMutuallyCompatible(){
		if(mutuallyCompatible.isEmpty() && !activities.isEmpty()){
			prioritize();
		}
		
		return mutuallyCompatible;
	}
	
	private void prioritize(){
		Activity m = activities.poll();
		mutuallyCompatible.add(m);
		
		while(!activities.isEmpty()){
			Activity current = activities.poll();
			if(current.getStart() >= m.getFinish()){
				mutuallyCompatible.add(current);
				m = current;
			}
		}
	}
	
	private static class ActivityFinishTimeComparator implements Comparator<Activity> {
		@Override
		public int compare(Activity a1, Activity a2) {
			int comp = a1.getFinish().compareTo(a2.getFinish());
			if(comp == 0){
				return a1.getStart().compareTo(a2.getStart());
			}
			return comp;
		}
	}
}
