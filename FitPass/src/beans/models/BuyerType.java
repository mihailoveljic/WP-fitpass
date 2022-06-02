package beans.models;

public class BuyerType {
	private double id;
	private String typeName;
	private double discount;
	private double requiredPointsForUpgrade;
	
	public BuyerType() {}

	public BuyerType(double id, String typeName, double discount, double collectedPoints) {
		super();
		this.id = id;
		this.typeName = typeName;
		this.discount = discount;
		this.requiredPointsForUpgrade = collectedPoints;
	}
	
	public double getId() {
		return id;
	}

	public void setId(double id) {
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
		
}
