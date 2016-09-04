package br.com.studies.algorithms.part1;

import java.util.List;

public final class TestSortUtils {

	private TestSortUtils(){}
	
	public static boolean isOrdered(List<Object> ls){
		for (int i = 1; i < ls.size(); i++) {
			if(SortUtils.toComparable(ls.get(i)).compareTo(ls.get(i-1)) < 0){
				return false;
			}
		}
		
		return true;
	}
}
