package jmarket.util;

import java.util.HashMap;
import java.util.List;

import jmarket.dao.TUserDAO;
import jmarket.pojo.TUser;
import jmarket.pojo.TUser;

public class LocalUserCache {
	private LocalUserCache() {
	}
	
	private static HashMap<String, TUser> userCache = new HashMap<String, TUser>(100);
	static{
		TUserDAO userDao = new TUserDAO();
		List users = userDao.findAll();
		for(Object obj : users) {
			TUser user = (TUser)obj;
			userCache.put(user.getUId(), user);
		}
	}
	private static LocalUserCache localUserCache;
	
	public static LocalUserCache getLocalUserCache(){
		if(localUserCache == null) {
			localUserCache = new LocalUserCache();
		}
		return localUserCache;
	}
	
	public synchronized void addUser(TUser user) {
		userCache.put(user.getUId(), user);
	}
	
	public synchronized  TUser getUser(String id) {
		return userCache.get(id);
	}
	
	public synchronized  void removeUser(String id) {
		userCache.remove(id);
	}
	
	public synchronized  TUser getUserByEmail(String email) {
		TUser user = null;
		for(String uid : userCache.keySet()) {
			TUser tmp = userCache.get(uid);
			if(tmp.getUEmail().equals(email)) {
				user = tmp;
				break;
			}
		}
		return user;
	}
	
	public synchronized  TUser getUserByName(String name) {
		TUser user = null;
		for(String uid : userCache.keySet()) {
			TUser tmp = userCache.get(uid);
			if(tmp.getUName().equals(name)) {
				user = tmp;
				break;
			}
		}
		return user;
	}
}
