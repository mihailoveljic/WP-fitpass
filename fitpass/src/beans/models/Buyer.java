package beans.models;

import java.io.Serializable;
import java.util.ArrayList;

public class Buyer extends User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5479223749777028107L;
	
	private Membership membership;
	private ArrayList<SportsFacility> visitedSportsFacilities;
	private int numberOfCollectedPoints;
	private BuyerType buyerType;
	
	public Buyer() {}

	public Buyer(Membership membership, ArrayList<SportsFacility> visitedSportsFacilities, int numberOfCollectedPoints,
			BuyerType buyerType) {
		super();
		this.membership = membership;
		this.visitedSportsFacilities = visitedSportsFacilities;
		this.numberOfCollectedPoints = numberOfCollectedPoints;
		this.buyerType = buyerType;
	}

	public Membership getMembership() {
		return membership;
	}

	public void setMembership(Membership membership) {
		this.membership = membership;
	}

	public ArrayList<SportsFacility> getVisitedSportsFacilities() {
		return visitedSportsFacilities;
	}

	public void setVisitedSportsFacilities(ArrayList<SportsFacility> visitedSportsFacilities) {
		this.visitedSportsFacilities = visitedSportsFacilities;
	}

	public int getNumberOfCollectedPoints() {
		return numberOfCollectedPoints;
	}

	public void setNumberOfCollectedPoints(int numberOfCollectedPoints) {
		this.numberOfCollectedPoints = numberOfCollectedPoints;
	}

	public BuyerType getBuyerType() {
		return buyerType;
	}

	public void setBuyerType(BuyerType buyerType) {
		this.buyerType = buyerType;
	}
}
