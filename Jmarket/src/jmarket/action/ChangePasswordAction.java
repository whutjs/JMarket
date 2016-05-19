package jmarket.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jmarket.dao.TUserDAO;
import jmarket.pojo.TUser;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

public class ChangePasswordAction extends ActionSupport implements ServletRequestAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7087860937279551173L;
	private String oldPassword;
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	private String newPassword;
	private HttpSession session;

	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		session=request.getSession();		
	}
	@Override
	public String execute()  {
		// TODO Auto-generated method stub
		try{
		TUser user=(TUser)session.getAttribute("user");
		TUserDAO tud=new TUserDAO();
		if(user.getUPassword().equals(oldPassword)){
			user.setUPassword(newPassword);
			tud.merge(user);
			session.removeAttribute("user");
			session.setAttribute("user", user);
			return SUCCESS;
		}else{
			return ERROR;
		}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ERROR;
		}
	}
}
