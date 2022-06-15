package beans.models;

import java.io.Serializable;
import java.time.LocalDate;

public class Membership implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8271414579636648057L;
	private long id;
	private double membershipNumber;
	private long membershipTypeId;
	private LocalDate paymentDate;
	private LocalDate expirationDate;
	private double price;
	private long buyerId;
	private boolean active;
	private int numberOfDailyTraining;
	
	public Membership() {}

	public Membership(long id, double membershipNumber, long membershipTypeId, LocalDate paymentDate,
			LocalDate expirationDate, double price, long buyerId, boolean active, int numberOfDailyTraining) {
		super();
		this.id = id;
		this.membershipNumber = membershipNumber;
		this.membershipTypeId = membershipTypeId;
		this.paymentDate = paymentDate;
		this.expirationDate = expirationDate;
		this.price = price;
		this.buyerId = buyerId;
		this.active = active;
		this.numberOfDailyTraining = numberOfDailyTraining;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getMembershipNumber() {
		return membershipNumber;
	}

	public void setMembershipNumber(double membershipNumber) {
		this.membershipNumber = membershipNumber;
	}

	public long getMembershipTypeId() {
		return membershipTypeId;
	}

	public void setMembershipTypeId(long membershipTypeId) {
		this.membershipTypeId = membershipTypeId;
	}

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}

	public LocalDate getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(LocalDate expirationDate) {
		this.expirationDate = expirationDate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public long getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(long buyerId) {
		this.buyerId = buyerId;
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
