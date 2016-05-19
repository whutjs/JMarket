package jmarket.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import jmarket.dao.TItemDAO;
import jmarket.dao.TMessageDAO;
import jmarket.pojo.MessageResult;
import jmarket.pojo.TItem;
import jmarket.pojo.TMessage;
import jmarket.util.ConstantValue;
import jmarket.util.Util;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class MGetCommentAction  extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private Map<String,Object> jsonMap = new HashMap<String, Object>();
	public Map<String, Object> getJsonMap() {
		return jsonMap;
	}
	public void setJsonMap(Map<String, Object> jsonMap) {
		this.jsonMap = jsonMap;
	}
	
	private String itemId = "";
	
	
	 @Override
	public String execute() throws Exception {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/json");
			response.setHeader("Access-Control-Allow-Origin", "*"); //允许哪些url可以跨域请求到本域
			response.setHeader("Access-Control-Allow-Methods","POST"); //允许的请求方法，一般是GET,POST,PUT,DELETE,OPTIONS
			response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type"); //允许哪些请求头可以跨域
			
			itemId = ServletActionContext.getRequest().getParameter(ConstantValue.ITEM_ID_KEY);
			if(Util.checkStrEmpty(itemId)) {
				 jsonMap.clear();
				 jsonMap.put(ConstantValue.FLAG_KEY, ConstantValue.FLAG_FAIL_VALUE);
				 jsonMap.put(ConstantValue.MSG_KEY, "item id is null");
				 return SUCCESS;
			}
			TMessageDAO msgDao = new TMessageDAO();
			TItemDAO itemDao = new TItemDAO();
			
			TItem item = itemDao.findById(itemId);
			List<Object> result = msgDao.findByTItem(item);
			List<MessageResult> msgResult = new ArrayList<MessageResult>(50);
			for(Object obj : result) {
				TMessage msg = (TMessage)obj;
				MessageResult comment = new MessageResult();
				comment.setContent(msg.getMContent());
				comment.setDate(msg.getMDate().toString());
				comment.setItem_id(itemId);
				comment.setMsg_id(msg.getMId());
				comment.setUser_name(msg.getTUser().getUName());
				comment.setAvatar(msg.getTUser().getUImage());
				TMessage parentMsg = msg.getTMessage();
				if(parentMsg != null) {
					comment.setParent_msg_id(parentMsg.getMId());
				}
				msgResult.add(comment);
			}
			 jsonMap.clear();
			 jsonMap.put(ConstantValue.FLAG_KEY, ConstantValue.FLAG_SUCC_VALUE);
			 jsonMap.put(ConstantValue.MSG_INFO_KEY, msgResult);
			 
			 msgDao.closeSession();
			 itemDao.closeSession();
			 return  SUCCESS;
			
	 }
	
}
