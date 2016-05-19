package jmarket.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jmarket.dao.TItemDAO;
import jmarket.dao.TMessageDAO;
import jmarket.pojo.TItem;
import jmarket.pojo.TMessage;
import jmarket.pojo.TUser;
import jmarket.util.LocalMessageCache;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

public class ShowMessageAction extends ActionSupport implements ServletRequestAware{
	private HttpSession session;
	private String type;

	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		session=request.getSession();
	}
	@Override
	public String execute()  {
		// TODO Auto-generated method stub
		try {

		TUser user=(TUser) session.getAttribute("user");
		if(user==null){
			return "nologin";
		}
//		TMessage message=new TMessage();
//		message.setTUser(user);
//		TMessageDAO tmd=new TMessageDAO();
		List messages = LocalMessageCache.getLocalMsgCache().getMsgByUser(user);
		session.removeAttribute("usermessage");
		session.setAttribute("usermessage", messages);
		TItem item=new TItem();
		item.setTUser(user);
		TItemDAO tid=new TItemDAO();
		List items=tid.findByProperty("TUser", user);
		TItem temp=new TItem();
		List message4user=new ArrayList();
		TMessage messagetemp=new TMessage();
		for(int i=0;i<items.size();i++){
			temp=(TItem) items.get(i);
//			message4user.addAll(tmd.findByProperty("TItem",temp));
			message4user.addAll(LocalMessageCache.getLocalMsgCache().getMsgByItem(temp));
		}
		session.removeAttribute("message4user");
		session.setAttribute("message4user", message4user);
		List reply4user=new ArrayList();
		if(messages!=null){
		for(int i=0;i<messages.size();i++){
			messagetemp=(TMessage)messages.get(i);
//			reply4user.addAll(tmd.findByProperty("TMessage", messagetemp));
			reply4user.addAll(LocalMessageCache.getLocalMsgCache().getMsgByParentId(messagetemp));
		}
		}
		session.removeAttribute("reply4user");
		session.setAttribute("reply4user", reply4user);
		int size=0;
		int totalsize=0;
		if(reply4user!=null){
			size=reply4user.size();
			totalsize=totalsize+size;
		}
		if(message4user!=null){
			totalsize=message4user.size()+totalsize;
		}
		if(messages!=null){
			totalsize=messages.size()+totalsize;
		}
		session.removeAttribute("reply4usersize");
		session.setAttribute("reply4usersize",size);
		session.removeAttribute("totalsize");
		session.setAttribute("totalsize", totalsize);
		if(type.equals("1")){
		return SUCCESS;
		}else if(type.equals("2")){
			return "comment";
		}else{
			return "nologin";
		}
		
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		return ERROR;
	}
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
