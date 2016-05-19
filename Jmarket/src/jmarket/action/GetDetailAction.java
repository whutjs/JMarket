package jmarket.action;

import java.util.HashMap;
import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;


import jmarket.dao.TItemDAO;
import jmarket.dao.TItemphotoDAO;
import jmarket.dao.TMessageDAO;
import jmarket.dao.TUserDAO;
import jmarket.pojo.TItem;
import jmarket.pojo.TItemphoto;
import jmarket.pojo.TMessage;
import jmarket.pojo.TUser;
import jmarket.util.LocalItemCache;
import jmarket.util.LocalMessageCache;
import jmarket.util.LocalPhotoCache;
import jmarket.util.LocalUserCache;

public class GetDetailAction extends ActionSupport implements ServletRequestAware {
	
	private static final long serialVersionUID = 1009982L;
	private String itemID;
	private HttpSession session;

	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.session=request.getSession();
	}
	public String execute(){
		try{
//		TMessageDAO tmd=new TMessageDAO();
		TItem item=getTItem(itemID);
		TUser user4Item=getTUser(item.getTUser().getUId());
		HashMap<String, String[]> itemPhotos=matchPicture(item);
		@SuppressWarnings("rawtypes")
//		List commentList=tmd.findByProperty("TItem", item);	
		List commentList = LocalMessageCache.getLocalMsgCache().getMsgByItem(item);
		HashMap<TMessage, TMessage[]> messagepair=new HashMap<TMessage, TMessage[]>();
		for(int i=0;i<commentList.size();i++){
			TMessage tMessage=(TMessage) commentList.get(i);
			if(tMessage.getTMessage()==null||tMessage.getTMessage().getMId()==null){
				messagepair.put(tMessage, null);
			}			
		}
		Object[] keysSet=messagepair.keySet().toArray();
		for(int i=0;i<keysSet.length;i++){
			TMessage[] tMessages=new TMessage[commentList.size()];
			TMessage temp=(TMessage) keysSet[i];
			for(int j=0;j<commentList.size();j++){
				TMessage tMessage=(TMessage) commentList.get(j);
				if(tMessage.getTMessage()!=null&&tMessage.getTMessage().getMId()!=null&&tMessage.getTMessage().getMId()==temp.getMId()){
					tMessages[j]=tMessage;
				}	
			messagepair.remove(temp);
			messagepair.put(temp, tMessages);			
		}	
		}
		String[] stemp=itemPhotos.get(item.getIId());
		session.removeAttribute("user4Item");
		session.setAttribute("user4Item", user4Item);
		session.removeAttribute("itemdetail");
		session.setAttribute("itemdetail", item);
		session.removeAttribute("photos");
		session.setAttribute("photos",itemPhotos.get(item.getIId()));
		session.removeAttribute("comment");
		session.setAttribute("comment", messagepair);
		session.removeAttribute("commentkey");
		session.setAttribute("commentkey", keysSet);
		session.removeAttribute("number");
		session.setAttribute("number", commentList.size());
		return SUCCESS;
		}
		catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			return ERROR;
		}
		
	}
	private HashMap<String, String[]> matchPicture(TItem item){
		HashMap<String, String[]> photoiteMap=new HashMap<String, String[]>();
//		TItemphotoDAO tipdDao=new TItemphotoDAO();
			@SuppressWarnings("rawtypes")
//			List itemphotos=tipdDao.findByProperty("TItem",item);
			List itemphotos = LocalPhotoCache.getLocalMsgCache().getPhotoByItem(item);
			if(itemphotos==null){
				photoiteMap.put(item.getIId(), null);
			}else if(itemphotos.size()==0){
				photoiteMap.put(item.getIId(),null );
			}else {
				String [] stemps=new String[itemphotos.size()];
				for(int i=0;i<itemphotos.size();i++){
			TItemphoto itemphoto=new TItemphoto();
				itemphoto=(TItemphoto)itemphotos.get(i);
				stemps[i]=itemphoto.getPPath();
				}
				photoiteMap.put(item.getIId(),stemps);
			}
		return photoiteMap;
		
	}
	private TItem getTItem(String itemid){
//		TItemDAO tid=new TItemDAO();
//		return tid.findById(itemid);
		return LocalItemCache.getLocalItemCache().getItem(itemid);
	}
	private TUser getTUser(String userid){
//		TUserDAO tud=new TUserDAO();
//		return tud.findById(userid);
		return LocalUserCache.getLocalUserCache().getUser(userid);
	}
	public String getItemID() {
		return itemID;
	}
	public void setItemID(String itemID) {
		this.itemID = itemID;
	}
}
