package jmarket.pojo;

/**
 * TCollection entity. @author MyEclipse Persistence Tools
 */

public class TCollection implements java.io.Serializable {

	// Fields

	private String CId;
	private TItem TItem;
	private TUser TUser;

	// Constructors

	/** default constructor */
	public TCollection() {
	}

	/** minimal constructor */
	public TCollection(String CId) {
		this.CId = CId;
	}

	/** full constructor */
	public TCollection(String CId, TItem TItem, TUser TUser) {
		this.CId = CId;
		this.TItem = TItem;
		this.TUser = TUser;
	}

	// Property accessors

	public String getCId() {
		return this.CId;
	}

	public void setCId(String CId) {
		this.CId = CId;
	}

	public TItem getTItem() {
		return this.TItem;
	}

	public void setTItem(TItem TItem) {
		this.TItem = TItem;
	}

	public TUser getTUser() {
		return this.TUser;
	}

	public void setTUser(TUser TUser) {
		this.TUser = TUser;
	}

}