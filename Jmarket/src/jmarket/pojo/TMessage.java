package jmarket.pojo;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * TMessage entity. @author MyEclipse Persistence Tools
 */

public class TMessage implements java.io.Serializable {

	// Fields

	private String MId;
	private TItem TItem;
	private TUser TUser;
	private TMessage TMessage;
	private String MContent;
	private Timestamp MDate;
	private Set TMessages = new HashSet(0);

	// Constructors

	/** default constructor */
	public TMessage() {
	}

	/** minimal constructor */
	public TMessage(String MId, TItem TItem, TUser TUser, String MContent,
			Timestamp MDate) {
		this.MId = MId;
		this.TItem = TItem;
		this.TUser = TUser;
		this.MContent = MContent;
		this.MDate = MDate;
	}

	/** full constructor */
	public TMessage(String MId, TItem TItem, TUser TUser, TMessage TMessage,
			String MContent, Timestamp MDate, Set TMessages) {
		this.MId = MId;
		this.TItem = TItem;
		this.TUser = TUser;
		this.TMessage = TMessage;
		this.MContent = MContent;
		this.MDate = MDate;
		this.TMessages = TMessages;
	}

	// Property accessors

	public String getMId() {
		return this.MId;
	}

	public void setMId(String MId) {
		this.MId = MId;
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

	public TMessage getTMessage() {
		return this.TMessage;
	}

	public void setTMessage(TMessage TMessage) {
		this.TMessage = TMessage;
	}

	public String getMContent() {
		return this.MContent;
	}

	public void setMContent(String MContent) {
		this.MContent = MContent;
	}

	public Timestamp getMDate() {
		return this.MDate;
	}

	public void setMDate(Timestamp MDate) {
		this.MDate = MDate;
	}

	public Set getTMessages() {
		return this.TMessages;
	}

	public void setTMessages(Set TMessages) {
		this.TMessages = TMessages;
	}

}