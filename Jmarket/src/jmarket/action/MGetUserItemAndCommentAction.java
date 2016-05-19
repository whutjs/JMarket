package jmarket.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

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
import jmarket.util.LocalItemCache;
import jmarket.util.LocalMessageCache;
import jmarket.util.LocalUserCache;
import jmarket.util.Util;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 获取特定用户的所有商品，需要:user_id
 * @author Jenson
 *
 */
public class MGetUserItemAndCommentAction   extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private Map<String,Object> jsonMap = new HashMap<String, Object>();
	public Map<String, Object> getJsonMap() {
		return jsonMap;
	}
	public void setJsonMap(Map<String, Object> jsonMap) {
		this.jsonMap = jsonMap;
	}
	
	private String userId = "";
	
	 @Override
		public String execute() throws Exception {
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setCharacterEncoding("utf-8");
				response.setContentType("application/json");
				response.setHeader("Access-Control-Allow-Origin", "*"); //允许哪些url可以跨域请求到本域
				response.setHeader("Access-Control-Allow-Methods","POST"); //允许的请求方法，一般是GET,POST,PUT,DELETE,OPTIONS
				response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type"); //允许哪些请求头可以跨域
				
				userId = ServletActionContext.getRequest().getParameter(ConstantValue.UID_KEY);
				if(Util.checkStrEmpty(userId)) {
					 jsonMap.clear();
					 jsonMap.put(ConstantValue.FLAG_KEY, ConstantValue.FLAG_FAIL_VALUE);
					 jsonMap.put(ConstantValue.MSG_KEY, "user id is null");
					 return SUCCESS;
				}
				
//				TItemDAO itemDao = new TItemDAO();
//				TUserDAO userDao = new TUserDAO();
//				TMessageDAO msgDao = new TMessageDAO();
				
//				TUser user = userDao.findById(userId);
				TUser user = LocalUserCache.getLocalUserCache().getUser(userId);
				if(user == null) {
					jsonMap.clear();
					 jsonMap.put(ConstantValue.FLAG_KEY, ConstantValue.FLAG_FAIL_VALUE);
					 jsonMap.put(ConstantValue.MSG_KEY, "con not find user according to user id");
//					 userDao.closeSession();
					 return SUCCESS;
				}
//				List itemList = itemDao.findByIUser(user);
				List itemList = LocalItemCache.getLocalItemCache().getItemByUser(user);
				if(itemList == null) {
					itemList = new ArrayList();
				}
				List<ItemResult> itemResult = new ArrayList<ItemResult>(30);
				List<MessageResult> msgResult = new ArrayList<MessageResult>(50);
				for(Object obj : itemList) {
					TItem item = (TItem)obj;
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
					List<String> postImgPath = new ArrayList<String>(5);
					Iterator<TItemphoto> it = item.getTItemphotos().iterator();  
					while(it.hasNext()) {
						TItemphoto photo = it.next();
						postImgPath.add(photo.getPPath());
					}
					ir.setPostImgPath(postImgPath);
					itemResult.add(ir);
					
//					List<Object> result = msgDao.findByTItem(item);
					List<TMessage> result = LocalMessageCache.getLocalMsgCache().getMsgByItem(item);
					for(TMessage msg : result) {
						MessageResult comment = new MessageResult();
						comment.setContent(msg.getMContent());
						comment.setDate(msg.getMDate().toString());
						comment.setItem_id(item.getIId());
						comment.setMsg_id(msg.getMId());
						comment.setUser_name(msg.getTUser().getUName());
						comment.setAvatar(msg.getTUser().getUImage());
						TMessage parentMsg = msg.getTMessage();
						if(parentMsg != null) {
							comment.setParent_msg_id(parentMsg.getMId());
						}
						msgResult.add(comment);
					}
				}
				
				
				 jsonMap.clear();
				 jsonMap.put(ConstantValue.FLAG_KEY, ConstantValue.FLAG_SUCC_VALUE);
				 jsonMap.put(ConstantValue.ITEMS_KEY, itemResult);
				 jsonMap.put(ConstantValue.MSG_INFO_KEY, msgResult);

//				 userDao.closeSession();
//				 itemDao.closeSession();
//				 msgDao.closeSession();
				 return  SUCCESS;
				
		 }
}
