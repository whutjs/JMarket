package jmarket.action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jmarket.dao.TUserDAO;
import jmarket.pojo.TUser;
import jmarket.util.LocalUserCache;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;



import java.util.*;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;


public class LoginAction extends ActionSupport implements ModelDriven<TUser>,ServletRequestAware {
	private static final long serialVersionUID = 6546461L;
	private TUser  user=new TUser();
	private String remember;
	private HttpSession session;
	private HttpServletResponse response=ServletActionContext.getResponse();

	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.session=request.getSession();
	}
	public TUser getModel() {
		// TODO Auto-generated method stub
		return user;
	}
	@Override
	public String execute(){
		// TODO Auto-generated method stub
		TUserDAO tud=new TUserDAO();
		@SuppressWarnings("rawtypes")
		List userList=new ArrayList();
//		if(user.getUName()!=null){
//			userList=tud.findByUName(user.getUName());
//		}else if(user.getUEmail()!=null)
//		{
//		userList=tud.findByUEmail(user.getUEmail());
//		}
		TUser tmpUser = LocalUserCache.getLocalUserCache().getUserByEmail(user.getUEmail());
		if(tmpUser != null) {
			userList.add(tmpUser);
		}
		if(userList.size()<1){
			this.addActionError("该邮箱不存在");
			return ERROR;
		}else if(userList.size()>1){
			return ERROR;
		}else {
			TUser userResult=(TUser) userList.get(0);
			if(user.getUPassword().equals(userResult.getUPassword())){
				System.out.println(userResult.getUId());
				session.removeAttribute("user");
				session.setAttribute("user", userResult);
				session.removeAttribute("login");
				session.setAttribute("login", true);		
				remember=this.getRemember();
				if(remember!=null){
				if(remember.equals("ok")){
					Cookie cookieMail=new Cookie("userEmail",userResult.getUEmail());
					cookieMail.setMaxAge(604800);
					Cookie cookiePassword=new Cookie("userPasswoed", userResult.getUPassword());
					cookiePassword.setMaxAge(604800);
					response.addCookie(cookieMail);
					response.addCookie(cookiePassword);
				}
				}	
				return SUCCESS;
			}else{
				this.addActionError("用户邮箱或密码错误");
				return ERROR;
			}
		}
		
	}
	public String getRemember() {
		return remember;
	}
	public void setRemember(String remember) {
		this.remember = remember;
	}


}
