package jmarket.action;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jmarket.dao.TItemDAO;
import jmarket.pojo.TItem;
import jmarket.pojo.TUser;
import jmarket.util.LocalItemCache;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

public class ShowUserItemAction extends ActionSupport implements ServletRequestAware {
	private HttpSession session;
	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		session=request.getSession();
	}
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		try {			
		TUser user=(TUser) session.getAttribute("user");
		if(user==null){
			return "nologin";
		}
//		TItemDAO tid=new TItemDAO();
//		List items=tid.findByProperty("TUser", user);
		List items = LocalItemCache.getLocalItemCache().getItemByUser(user);
		SearchAction sa=new SearchAction();
		HashMap<String, String> photoitem=sa.matchPicture(items);
		session.removeAttribute("item4user");
		session.setAttribute("items4user", items);
		session.removeAttribute("photo4useritem");
		session.setAttribute("photo4useritem", photoitem);
		return SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ERROR;
		}
	}
	

}
