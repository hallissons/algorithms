package br.com.studies.algorithms.m1.week1;

import java.util.List;

import br.com.studies.algorithms.utils.ObjectUtils;

public final class TestSortUtils {

	private TestSortUtils(){}
	
	public static boolean isSorted(List<Object> ls){
		for (int i = 1; i < ls.size(); i++) {
			if(ObjectUtils.toComparable(ls.get(i)).compareTo(ls.get(i-1)) < 0){
				return false;
			}
		}
		
		return true;
	}
}
