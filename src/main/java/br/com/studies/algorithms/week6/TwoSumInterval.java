package br.com.studies.algorithms.week6;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class TwoSumInterval {
	private final Map<Long, Integer> data;
	private final int begin;
	private final int end;
	private int count;

	public TwoSumInterval(List<String> entities, int begin, int end) {
		data = new HashMap<>();
		for (int i = 0; i < entities.size(); i++) {
			String str = entities.get(i);
			data.put(Long.parseLong(str), i);
		}
		this.begin = begin;
		this.end = end;
	}

	public int countTarget() {
		count = 0;
		double perCount = 0;
		for (int i = begin; i <= end; i++) {
			if (twoSumExists(i)) {
				count++;
			}
			perCount++;
			if (perCount % 100 == 0) {
				double per = perCount / (Math.abs(begin) + Math.abs(end));
				System.out.println(Math.round((per * 100)) + "%");
			}
		}
		return count;
	}

	private boolean twoSumExists(int target) {
		for (Entry<Long, Integer> ent : data.entrySet()) {
			Long comp = target - ent.getKey();
			if (comp != ent.getKey() && data.containsKey(comp)) {
				return true;
			}
		}
		return false;
	}
}
