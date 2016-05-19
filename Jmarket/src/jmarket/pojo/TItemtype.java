package jmarket.pojo;

/**
 * TItemtype entity. @author MyEclipse Persistence Tools
 */

public class TItemtype implements java.io.Serializable {

	// Fields

	private String TId;
	private String TMainclass;
	private String TSecondclass;

	// Constructors

	/** default constructor */
	public TItemtype() {
	}

	/** full constructor */
	public TItemtype(String TId, String TMainclass, String TSecondclass) {
		this.TId = TId;
		this.TMainclass = TMainclass;
		this.TSecondclass = TSecondclass;
	}

	// Property accessors

	public String getTId() {
		return this.TId;
	}

	public void setTId(String TId) {
		this.TId = TId;
	}

	public String getTMainclass() {
		return this.TMainclass;
	}

	public void setTMainclass(String TMainclass) {
		this.TMainclass = TMainclass;
	}

	public String getTSecondclass() {
		return this.TSecondclass;
	}

	public void setTSecondclass(String TSecondclass) {
		this.TSecondclass = TSecondclass;
	}

}