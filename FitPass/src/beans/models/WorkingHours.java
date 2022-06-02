package beans.models;

public class WorkingHours {
	private double id;
	private int fromThe;
	private int toThe;
	
	public WorkingHours() {}

	public WorkingHours(double id, int fromThe, int toThe) {
		super();
		this.id = id;
		this.fromThe = fromThe;
		this.toThe = toThe;
	}

	public double getId() {
		return id;
	}

	public void setId(double id) {
		this.id = id;
	}

	public int getFromThe() {
		return fromThe;
	}

	public void setFromThe(int fromThe) {
		this.fromThe = fromThe;
	}

	public int getToThe() {
		return toThe;
	}

	public void setToThe(int toThe) {
		this.toThe = toThe;
	}
}
