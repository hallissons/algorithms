package br.com.studies.algorithms.domain.activity;

public class WeightedActivity {

	private final int id;
	private final Integer weight;
	private final Integer length;

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
		return weight - length;
	}

	public Float ratio() {
		return Float.valueOf(weight) / Float.valueOf(length);
	}

	@Override
	public String toString() {
		return "WeightedActivity [id=" + id + ", weight=" + weight + ", length=" + length + "]";
	}
}
