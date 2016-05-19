package jmarket.pojo;

import java.util.List;

public class ItemResult{
	private String item_id;
	private String avatar_path;
	private String username;
	private String postDate;
	private String price;
	private String location;
	private String title;
	private String description;
	private List<String> postImgPath;
	private float quality;
	private String wechat;
	
	public float getQuality() {
		return quality;
	}
	public void setQuality(float quality) {
		this.quality = quality;
	}
	public String getWechat() {
		return wechat;
	}
	public void setWechat(String wechat) {
		this.wechat = wechat;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	private String phone;
	private String qq;
	
	public String getItem_id() {
		return item_id;
	}
	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}
	public String getAvatar_path() {
		return avatar_path;
	}
	public void setAvatar_path(String avatar_path) {
		this.avatar_path = avatar_path;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPostDate() {
		return postDate;
	}
	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<String> getPostImgPath() {
		return postImgPath;
	}
	public void setPostImgPath(List<String> postImgPath) {
		this.postImgPath = postImgPath;
	}
	
}
