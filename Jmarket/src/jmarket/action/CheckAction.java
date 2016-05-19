package jmarket.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jmarket.dao.TUserDAO;
import jmarket.pojo.TUser;


import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

public class CheckAction extends ActionSupport implements ServletRequestAware{


	private static final long serialVersionUID = 112221L;
	private String code;
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	private HttpSession session;

	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.session=request.getSession();
	}

	public String execute(){
		String usercode=(String)session.getAttribute("Code");
		session.removeAttribute("Code");

		if(usercode.equals(code)){
			try{
				TUserDAO tud=new TUserDAO();
				TUser user=(TUser)session.getAttribute("user");
				tud.save(user);
				}catch (Exception e) {
					// TODO: handle exception
					this.addActionError("系统内部错误，请重试");
					return ERROR;
				}
			session.removeAttribute("login");
			session.setAttribute("login", true);	
			return SUCCESS;
		}else{
			this.addActionError("邮箱验证码错误");
			return ERROR;
		}
	}



}
