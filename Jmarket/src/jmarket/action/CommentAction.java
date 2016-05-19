package jmarket.action;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jmarket.dao.TMessageDAO;
import jmarket.pojo.TItem;
import jmarket.pojo.TMessage;
import jmarket.pojo.TUser;
import jmarket.util.LocalItemCache;
import jmarket.util.LocalMessageCache;
import jmarket.util.Util;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class CommentAction extends ActionSupport implements
		ModelDriven<TMessage>, ServletRequestAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5445522183201979459L;
	private TMessage message = new TMessage();
	private HttpSession session;
	private String itemid;
	private String parentid;
	private String content;

	public String getItemid() {
		return itemid;
	}

	public void setItemid(String itemid) {
		this.itemid = itemid;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		session = request.getSession();
	}

	@Override
	public TMessage getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String execute() {
		// TODO Auto-generated method stub
		try {
			TUser user = (TUser) session.getAttribute("user");
			if (user == null) {
				return "nologin";
			}
			TMessageDAO tmd = new TMessageDAO();
//			TItem item = new TItem();
//			item.setIId(itemid);
			TItem item = LocalItemCache.getLocalItemCache().getItem(itemid);
			Date date = new Date();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			message.setMId(Util.getUUID());
			Timestamp ts = new Timestamp(calendar.getTimeInMillis());
			message.setMDate(ts);
			message.setTUser(user);
			message.setTItem(item);
			message.setMContent(content);
			if (!Util.checkStrEmpty(parentid)) {
//				TMessage temp = tmd.findById(parentid);
				TMessage temp = LocalMessageCache.getLocalMsgCache().getMsg(parentid);
				message.setTMessage(temp);
				System.out.println("content="+message.getMContent()+" m.parentId="+message.getTMessage().getMId());
			}
			tmd.save(message);
			return SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ERROR;
		}

	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
