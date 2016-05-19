package jmarket.action;

import java.io.BufferedReader;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jmarket.dao.TItemDAO;
import jmarket.dao.TMessageDAO;
import jmarket.dao.TUserDAO;
import jmarket.pojo.ItemResult;
import jmarket.pojo.MessageResult;
import jmarket.pojo.TItem;
import jmarket.pojo.TItemphoto;
import jmarket.pojo.TMessage;
import jmarket.pojo.TUser;
import jmarket.util.ConstantValue;
import jmarket.util.Util;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class MCommitMessageAction  extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private Map<String,Object> jsonMap = new HashMap<String, Object>();
	public Map<String, Object> getJsonMap() {
		return jsonMap;
	}
	public void setJsonMap(Map<String, Object> jsonMap) {
		this.jsonMap = jsonMap;
	}
	
	private String itemId = "";
	private String parentMsgId = "";			// 如果是评论Item，则为空，否则为回复的消息的id
	private String content = "";
	private String token = "";
	
	public String execute() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		response.setHeader("Access-Control-Allow-Origin", "*"); //允许哪些url可以跨域请求到本域
		response.setHeader("Access-Control-Allow-Methods","POST"); //允许的请求方法，一般是GET,POST,PUT,DELETE,OPTIONS
		response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type"); //允许哪些请求头可以跨域
		// 客户端用httpost请求
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
		StringBuffer jb = new StringBuffer();
	    String line = null;
		  try {
		    BufferedReader reader = request.getReader();
		    while ((line = reader.readLine()) != null)
		      jb.append(line);
		  } catch (Exception e) {
			  System.out.println(e.getMessage());
			  e.printStackTrace();
		  }
		  String postData = jb.toString();
		  if(Util.checkStrEmpty(postData)) {
			  	jsonMap.clear();
				jsonMap.put(ConstantValue.FLAG_KEY, ConstantValue.FLAG_FAIL_VALUE);
				jsonMap.put(ConstantValue.MSG_KEY, ConstantValue.MSG_BADREQUEST);
				return SUCCESS;
		  }
		  JSONObject jsonObject = null;
		  try{
			  jsonObject = JSONObject.fromObject(postData);
		  }catch (JSONException e) {
			  System.out.println(e.getMessage());
			  e.printStackTrace();
			  jsonMap.clear();
			  jsonMap.put(ConstantValue.FLAG_KEY, ConstantValue.FLAG_FAIL_VALUE);
			  jsonMap.put(ConstantValue.MSG_KEY, ConstantValue.MSG_BADREQUEST);
			  return SUCCESS;
		  }
		
		itemId = jsonObject.optString(ConstantValue.ITEM_ID_KEY, "");
		parentMsgId = jsonObject.optString(ConstantValue.PARENT_MSG_ID_KEY, "");
		content = jsonObject.optString(ConstantValue.CONTENT_KEY, "");
		token = jsonObject.optString(ConstantValue.TOKEN_KEY, "");
		
		if(Util.checkStrEmpty(itemId)) {
			 jsonMap.clear();
			 jsonMap.put(ConstantValue.FLAG_KEY, ConstantValue.FLAG_FAIL_VALUE);
			 jsonMap.put(ConstantValue.MSG_KEY, "item id is null");
			 return SUCCESS;
		}
		if(Util.checkStrEmpty(content)) {
			 jsonMap.clear();
			 jsonMap.put(ConstantValue.FLAG_KEY, ConstantValue.FLAG_FAIL_VALUE);
			 jsonMap.put(ConstantValue.MSG_KEY, "content is null");
			 return SUCCESS;
		}
		if(Util.checkStrEmpty(token)) {
			 jsonMap.clear();
			 jsonMap.put(ConstantValue.FLAG_KEY, ConstantValue.FLAG_FAIL_VALUE);
			 jsonMap.put(ConstantValue.MSG_KEY, "token is null");
			 return SUCCESS;
		}
		 if(Util.mobileGlobalTokenToEmail.containsKey(token) == false) {
			 // invalid access 
			 jsonMap.clear();
			 jsonMap.put(ConstantValue.FLAG_KEY, ConstantValue.FLAG_FAIL_VALUE);
			 jsonMap.put(ConstantValue.MSG_KEY, ConstantValue.MSG_INVALID_TOKEN);
			 return SUCCESS;
		 }
		 String userEmail = Util.mobileGlobalTokenToEmail.get(token);
		
		 TUserDAO userDao = new TUserDAO();
		TItemDAO itemDao = new TItemDAO();
		TMessageDAO msgDao = new TMessageDAO();
		
		TUser user = (TUser) userDao.findByUEmail(userEmail).get(0);
		TMessage parentMsg = null;
		if(!Util.checkStrEmpty(parentMsgId)) {
			parentMsg = msgDao.findById(parentMsgId);
		}
		TItem item = itemDao.findById(itemId);
		
		TMessage msg = new TMessage();
		msg.setMContent(content);
		
		Date date=new Date();
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		Timestamp ts = new Timestamp(calendar.getTimeInMillis());
		msg.setMDate(ts);
		msg.setMId(Util.getUUID());
		msg.setTUser(user);
		msg.setTItem(item);
		if(parentMsg != null) {
			msg.setTMessage(parentMsg);
		}
		msgDao.save(msg);
		
		MessageResult msgResult = new MessageResult();
		msgResult.setContent(msg.getMContent());
		msgResult.setDate(msg.getMDate().toString());
		msgResult.setItem_id(itemId);
		msgResult.setMsg_id(msg.getMId());
		msgResult.setParent_msg_id(parentMsgId);
		msgResult.setUser_name(user.getUName());
		 jsonMap.clear();
		 jsonMap.put(ConstantValue.FLAG_KEY, ConstantValue.FLAG_SUCC_VALUE);
		 jsonMap.put(ConstantValue.MSG_INFO_KEY, msgResult);
		 
		 userDao.closeSession();
		 itemDao.closeSession();
		 msgDao.closeSession();
		return SUCCESS;
	}
}
