package jmarket.pojo;

/**
 * TItemphoto entity. @author MyEclipse Persistence Tools
 */

public class TItemphoto implements java.io.Serializable {

	// Fields

	private String PId;
	private TItem TItem;
	private String PPath;

	// Constructors

	/** default constructor */
	public TItemphoto() {
	}

	/** minimal constructor */
	public TItemphoto(String PId, TItem TItem) {
		this.PId = PId;
		this.TItem = TItem;
	}

	/** full constructor */
	public TItemphoto(String PId, TItem TItem, String PPath) {
		this.PId = PId;
		this.TItem = TItem;
		this.PPath = PPath;
	}

	// Property accessors

	public String getPId() {
		return this.PId;
	}

	public void setPId(String PId) {
		this.PId = PId;
	}

	public TItem getTItem() {
		return this.TItem;
	}

	public void setTItem(TItem TItem) {
		this.TItem = TItem;
	}

	public String getPPath() {
		return this.PPath;
	}

	public void setPPath(String PPath) {
		this.PPath = PPath;
	}

}