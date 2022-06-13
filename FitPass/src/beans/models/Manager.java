package beans.models;

import java.io.Serializable;

public class Manager extends User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6450422194171631483L;
	private SportsFacility sportsFacility;
	
	public Manager (){}

	public Manager(SportsFacility sportsFacility) {
		super();
		this.sportsFacility = sportsFacility;
	}

	public SportsFacility getSportsFacility() {
		return sportsFacility;
	}

	public void setSportsFacility(SportsFacility sportsFacility) {
		this.sportsFacility = sportsFacility;
	}
	
}
