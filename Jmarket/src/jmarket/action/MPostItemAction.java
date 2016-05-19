package jmarket.action;

import java.io.BufferedReader;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jmarket.dao.TItemDAO;
import jmarket.dao.TItemphotoDAO;
import jmarket.dao.TUserDAO;
import jmarket.pojo.TItem;
import jmarket.pojo.TItemphoto;
import jmarket.pojo.TUser;
import jmarket.util.ConstantValue;
import jmarket.util.LocalItemCache;
import jmarket.util.LocalUserCache;
import jmarket.util.Util;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 发布商品
 * @author Jenson
 *
 */
public class MPostItemAction   extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private Map<String,Object> jsonMap = new HashMap<String, Object>();
	
	private String token = "";
	private String firstType = "";
	private String secondType = "";
	private String quality = "";
	private String price = "";
	private String title = "";
	private String detail = "";
	private String location = "";
	private String imgSize = "";
	
	public Map<String, Object> getJsonMap() {
		return jsonMap;
	}
	public void setJsonMap(Map<String, Object> jsonMap) {
		this.jsonMap = jsonMap;
	}
	
	 @Override
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
			  jsonMap.put(ConstantValue.MSG_KEY, ConstantValue.MSG_BADREQUEST);
			  return SUCCESS;
		  }
		
		  token = jsonObject.optString(ConstantValue.TOKEN_KEY, "");
		  firstType = jsonObject.optString(ConstantValue.FIRST_TYPE_KEY, "");
		 secondType = jsonObject.optString(ConstantValue.SECOND_TYPE_KEY, "");
		 quality = jsonObject.optString(ConstantValue.QUALITY_KEY, "");
		 price = jsonObject.optString(ConstantValue.PRICE_KEY, "");
		 title = jsonObject.optString(ConstantValue.TITLE_KEY, "");
		 detail = jsonObject.optString(ConstantValue.DETAIL_KEY, "");
		 location = jsonObject.optString(ConstantValue.LOCATION_KEY, "");
		 imgSize = jsonObject.optString(ConstantValue.IMGSIZE_KEY, "");
		 
		if(Util.checkStrEmpty(token)) {
			jsonMap.clear();
			jsonMap.put(ConstantValue.FLAG_KEY, ConstantValue.FLAG_FAIL_VALUE);
			jsonMap.put(ConstantValue.MSG_KEY, "token is null");
			return SUCCESS;
		}
		if(Util.checkStrEmpty(firstType)) {
			jsonMap.clear();
			jsonMap.put(ConstantValue.FLAG_KEY, ConstantValue.FLAG_FAIL_VALUE);
			jsonMap.put(ConstantValue.MSG_KEY, "first type can not be null");
			return SUCCESS;
		}
		if(Util.mobileGlobalTokenToEmail.containsKey(token) == false) {
			 // invalid access 
			jsonMap.clear();
			jsonMap.put(ConstantValue.FLAG_KEY, ConstantValue.FLAG_FAIL_VALUE);
			jsonMap.put(ConstantValue.MSG_KEY, ConstantValue.MSG_INVALID_TOKEN);
			return SUCCESS;
		}
//		TUserDAO userDAO = new TUserDAO();
		TItemDAO itemDAO = new TItemDAO();
		TItemphotoDAO itemPhotoDao = new TItemphotoDAO();	
		
		String uid = Util.getUUID();
		boolean exits = false;
//		TItem item = itemDAO.findById(uid);
		TItem item = LocalItemCache.getLocalItemCache().getItem(uid);
		if(item != null) {
			exits = true;
		}else{
			item = new TItem();
			item.setIId(uid);
		}
		item.setIFlag("0");
		item.setIDescription(detail);
		item.setIName(title);
		item.setIPrice(Float.parseFloat(price));
		item.setIState(Float.parseFloat(quality));
		item.setIPlace(location);
//		firstType = new String(firstType.getBytes("utf-8"));
//		secondType = new String(secondType.getBytes("utf-8"));
//		System.out.println(firstType+" : "+secondType);
		String[] typeRes = Util.parseType(firstType, secondType);
		
		String[] iType = Util.getTypeID(typeRes[0], typeRes[1]);
		item.setIType(iType[0]);
		
		Calendar calendar = Calendar.getInstance();
		Date date = new Date();
		calendar.setTime(date);
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		item.setIDate(ts);
		
		String email = Util.mobileGlobalTokenToEmail.get(token);
		
//		TUser user = (TUser) userDAO.findByUEmail(email).get(0);
		TUser user = LocalUserCache.getLocalUserCache().getUserByEmail(email);
		item.setTUser(user);
		
		itemDAO.save(item);
		
		// save item photo
		JSONArray imgJsonArray = jsonObject.getJSONArray(ConstantValue.IMGSRC_KEY);
		int imgArraySize = Integer.parseInt(imgSize);
		for(int i = 0; i < imgArraySize; i++) {
			TItemphoto itemPhoto = new TItemphoto();
			String imgPath = imgJsonArray.getString(i);
			itemPhoto.setPPath(imgPath);
			itemPhoto.setTItem(item);
			itemPhoto.setPId(Util.getUUID());
			itemPhotoDao.save(itemPhoto);
		}
		
		jsonMap.clear();
		jsonMap.put(ConstantValue.FLAG_KEY, ConstantValue.FLAG_SUCC_VALUE);
		
//		userDAO.closeSession();
		itemDAO.closeSession();
		itemPhotoDao.closeSession();
		
		return SUCCESS;
	 }
}
