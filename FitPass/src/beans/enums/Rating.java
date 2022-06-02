package beans.enums;

public enum Rating {
	E(1), D(2), C(3), B(4), A(5);
	
	private int numVal;
	
	Rating(int numVal){
		this.numVal = numVal;
	}
	
	public int getNumVal() {
		return numVal;
	}
}
