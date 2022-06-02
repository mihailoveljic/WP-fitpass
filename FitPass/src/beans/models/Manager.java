package beans.models;

public class Manager extends User {
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
