package beans.models;

import java.io.Serializable;

public class WorkingHours implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7032291776794838389L;
	private String fromThe;
	private String toThe;
	
	public WorkingHours() {}

	public WorkingHours(long id, String fromThe, String toThe) {
		super();
		this.fromThe = fromThe;
		this.toThe = toThe;
	}

	public String getFromThe() {
		return fromThe;
	}

	public void setFromThe(String fromThe) {
		this.fromThe = fromThe;
	}

	public String getToThe() {
		return toThe;
	}

	public void setToThe(String toThe) {
		this.toThe = toThe;
	}

	
}
