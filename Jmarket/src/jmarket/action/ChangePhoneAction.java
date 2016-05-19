package jmarket.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jmarket.dao.TUserDAO;
import jmarket.pojo.TUser;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

public class ChangePhoneAction extends ActionSupport implements ServletRequestAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1516480886803257585L;
	private String newphone;
	public String getNewphone() {
		return newphone;
	}
	public void setNewphone(String newphone) {
		this.newphone = newphone;
	}
	private HttpSession session;
	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		session=request.getSession();
	}
	@Override
	public String execute() {
		// TODO Auto-generated method stub
		try {

		TUser user=(TUser) session.getAttribute("user");
		user.setUPhone(newphone);
		TUserDAO tud=new TUserDAO();
		tud.merge(user);
		session.removeAttribute("user");
		session.setAttribute("user", user);
		return SUCCESS;
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		return ERROR;
	}
	}
	
}
