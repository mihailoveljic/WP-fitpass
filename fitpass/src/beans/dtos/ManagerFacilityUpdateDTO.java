package beans.dtos;

public class ManagerFacilityUpdateDTO {
	private long managerId;
	private long facilityId;
	
	public ManagerFacilityUpdateDTO() { }
	
	public long getManagerId() {
		return managerId;
	}
	public void setManagerId(long managerId) {
		this.managerId = managerId;
	}
	public long getFacilityId() {
		return facilityId;
	}
	public void setFacilityId(long facilityId) {
		this.facilityId = facilityId;
	}
	
}
