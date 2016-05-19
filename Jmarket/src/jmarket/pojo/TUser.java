package jmarket.pojo;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * TUser entity. @author MyEclipse Persistence Tools
 */

public class TUser implements java.io.Serializable {

	// Fields

	private String UId;
	private String UName;
	private String UPassword;
	private String USex;
	private String UEmail;
	private String UType;
	private String UQq;
	private String UWechat;
	private String UImage;
	private String UFlag;
	private Timestamp URegtime;
	private String UPhone;
	private Set TItems = new HashSet(0);
	private Set TMessages = new HashSet(0);
	private Set TCollections = new HashSet(0);

	// Constructors

	/** default constructor */
	public TUser() {
	}

	/** minimal constructor */
	public TUser(String UId, String UName, String UPassword, String UEmail,
			String UFlag) {
		this.UId = UId;
		this.UName = UName;
		this.UPassword = UPassword;
		this.UEmail = UEmail;
		this.UFlag = UFlag;
	}

	/** full constructor */
	public TUser(String UId, String UName, String UPassword, String USex,
			String UEmail, String UType, String UQq, String UWechat,
			String UImage, String UFlag, Timestamp URegtime, String UPhone,
			Set TItems, Set TMessages, Set TCollections) {
		this.UId = UId;
		this.UName = UName;
		this.UPassword = UPassword;
		this.USex = USex;
		this.UEmail = UEmail;
		this.UType = UType;
		this.UQq = UQq;
		this.UWechat = UWechat;
		this.UImage = UImage;
		this.UFlag = UFlag;
		this.URegtime = URegtime;
		this.UPhone = UPhone;
		this.TItems = TItems;
		this.TMessages = TMessages;
		this.TCollections = TCollections;
	}

	// Property accessors

	public String getUId() {
		return this.UId;
	}

	public void setUId(String UId) {
		this.UId = UId;
	}

	public String getUName() {
		return this.UName;
	}

	public void setUName(String UName) {
		this.UName = UName;
	}

	public String getUPassword() {
		return this.UPassword;
	}

	public void setUPassword(String UPassword) {
		this.UPassword = UPassword;
	}

	public String getUSex() {
		return this.USex;
	}

	public void setUSex(String USex) {
		this.USex = USex;
	}

	public String getUEmail() {
		return this.UEmail;
	}

	public void setUEmail(String UEmail) {
		this.UEmail = UEmail;
	}

	public String getUType() {
		return this.UType;
	}

	public void setUType(String UType) {
		this.UType = UType;
	}

	public String getUQq() {
		return this.UQq;
	}

	public void setUQq(String UQq) {
		this.UQq = UQq;
	}

	public String getUWechat() {
		return this.UWechat;
	}

	public void setUWechat(String UWechat) {
		this.UWechat = UWechat;
	}

	public String getUImage() {
		return this.UImage;
	}

	public void setUImage(String UImage) {
		this.UImage = UImage;
	}

	public String getUFlag() {
		return this.UFlag;
	}

	public void setUFlag(String UFlag) {
		this.UFlag = UFlag;
	}

	public Timestamp getURegtime() {
		return this.URegtime;
	}

	public void setURegtime(Timestamp URegtime) {
		this.URegtime = URegtime;
	}

	public String getUPhone() {
		return this.UPhone;
	}

	public void setUPhone(String UPhone) {
		this.UPhone = UPhone;
	}

	public Set getTItems() {
		return this.TItems;
	}

	public void setTItems(Set TItems) {
		this.TItems = TItems;
	}

	public Set getTMessages() {
		return this.TMessages;
	}

	public void setTMessages(Set TMessages) {
		this.TMessages = TMessages;
	}

	public Set getTCollections() {
		return this.TCollections;
	}

	public void setTCollections(Set TCollections) {
		this.TCollections = TCollections;
	}

}