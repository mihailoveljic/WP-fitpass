package beans.models;


public class Guestbook {
	private double id;
	private Buyer buyer;
	private SportsFacility sportsFacility;
	private String comment;
	private int rating;
	
	public Guestbook() {}

	public Guestbook(double id, Buyer buyer, SportsFacility sportsFacility, String comment, int rating) {
		super();
		this.id = id;
		this.buyer = buyer;
		this.sportsFacility = sportsFacility;
		this.comment = comment;
		this.rating = rating;
	}

	public double getId() {
		return id;
	}

	public void setId(double id) {
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
