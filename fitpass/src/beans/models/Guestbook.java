package beans.models;

import java.io.Serializable;

import beans.enums.ApprovalStatus;

public class Guestbook implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 480360508148381061L;
	private long id;
	private long buyerId;
	private long sportsFacilityId;
	private String comment;
	private int rating;
	private ApprovalStatus approvalStatus;
	private boolean isDeleted;
	
	public Guestbook() {}

	public Guestbook(long id, long buyerId, long sportsFacilityId, String comment, int rating, ApprovalStatus approvalStatus, boolean isDeleted) {
		super();
		this.id = id;
		this.buyerId = buyerId;
		this.sportsFacilityId = sportsFacilityId;
		this.comment = comment;
		this.rating = rating;
		this.approvalStatus = approvalStatus;
		this.isDeleted = isDeleted;
	}

	public boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(long buyerId) {
		this.buyerId = buyerId;
	}

	public long getSportsFacilityId() {
		return sportsFacilityId;
	}

	public void setSportsFacilityId(long sportsFacilityId) {
		this.sportsFacilityId = sportsFacilityId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public ApprovalStatus getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(ApprovalStatus approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	

	
}
