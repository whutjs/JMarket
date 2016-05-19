package jmarket.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jmarket.dao.TItemDAO;
import jmarket.pojo.TItem;
import jmarket.util.LocalItemCache;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;


public class RepostItemAction extends  ActionSupport implements ServletRequestAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4520212398405521222L;
	private String itemid;
	private HttpSession session;
	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		session=request.getSession();
	}
	
	public String getItemid() {
		return itemid;
	}

	public void setItemid(String itemid) {
		this.itemid = itemid;
	}
	@Override
	public String execute()  {
		// TODO Auto-generated method stub
		try {
			TItemDAO tid=new TItemDAO();
//			TItem item=tid.findById(itemid);
			TItem item = LocalItemCache.getLocalItemCache().getItem(itemid);
			session.removeAttribute("item4repost");
			session.setAttribute("item4repost", item);
			return SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ERROR;
		}
	}



}
