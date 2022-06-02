package beans.models;

import java.sql.Date;

public class Membership {
	private double id;
	private double membershipNumber;
	private MembershipType membershipType;
	private Date paymentDate;
	private Date expirationDate;
	private double price;
	private Buyer buyer;
	private boolean active;
	private int numberOfDailyTraining;
	
	public Membership() {}

	public Membership(double id, double membershipNumber, MembershipType membershipType, Date paymentDate,
			Date expirationDate, double price, Buyer buyer, boolean active, int numberOfDailyTraining) {
		super();
		this.id = id;
		this.membershipNumber = membershipNumber;
		this.membershipType = membershipType;
		this.paymentDate = paymentDate;
		this.expirationDate = expirationDate;
		this.price = price;
		this.buyer = buyer;
		this.active = active;
		this.numberOfDailyTraining = numberOfDailyTraining;
	}

	public double getId() {
		return id;
	}

	public void setId(double id) {
		this.id = id;
	}

	public double getMembershipNumber() {
		return membershipNumber;
	}

	public void setMembershipNumber(double membershipNumber) {
		this.membershipNumber = membershipNumber;
	}

	public MembershipType getMembershipType() {
		return membershipType;
	}

	public void setMembershipType(MembershipType membershipType) {
		this.membershipType = membershipType;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Buyer getBuyer() {
		return buyer;
	}

	public void setBuyer(Buyer buyer) {
		this.buyer = buyer;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public int getNumberOfDailyTraining() {
		return numberOfDailyTraining;
	}

	public void setNumberOfDailyTraining(int numberOfDailyTraining) {
		this.numberOfDailyTraining = numberOfDailyTraining;
	}
	
	
	
}
