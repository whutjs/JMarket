package jmarket.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import jmarket.dao.TItemDAO;
import jmarket.dao.TItemphotoDAO;
import jmarket.dao.TUserDAO;
import jmarket.pojo.ItemResult;
import jmarket.pojo.TItem;
import jmarket.pojo.TItemphoto;
import jmarket.pojo.TUser;
import jmarket.util.ConstantValue;
import jmarket.util.LocalItemCache;
import jmarket.util.LocalPhotoCache;
import jmarket.util.LocalUserCache;
import jmarket.util.Util;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 获取商品
 * @author Jenson
 *
 */

public class MGetItemAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private Map<String,Object> jsonMap = new HashMap<String, Object>();
	public Map<String, Object> getJsonMap() {
		return jsonMap;
	}
	public void setJsonMap(Map<String, Object> jsonMap) {
		this.jsonMap = jsonMap;
	}
	
	private String firstType = "";
	private String secondType = "";
	private String page = "";
	private String pageSize = "";
	private String minPrice = "";
	private String maxPrice = "";
	private String minState = "";
	private String maxState = "";
	private String place = "";
	private String order = "";
	
	
	
	public List<TItem> getTItem(List itemlist){
		List<TItem> itemsjparse =new ArrayList<TItem>();

		Iterator itr = itemlist.iterator();
		while(itr.hasNext()){
			TUser userjparse=new TUser(); 
		   TItem itemjparse=new TItem();
		   Object[] obj =  (Object[]) itr.next();
		   itemjparse.setIId(String.valueOf(obj[0]));
		   itemjparse.setIName(String.valueOf(obj[1]));
		   itemjparse.setIDescription(String.valueOf(obj[2]));
		   itemjparse.setIDate((Timestamp) obj[3]);
		   itemjparse.setIFlag(String.valueOf(obj[4]));
		   itemjparse.setIType(String.valueOf(obj[5]));
		   userjparse.setUId(String.valueOf(obj[6]));
		   itemjparse.setTUser(userjparse);
		   itemjparse.setIPrice(Float.valueOf(String.valueOf(obj[7])));
		   itemjparse.setIPlace(String.valueOf(obj[8]));
		   itemjparse.setIState(Float.valueOf(String.valueOf(obj[9])));
		   itemsjparse.add(itemjparse);
		}
		return itemsjparse;
	}
	
	 @Override
	public String execute() throws Exception {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/json");
			response.setHeader("Access-Control-Allow-Origin", "*"); //允许哪些url可以跨域请求到本域
			response.setHeader("Access-Control-Allow-Methods","POST"); //允许的请求方法，一般是GET,POST,PUT,DELETE,OPTIONS
			response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type"); //允许哪些请求头可以跨域
			
			// 客户端用httpget请求
			firstType = ServletActionContext.getRequest().getParameter(ConstantValue.FIRST_TYPE_KEY);
			secondType = ServletActionContext.getRequest().getParameter(ConstantValue.SECOND_TYPE_KEY);
			page = ServletActionContext.getRequest().getParameter(ConstantValue.PAGE_KEY);
			pageSize = ServletActionContext.getRequest().getParameter(ConstantValue.PAGE_SIZE_KEY);
			maxPrice = ServletActionContext.getRequest().getParameter(ConstantValue.MAX_PRICE_KEY);
			maxState = ServletActionContext.getRequest().getParameter(ConstantValue.MAXN_STATE_KEY);
			place = ServletActionContext.getRequest().getParameter(ConstantValue.PLACE_KEY);
			order = ServletActionContext.getRequest().getParameter(ConstantValue.ORDER_KEY);
			
			if(Util.checkStrEmpty(firstType)) {
				 // invalid access 
				 jsonMap.clear();
				 jsonMap.put(ConstantValue.FLAG_KEY, ConstantValue.FLAG_FAIL_VALUE);
				 jsonMap.put(ConstantValue.MSG_KEY, ConstantValue.MSG_MISS_FIRST_TYPE);
				 return SUCCESS;
			}
			if(Util.checkStrEmpty(page)) {
				 // invalid access 
				 jsonMap.clear();
				 jsonMap.put(ConstantValue.FLAG_KEY, ConstantValue.FLAG_FAIL_VALUE);
				 jsonMap.put(ConstantValue.MSG_KEY, ConstantValue.MSG_MISS_PAGE);
				 return SUCCESS;
			}
			if(Util.checkStrEmpty(pageSize)) {
				 // invalid access 
				 jsonMap.clear();
				 jsonMap.put(ConstantValue.FLAG_KEY, ConstantValue.FLAG_FAIL_VALUE);
				 jsonMap.put(ConstantValue.MSG_KEY, ConstantValue.MSG_MISS_PAGE_SIZE);
				 return SUCCESS;
			}
			
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
//			TItemDAO itemDao = new TItemDAO();
//			TItemphotoDAO photoDao = new TItemphotoDAO();
//			TUserDAO userDao = new TUserDAO();
			int firstTypeInt = Integer.parseInt(firstType);
			if(true) {
				HashMap<String, String> content = Util.MParse(maxPrice, maxState, place,
						firstType, secondType, order);
				// query all
				List<TItem> allResult;				
				if (!Util.checkStrEmpty(content.get("typeID"))) {
					allResult = new ArrayList<TItem>();	
					String[] idStrings = content.get("typeID").split("@");
					for (int i = 0; i < idStrings.length; i++) {
//						allResult.addAll(getTItem(itemDao.getMembersByConditionsWithPage(content.get("minPrice"),
//								content.get("maxPrice"), content.get("place"),
//								content.get("minState"),
//								content.get("maxState"), idStrings[i],
//								content.get("order"), "", pageNo, pageSizeInt))
//							);
						
						allResult.addAll(LocalItemCache.getLocalItemCache().getMembersByConditionsWithPage(content.get("minPrice"),
								content.get("maxPrice"), content.get("place"),
								content.get("minState"),
								content.get("maxState"), idStrings[i],
								content.get("order"), "", pageNo, pageSizeInt)
							);
					}
				}else{
//					allResult = getTItem(itemDao.getMembersByConditionsWithPage(content.get("minPrice"),
//							content.get("maxPrice"), content.get("place"),
//							content.get("minState"),
//							content.get("maxState"), content.get("typeID"),
//							content.get("order"), "", pageNo, pageSizeInt));
					
					allResult = LocalItemCache.getLocalItemCache().getMembersByConditionsWithPage(content.get("minPrice"),
							content.get("maxPrice"), content.get("place"),
							content.get("minState"),
							content.get("maxState"), content.get("typeID"),
							content.get("order"), "", pageNo, pageSizeInt);
				}
				List<ItemResult> itemResult = new ArrayList<ItemResult>(30);
				for(Object obj : allResult) {
					TItem item = (TItem)obj;
					TUser user = item.getTUser();
//					user = userDao.findById(user.getUId());
					user =  LocalUserCache.getLocalUserCache().getUser(user.getUId());
					if(user == null) {
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
//					List photoList = photoDao.findByPItem(item);
					List photoList = LocalPhotoCache.getLocalMsgCache().getPhotoByItem(item);
					Iterator<TItemphoto> it = photoList.iterator();  
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
//				 itemDao.closeSession();
				return SUCCESS;
			}
			return SUCCESS;
	 }
	 
}
