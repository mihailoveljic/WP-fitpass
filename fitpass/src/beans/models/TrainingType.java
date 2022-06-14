package beans.models;

import java.io.Serializable;

public class TrainingType implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -570934960790152524L;
	private long id;
	private String name;
	
	public TrainingType() {}

	public TrainingType(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
