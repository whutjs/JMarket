package jmarket.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

public class LogoutAction extends ActionSupport implements ServletRequestAware{
	
	private HttpSession session;

	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		session=request.getSession();
	}
	public String execute()  {
		// TODO Auto-generated method stub
		try {
			session.removeAttribute("user");
			session.removeAttribute("login");
			session.setAttribute("login", false);
			return SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ERROR;
		}
	}
}
