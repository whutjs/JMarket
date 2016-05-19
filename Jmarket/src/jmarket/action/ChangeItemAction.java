package jmarket.action;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jmarket.dao.TItemDAO;
import jmarket.pojo.TItem;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;


public class ChangeItemAction extends ActionSupport implements ServletRequestAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3836752700908191781L;
	private String state;
	private String campus;
	private String area;
	private String building;
	private String price;
	private String name;
	private String description;
	private HttpSession session;
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCampus() {
		return campus;
	}
	public void setCampus(String campus) {
		this.campus = campus;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getBuilding() {
		return building;
	}
	public void setBuilding(String building) {
		this.building = building;
	}
	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		session=request.getSession();
	}
	@Override
	public String execute()  {
		// TODO Auto-generated method stub
		try {
		TItem item=(TItem) session.getAttribute("item4repost");
		item.setIPrice(Float.parseFloat(price));
		item.setIName(name);
		item.setIDescription(description);
		UploadItemAction ulia=new UploadItemAction();
		item.setIPlace(ulia.parseplace(campus, area, building));
		if(state!=null&&(!state.equals("0"))){
		item.setIState((float) (11-Integer.parseInt(state)));
		}
		TItemDAO tidDao=new TItemDAO();
		tidDao.merge(item);
		return SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ERROR;
		}
	}
}
