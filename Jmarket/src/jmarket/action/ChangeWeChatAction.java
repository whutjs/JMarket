package jmarket.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jmarket.dao.TUserDAO;
import jmarket.pojo.TUser;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

public class ChangeWeChatAction extends ActionSupport implements ServletRequestAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8242175656647375920L;

	public String getNewWeChat() {
		return newWeChat;
	}
	public void setNewWeChat(String newWeChat) {
		this.newWeChat = newWeChat;
	}
	private String newWeChat;
	private HttpSession session;

	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		session=request.getSession();
	}
	@Override
	public String execute() {
		// TODO Auto-generated method stub
		try{
		TUser user=(TUser)session.getAttribute("user");
		user.setUWechat(newWeChat);
		TUserDAO tud=new TUserDAO();
		tud.merge(user);
		session.removeAttribute("user");
		session.setAttribute("user", user);
		return SUCCESS;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ERROR;
		}
				
	}
}
