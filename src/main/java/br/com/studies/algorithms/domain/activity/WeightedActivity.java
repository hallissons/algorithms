package br.com.studies.algorithms.domain.activity;

public class WeightedActivity {

	private final int id;
	private final Integer weight;
	private final Integer length;
	private Float rt;
	private Integer di;

	public WeightedActivity(int id, Integer weight, Integer length) {
		super();
		this.id = id;
		this.weight = weight;
		this.length = length;
	}

	public int getId() {
		return id;
	}

	public Integer getWeight() {
		return weight;
	}

	public Integer getLength() {
		return length;
	}

	public Integer difference() {
		if (di == null) {
			di = weight - length;
		}
		return di;
	}

	public Float ratio() {
		if (rt == null) {
			rt = Float.valueOf(weight) / Float.valueOf(length);
		}
		return rt;
	}

	@Override
	public String toString() {
		return "WeightedActivity [id=" + id + ", weight=" + weight + ", length=" + length + "]";
	}
}
