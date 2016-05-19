package jmarket.pojo;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * 评论消息
 * @author Jenson
 *
 */
public class MessageResult {
	private String msg_id;
	public String getMsg_id() {
		return msg_id;
	}
	public void setMsg_id(String msg_id) {
		this.msg_id = msg_id;
	}
	public String getItem_id() {
		return item_id;
	}
	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getParent_msg_id() {
		return parent_msg_id;
	}
	public void setParent_msg_id(String parent_msg_id) {
		this.parent_msg_id = parent_msg_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	private String item_id;
	// 发布评论的用户名
	private String user_name;
	private String parent_msg_id;
	private String content;
	private String date;
	private String avatar;
	
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
}
