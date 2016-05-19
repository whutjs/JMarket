package jmarket.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jmarket.dao.TItemDAO;
import jmarket.dao.TMessageDAO;
import jmarket.pojo.TItem;
import jmarket.pojo.TMessage;
import jmarket.pojo.TUser;

/**
 * 本地缓存评论消息
 * @author Jenson
 *
 */
public class LocalMessageCache {
	private LocalMessageCache() {
	}
	
	private static HashMap<String, TMessage> msgCache = new HashMap<String, TMessage>(500);
	static{
		TMessageDAO msgDao = new TMessageDAO();
		List msgs = msgDao.findAll();
		for(Object obj : msgs) {
			TMessage msg = (TMessage)obj;
			msgCache.put(msg.getMId(), msg);
		}
	}
	private static LocalMessageCache localMsgCache;
	
	public static LocalMessageCache getLocalMsgCache(){
		if(localMsgCache == null) {
			localMsgCache = new LocalMessageCache();
		}
		return localMsgCache;
	}
	
	public synchronized void addMsg(TMessage msg) {
		msgCache.put(msg.getMId(), msg);
	}
	
	public synchronized void removeMsg(TMessage msg) {
		msgCache.remove(msg.getMId());
	}
	
	public synchronized  TMessage getMsg(String msgId) {
		return msgCache.get(msgId);
	}
	
	public synchronized  List<TMessage> getMsgByItem(TItem item) {
		String itemId = item.getIId();
		List<TMessage> msgs = new ArrayList<TMessage>();
		for(String mid : msgCache.keySet()) {
			TMessage msg = msgCache.get(mid);
			if(msg.getTItem().getIId().equals(itemId)) {
				msgs.add(msg);
			}
		}
		return msgs;
	}
	
	public synchronized  List<TMessage> getMsgByUser(TUser user) {
		String uid = user.getUId();
		List<TMessage> msgs = new ArrayList<TMessage>();
		for(String mid : msgCache.keySet()) {
			TMessage msg = msgCache.get(mid);
			if(msg.getTUser().getUId().equals(uid)) {
				msgs.add(msg);
			}
		}
		return msgs;
	}
	
	public synchronized  List<TMessage> getMsgByParentId(TMessage parentId) {
		String msgid = parentId.getMId();
		List<TMessage> msgs = new ArrayList<TMessage>();
		for(String mid : msgCache.keySet()) {
			TMessage msg = msgCache.get(mid);
			TMessage parentMsg = msg.getTMessage();
			if(parentMsg != null && parentMsg.getMId().equals(msgid)) {
				msgs.add(msg);
			}
		}
		return msgs;
	}
}
