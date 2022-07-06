package beans.models;

import java.util.Date;

public class PromoCode {

	private long id;
	private String mark;
	private Date expirationDate;
	private int howManyTimeCanBeUsed;
	private double discount;
	private boolean isDeleted;
	
	public PromoCode() {}

	public PromoCode(long id, String mark, Date expirationDate, int howManyTimeCanBeUsed, double discount, boolean isDeleted) {
		super();
		this.id = id;
		this.mark = mark;
		this.expirationDate = expirationDate;
		this.howManyTimeCanBeUsed = howManyTimeCanBeUsed;
		this.discount = discount;
		this.isDeleted = isDeleted;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public int getHowManyTimeCanBeUsed() {
		return howManyTimeCanBeUsed;
	}

	public void setHowManyTimeCanBeUsed(int howManyTimeCanBeUsed) {
		this.howManyTimeCanBeUsed = howManyTimeCanBeUsed;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}
	
	public boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
}
