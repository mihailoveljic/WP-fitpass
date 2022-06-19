package beans.models;

import java.io.Serializable;

public class Manager extends User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6450422194171631483L;
	private long sportsFacilityId;
	
	public Manager (){}

	public Manager(long sportsFacilityId) {
		super();
		this.sportsFacilityId = sportsFacilityId;
	}

	public long getSportsFacilityId() {
		return sportsFacilityId;
	}

	public void setSportsFacilityId(long sportsFacilityId) {
		this.sportsFacilityId = sportsFacilityId;
	}

	
	
}
