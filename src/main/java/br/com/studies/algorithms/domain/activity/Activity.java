package br.com.studies.algorithms.domain.activity;

public class Activity {
	private final Integer start;
	private final Integer finish;

	public Activity(int start, int finish) {
		super();
		this.start = start;
		this.finish = finish;
	}

	public Integer getStart() {
		return start;
	}

	public Integer getFinish() {
		return finish;
	}

	@Override
	public String toString() {
		return "Activity [start=" + start + ", finish=" + finish + "]";
	}
	
	
}
