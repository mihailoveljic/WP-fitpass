package beans.dtos;

import beans.enums.ApprovalStatus;

public class GuestbookDTO {
	private long id;
	private BuyerDTO buyer;
	private SportsFacilityDTO sportsFacility;
	private String comment;
	private double rating;
	private ApprovalStatus approvalStatus;
	
	public GuestbookDTO() {}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public BuyerDTO getBuyer() {
		return buyer;
	}
	public void setBuyer(BuyerDTO buyer) {
		this.buyer = buyer;
	}
	public SportsFacilityDTO getSportsFacility() {
		return sportsFacility;
	}
	public void setSportsFacility(SportsFacilityDTO sportsFacility) {
		this.sportsFacility = sportsFacility;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public ApprovalStatus getApprovalStatus() {
		return approvalStatus;
	}
	public void setApprovalStatus(ApprovalStatus approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	
	
}
