package beans.models;

import java.io.Serializable;
import java.util.Date;

public class Membership implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8271414579636648057L;
	private long id;
	private double membershipNumber;
	private long membershipTypeId;
	private Date paymentDate;
	private Date expirationDate;
	private double price;
	private long buyerId;
	private boolean active;
	private int numberOfDailyTraining;
	private boolean isDeleted;
	
	public Membership() {}

	public Membership(long id, double membershipNumber, long membershipTypeId, Date paymentDate,
			Date expirationDate, double price, long buyerId, boolean active, int numberOfDailyTraining, boolean isDeleted) {
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
		this.isDeleted = isDeleted;
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
	public boolean getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
		
}
