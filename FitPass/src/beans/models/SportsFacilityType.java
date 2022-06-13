package beans.models;

import java.io.Serializable;

public class SportsFacilityType implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3705251338510953202L;
	private long id;
	private String name;
	
	public SportsFacilityType() {}

	public SportsFacilityType(long id, String name) {
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
