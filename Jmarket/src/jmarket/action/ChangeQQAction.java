package jmarket.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jmarket.dao.TUserDAO;
import jmarket.pojo.TUser;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

public class ChangeQQAction extends ActionSupport implements ServletRequestAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5092340153552664099L;
	private String newqq;
	public String getNewqq() {
		return newqq;
	}
	public void setNewqq(String newqq) {
		this.newqq = newqq;
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
		user.setUQq(newqq);
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
