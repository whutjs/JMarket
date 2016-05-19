package jmarket.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jmarket.dao.TItemDAO;
import jmarket.pojo.TItem;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

public class GetItemAction extends ActionSupport implements ServletRequestAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4903719098769345157L;
	private String itemid;
	private HttpSession session;
	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		session=request.getSession();
	}
	@Override
	public String execute()  {
		// TODO Auto-generated method stub
		try {
			

		TItemDAO tidDao=new TItemDAO();
		TItem item=tidDao.findById(itemid);
		session.removeAttribute("item4change");
		session.setAttribute("item4change", item);
		return SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ERROR;
		}
	}
	public String getItemid() {
		return itemid;
	}
	public void setItemid(String itemid) {
		this.itemid = itemid;
	}

}
