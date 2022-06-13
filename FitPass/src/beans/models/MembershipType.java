package beans.models;

import java.io.Serializable;

public class MembershipType implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7891954538698337794L;
	private long id;
	private String name;
	
	public MembershipType() {}

	public MembershipType(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
