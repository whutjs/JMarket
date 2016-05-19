package jmarket.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jmarket.dao.TItemphotoDAO;
import jmarket.pojo.TItem;
import jmarket.pojo.TItemphoto;

public class LocalPhotoCache {
	private static HashMap<String, TItemphoto> photoCache = new HashMap<String, TItemphoto>(500);
	static{
		TItemphotoDAO photoDao = new TItemphotoDAO();
		List photos = photoDao.findAll();
		for(Object obj : photos) {
			TItemphoto photo = (TItemphoto)obj;
			photoCache.put(photo.getPId(), photo);
		}
	}
	
private static LocalPhotoCache localPhotoCache;
	
	public static LocalPhotoCache getLocalMsgCache(){
		if(localPhotoCache == null) {
			localPhotoCache = new LocalPhotoCache();
		}
		return localPhotoCache;
	}
	
	public synchronized void addItemPhoto(TItemphoto photo) {
		photoCache.put(photo.getPId(), photo);
	}
	
	public synchronized void removeMsg(TItemphoto photo) {
		photoCache.remove(photo.getPId());
	}
	
	public synchronized  TItemphoto getItemPhoto(String pid) {
		return photoCache.get(pid);
	}
	
	public synchronized  List<TItemphoto> getPhotoByItem(TItem item) {
		String itemId = item.getIId();
		List<TItemphoto> photos = new ArrayList<TItemphoto>();
		for(String pid : photoCache.keySet()) {
			TItemphoto photo = photoCache.get(pid);
			if(photo.getTItem().getIId().equals(itemId)) {
				photos.add(photo);
			}
		}
		return photos;
	}
	
}
