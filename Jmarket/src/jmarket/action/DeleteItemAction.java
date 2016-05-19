package jmarket.action;


import jmarket.dao.TItemDAO;
import jmarket.pojo.TItem;
import jmarket.util.LocalItemCache;


import com.opensymphony.xwork2.ActionSupport;

public class DeleteItemAction extends ActionSupport {

	private static final long serialVersionUID = 6898177251570113900L;
	private String itemid;

	@Override
	public String execute()  {
		// TODO Auto-generated method stub
		try {

		TItemDAO tid=new TItemDAO();
//		TItem item=tid.findById(itemid);
		TItem item = LocalItemCache.getLocalItemCache().getItem(itemid);
			tid.delete(item);
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
