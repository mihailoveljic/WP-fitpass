package beans.models;

import java.io.Serializable;
import java.util.Date;

public class Membership implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8271414579636648057L;
	private long id;
	private long membershipNumber;
	private long membershipTypeId;
	private Date paymentDate;
	private Date expirationDate;
	private double price;
	private long buyerId;
	private boolean isActive;
	private int numberOfRemainingTrainings;
	private boolean isUnlimited;
	private boolean isDeleted;
	
	public Membership() {}

	public Membership(long id, long membershipNumber, long membershipTypeId, Date paymentDate, Date expirationDate,
			double price, long buyerId, boolean isActive, int numberOfRemainingTrainings, boolean isUnlimited,
			boolean isDeleted) {
		super();
		this.id = id;
		this.membershipNumber = membershipNumber;
		this.membershipTypeId = membershipTypeId;
		this.paymentDate = paymentDate;
		this.expirationDate = expirationDate;
		this.price = price;
		this.buyerId = buyerId;
		this.isActive = isActive;
		this.numberOfRemainingTrainings = numberOfRemainingTrainings;
		this.isUnlimited = isUnlimited;
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

	public void setMembershipNumber(long membershipNumber) {
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

	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public int getNumberOfRemainingTrainings() {
		return numberOfRemainingTrainings;
	}

	public void setNumberOfRemainingTrainings(int numberOfRemainingTrainings) {
		this.numberOfRemainingTrainings = numberOfRemainingTrainings;
	}

	public boolean getIsUnlimited() {
		return isUnlimited;
	}

	public void setIsUnlimited(boolean isUnlimited) {
		this.isUnlimited = isUnlimited;
	}

	public boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	
}
