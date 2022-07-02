package beans.models;

import java.io.Serializable;

public class SportsFacilityType implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3705251338510953202L;
	private long id;
	private String name;
	private boolean isDeleted;
	
	public SportsFacilityType() {}

	public SportsFacilityType(long id, String name, boolean isDeleted) {
		super();
		this.id = id;
		this.name = name;
		this.isDeleted = isDeleted;
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
	public boolean getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
}
