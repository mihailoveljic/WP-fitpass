package beans.models;

import java.io.Serializable;

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
	
	public Guestbook() {}

	public Guestbook(long id, long buyerId, long sportsFacilityId, String comment, int rating) {
		super();
		this.id = id;
		this.buyerId = buyerId;
		this.sportsFacilityId = sportsFacilityId;
		this.comment = comment;
		this.rating = rating;
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

	
}
