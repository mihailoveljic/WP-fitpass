package beans.models;

import java.io.Serializable;
import java.util.ArrayList;

public class Buyer extends User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5479223749777028107L;
	
	private long membershipId;
	private ArrayList<Long> visitedSportsFacilitiesIds;
	private double numberOfCollectedPoints;
	private long buyerTypeId;
	
	public Buyer() {}

	public Buyer(long membershipId, ArrayList<Long> visitedSportsFacilitiesIds, double numberOfCollectedPoints,
			long buyerTypeId) {
		super();
		this.membershipId = membershipId;
		this.visitedSportsFacilitiesIds = visitedSportsFacilitiesIds;
		this.numberOfCollectedPoints = numberOfCollectedPoints;
		this.buyerTypeId = buyerTypeId;
	}

	public long getMembershipId() {
		return membershipId;
	}

	public void setMembershipId(long membershipId) {
		this.membershipId = membershipId;
	}

	public ArrayList<Long> getVisitedSportsFacilitiesIds() {
		return visitedSportsFacilitiesIds;
	}

	public void setVisitedSportsFacilitiesIds(ArrayList<Long> visitedSportsFacilitiesIds) {
		this.visitedSportsFacilitiesIds = visitedSportsFacilitiesIds;
	}

	public double getNumberOfCollectedPoints() {
		return numberOfCollectedPoints;
	}

	public void setNumberOfCollectedPoints(double numberOfCollectedPoints) {
		this.numberOfCollectedPoints = numberOfCollectedPoints;
	}

	public long getBuyerTypeId() {
		return buyerTypeId;
	}

	public void setBuyerTypeId(long buyerTypeId) {
		this.buyerTypeId = buyerTypeId;
	}

}
