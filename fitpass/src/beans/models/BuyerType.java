package beans.models;

import java.io.Serializable;

public class BuyerType implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6436050146111215737L;
	private long id;
	private String typeName;
	private double discount;
	private double requiredPointsForUpgrade;
	private boolean isDeleted;
	
	public BuyerType() {}

	public BuyerType(long id, String typeName, double discount, double collectedPoints, boolean isDeleted) {
		super();
		this.id = id;
		this.typeName = typeName;
		this.discount = discount;
		this.requiredPointsForUpgrade = collectedPoints;
		this.isDeleted = isDeleted;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getRequiredPointsForUpgrade() {
		return requiredPointsForUpgrade;
	}

	public void setRequiredPointsForUpgrade(double requiredPointsForUpgrade) {
		this.requiredPointsForUpgrade = requiredPointsForUpgrade;
	}
	public boolean getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
		
}
