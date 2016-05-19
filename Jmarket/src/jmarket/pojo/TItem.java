package jmarket.pojo;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * TItem entity. @author MyEclipse Persistence Tools
 */

public class TItem implements java.io.Serializable {

	// Fields

	private String IId;
	private TUser TUser;
	// title
	private String IName;
	private String IDescription;
	private Timestamp IDate;
	// 物品的状态0表示未售出，1表示已经售出
	private String IFlag;
	private String IType;
	private Float IPrice;
	private String IPlace;
	// 新旧程度
	private Float IState;
	private Set TItemphotos = new HashSet(0);
	private Set TMessages = new HashSet(0);
	private Set TCollections = new HashSet(0);

	// Constructors

	/** default constructor */
	public TItem() {
	}

	/** minimal constructor */
	public TItem(String IId, TUser TUser, String IName, String IDescription,
			Timestamp IDate, String IFlag, String IType, Float IPrice,
			Float IState) {
		this.IId = IId;
		this.TUser = TUser;
		this.IName = IName;
		this.IDescription = IDescription;
		this.IDate = IDate;
		this.IFlag = IFlag;
		this.IType = IType;
		this.IPrice = IPrice;
		this.IState = IState;
	}

	/** full constructor */
	public TItem(String IId, TUser TUser, String IName, String IDescription,
			Timestamp IDate, String IFlag, String IType, Float IPrice,
			String IPlace, Float IState, Set TItemphotos, Set TMessages,
			Set TCollections) {
		this.IId = IId;
		this.TUser = TUser;
		this.IName = IName;
		this.IDescription = IDescription;
		this.IDate = IDate;
		this.IFlag = IFlag;
		this.IType = IType;
		this.IPrice = IPrice;
		this.IPlace = IPlace;
		this.IState = IState;
		this.TItemphotos = TItemphotos;
		this.TMessages = TMessages;
		this.TCollections = TCollections;
	}

	// Property accessors

	public String getIId() {
		return this.IId;
	}

	public void setIId(String IId) {
		this.IId = IId;
	}

	public TUser getTUser() {
		return this.TUser;
	}

	public void setTUser(TUser TUser) {
		this.TUser = TUser;
	}

	public String getIName() {
		return this.IName;
	}

	public void setIName(String IName) {
		this.IName = IName;
	}

	public String getIDescription() {
		return this.IDescription;
	}

	public void setIDescription(String IDescription) {
		this.IDescription = IDescription;
	}

	public Timestamp getIDate() {
		return this.IDate;
	}

	public void setIDate(Timestamp IDate) {
		this.IDate = IDate;
	}

	public String getIFlag() {
		return this.IFlag;
	}

	public void setIFlag(String IFlag) {
		this.IFlag = IFlag;
	}

	public String getIType() {
		return this.IType;
	}

	public void setIType(String IType) {
		this.IType = IType;
	}

	public Float getIPrice() {
		return this.IPrice;
	}

	public void setIPrice(Float IPrice) {
		this.IPrice = IPrice;
	}

	public String getIPlace() {
		return this.IPlace;
	}

	public void setIPlace(String IPlace) {
		this.IPlace = IPlace;
	}

	public Float getIState() {
		return this.IState;
	}

	public void setIState(Float IState) {
		this.IState = IState;
	}

	public Set getTItemphotos() {
		return this.TItemphotos;
	}

	public void setTItemphotos(Set TItemphotos) {
		this.TItemphotos = TItemphotos;
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