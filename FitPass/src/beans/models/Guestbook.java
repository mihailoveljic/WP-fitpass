package beans.models;

import java.io.Serializable;

public class Guestbook implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 480360508148381061L;
	private long id;
	private Buyer buyer;
	private SportsFacility sportsFacility;
	private String comment;
	private int rating;
	
	public Guestbook() {}

	public Guestbook(long id, Buyer buyer, SportsFacility sportsFacility, String comment, int rating) {
		super();
		this.id = id;
		this.buyer = buyer;
		this.sportsFacility = sportsFacility;
		this.comment = comment;
		this.rating = rating;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Buyer getBuyer() {
		return buyer;
	}

	public void setBuyer(Buyer buyer) {
		this.buyer = buyer;
	}

	public SportsFacility getSportsFacility() {
		return sportsFacility;
	}

	public void setSportsFacility(SportsFacility sportsFacility) {
		this.sportsFacility = sportsFacility;
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
