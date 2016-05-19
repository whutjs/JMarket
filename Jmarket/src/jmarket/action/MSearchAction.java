package jmarket.action;

import java.io.BufferedReader;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jmarket.dao.TItemDAO;
import jmarket.dao.TUserDAO;
import jmarket.pojo.ItemResult;
import jmarket.pojo.TItem;
import jmarket.pojo.TItemphoto;
import jmarket.pojo.TUser;
import jmarket.util.ConstantValue;
import jmarket.util.Util;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class MSearchAction   extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	private Map<String,Object> jsonMap = new HashMap<String, Object>();
	
	public Map<String, Object> getJsonMap() {
		return jsonMap;
	}
	public void setJsonMap(Map<String, Object> jsonMap) {
		this.jsonMap = jsonMap;
	}
	
	private String keyword = "";
	private String page = "";
	private String pageSize = "";
	
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
		
		page = jsonObject.optString(ConstantValue.PAGE_KEY, "");
		pageSize = jsonObject.optString(ConstantValue.PAGE_SIZE_KEY, "");
		keyword = jsonObject.optString(ConstantValue.KEYWORD_KEY, "");
		if(Util.checkStrEmpty(keyword)) {
			 jsonMap.clear();
			 jsonMap.put(ConstantValue.FLAG_KEY, ConstantValue.FLAG_FAIL_VALUE);
			 jsonMap.put(ConstantValue.MSG_KEY, "keyword is null");
			 return SUCCESS;
		}
		if(Util.checkStrEmpty(page)) {
			 jsonMap.clear();
			 jsonMap.put(ConstantValue.FLAG_KEY, ConstantValue.FLAG_FAIL_VALUE);
			 jsonMap.put(ConstantValue.MSG_KEY, ConstantValue.MSG_MISS_PAGE);
			 return SUCCESS;
		}
		if(Util.checkStrEmpty(pageSize)) {
			 jsonMap.clear();
			 jsonMap.put(ConstantValue.FLAG_KEY, ConstantValue.FLAG_FAIL_VALUE);
			 jsonMap.put(ConstantValue.MSG_KEY, ConstantValue.MSG_MISS_PAGE_SIZE);
			 return SUCCESS;
		}
//		keyword = new String(tmpkeyword.getBytes("utf-8"), "ISO8859-1");
		System.out.println("page:"+page+" pageSize="+pageSize+" keyword="+keyword);
		int pageNo = 0;
		int pageSizeInt = 0;
		try{
			pageNo = Integer.parseInt(page);
		}catch (NumberFormatException e) {
			 jsonMap.clear();
			 jsonMap.put(ConstantValue.FLAG_KEY, ConstantValue.FLAG_FAIL_VALUE);
			 jsonMap.put(ConstantValue.MSG_KEY, e.getMessage());
			 return SUCCESS;
		}
		try{
			pageSizeInt = Integer.parseInt(pageSize);
		}catch (NumberFormatException e) {
			 jsonMap.clear();
			 jsonMap.put(ConstantValue.FLAG_KEY, ConstantValue.FLAG_FAIL_VALUE);
			 jsonMap.put(ConstantValue.MSG_KEY, e.getMessage());
			 return SUCCESS;
		}
		TItemDAO itemDao = new TItemDAO();
		List result = itemDao.vagueQueryForMobile(keyword, pageNo, pageSizeInt);
		List<ItemResult> itemResult = new ArrayList<ItemResult>(30);
		for(Object obj : result) {
			TItem item = (TItem)obj;
			TUser user = item.getTUser();
			if(user == null) {
				System.out.println("TUser is null");
				continue;
			}
			ItemResult ir = new ItemResult();
			ir.setItem_id(item.getIId());
			ir.setAvatar_path(user.getUImage());
			ir.setDescription(item.getIDescription());
			ir.setLocation(item.getIPlace());
			ir.setPrice(String.valueOf(item.getIPrice()));
			ir.setPostDate(item.getIDate().toString());
			ir.setUsername(user.getUName());
			ir.setTitle(item.getIName());
			ir.setQq(user.getUQq());
			ir.setWechat(user.getUWechat());
			ir.setPhone(user.getUPhone());
			ir.setQuality(item.getIState());
			
			List<String> postImgPath = new ArrayList<String>(5);
			Iterator<TItemphoto> it = item.getTItemphotos().iterator();  
			while(it.hasNext()) {
				TItemphoto photo = it.next();
				postImgPath.add(photo.getPPath());
			}
			ir.setPostImgPath(postImgPath);
			itemResult.add(ir);
		}
		 jsonMap.clear();
		 jsonMap.put(ConstantValue.FLAG_KEY, ConstantValue.FLAG_SUCC_VALUE);
		 jsonMap.put(ConstantValue.ITEMS_KEY, itemResult);
		 itemDao.closeSession();
		return SUCCESS;
	}
}
