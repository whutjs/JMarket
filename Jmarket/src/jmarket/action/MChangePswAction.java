package jmarket.action;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jmarket.dao.TUserDAO;
import jmarket.pojo.TUser;
import jmarket.util.ConstantValue;
import jmarket.util.LocalUserCache;
import jmarket.util.Util;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 修改密码
 * @author Jenson
 *
 */
public class MChangePswAction  extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private Map<String,Object> jsonMap = new HashMap<String, Object>();
	
	public Map<String, Object> getJsonMap() {
		return jsonMap;
	}
	public void setJsonMap(Map<String, Object> jsonMap) {
		this.jsonMap = jsonMap;
	}
	
	public String execute() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		response.setHeader("Access-Control-Allow-Origin", "*"); //允许哪些url可以跨域请求到本域
		response.setHeader("Access-Control-Allow-Methods","POST"); //允许的请求方法，一般是GET,POST,PUT,DELETE,OPTIONS
		response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type"); //允许哪些请求头可以跨域
		
		HttpServletRequest request = ServletActionContext.getRequest();
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
			  jsonMap.put(ConstantValue.MSG_KEY, e.getMessage());
			  return SUCCESS;
		  }
		  String token = jsonObject.optString(ConstantValue.TOKEN_KEY, "");
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
		  String email = Util.mobileGlobalTokenToEmail.get(token);
		  TUserDAO tud = new TUserDAO();
//		  List userList = tud.findByUEmail(email);
		  TUser user = LocalUserCache.getLocalUserCache().getUserByEmail(email);
		  if(user == null) {
			  jsonMap.clear();
			  jsonMap.put(ConstantValue.FLAG_KEY, ConstantValue.FLAG_FAIL_VALUE);
			  jsonMap.put(ConstantValue.MSG_KEY, ConstantValue.MSG_USER_NOTEXISTS);
			  tud.closeSession();
			  return SUCCESS;
		  }
		  String oldPsw = jsonObject.optString(ConstantValue.PSW_KEY, "");
		  if(Util.checkStrEmpty(oldPsw)) {
			  jsonMap.clear();
			  jsonMap.put(ConstantValue.FLAG_KEY, ConstantValue.FLAG_FAIL_VALUE);
			  jsonMap.put(ConstantValue.MSG_KEY, "old psw is null");
			  tud.closeSession();
			  return SUCCESS;
		  }
		  String newPsw = jsonObject.optString(ConstantValue.NEW_PSW_KEY, "");
		  if(Util.checkStrEmpty(newPsw)) {
			  jsonMap.clear();
			  jsonMap.put(ConstantValue.FLAG_KEY, ConstantValue.FLAG_FAIL_VALUE);
			  jsonMap.put(ConstantValue.MSG_KEY, "new psw is null");
			  tud.closeSession();
			  return SUCCESS;
		  }
//		  TUser user = (TUser) userList.get(0);
		  String psw = user.getUPassword();
		  if(!oldPsw.equals(psw)) {
			  jsonMap.clear();
			  jsonMap.put(ConstantValue.FLAG_KEY, ConstantValue.FLAG_FAIL_VALUE);
			  jsonMap.put(ConstantValue.MSG_KEY, "password is wrong");
			  tud.closeSession();
			  return SUCCESS;
		  }
		  user.setUPassword(newPsw);
		  tud.save(user);
		  jsonMap.clear();
		  jsonMap.put(ConstantValue.FLAG_KEY, ConstantValue.FLAG_SUCC_VALUE);
		  tud.closeSession();
		  return SUCCESS;
	}
}
