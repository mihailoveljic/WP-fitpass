package beans.models;

import java.io.Serializable;

public class WorkingHours implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7032291776794838389L;
	private long id;
	private int fromThe;
	private int toThe;
	
	public WorkingHours() {}

	public WorkingHours(long id, int fromThe, int toThe) {
		super();
		this.id = id;
		this.fromThe = fromThe;
		this.toThe = toThe;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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
