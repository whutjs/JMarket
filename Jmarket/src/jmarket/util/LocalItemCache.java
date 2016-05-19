package jmarket.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;


import jmarket.dao.TItemDAO;
import jmarket.pojo.TItem;
import jmarket.pojo.TMessage;
import jmarket.pojo.TUser;

/**
 * 在本地缓存商品
 * @author Jenson
 *
 */
class ItemPriceComparator implements Comparator{

	@Override
	public int compare(Object o1, Object o2) {
		TItem item1 = (TItem) o1;
		TItem item2 = (TItem) o2;
		return item1.getIPrice().compareTo(item2.getIPrice());
	}
	
}

class ItemDateComparator implements Comparator{

	@Override
	public int compare(Object o1, Object o2) {
		TItem item1 = (TItem) o1;
		TItem item2 = (TItem) o2;
		int res = item1.getIDate().compareTo(item2.getIDate());
		if(res == 0) {
			return res;
		}
		if(res < 0) {
			return 1;
		}
		return -1;
	}
	
}
public class LocalItemCache {
	
	private LocalItemCache() {
	}
	
	private static HashMap<String, TItem> itemCache = new HashMap<String, TItem>(500);
	static{
		TItemDAO itemDao = new TItemDAO();
		List items = itemDao.findAll();
		for(Object obj : items) {
			TItem item = (TItem)obj;
			itemCache.put(item.getIId(), item);
		}
	}
	private static LocalItemCache localItemCache;
	
	public static LocalItemCache getLocalItemCache(){
		if(localItemCache == null) {
			localItemCache = new LocalItemCache();
		}
		return localItemCache;
	}
	
	public synchronized void addItem(TItem item) {
		itemCache.put(item.getIId(), item);
	}
	
	public synchronized  TItem getItem(String id) {
		return itemCache.get(id);
	}
	
	public synchronized  List<TItem> getItemByUser(TUser user) {
		String uid = user.getUId();
		List<TItem> items = new ArrayList<TItem>();
		for(String itemId : itemCache.keySet()) {
			TItem item = itemCache.get(itemId);
			if(item.getTUser().getUId().equals(uid)) {
				items.add(item);
			}
		}
		return items;
	}
	
	public synchronized void removetItem(String id) {
		itemCache.remove(id);
	}
	
	public synchronized  List<TItem> getAllItem() {
		return new ArrayList<TItem>(itemCache.values());
	}
	
	public synchronized List<TItem> getMembersByConditions(String minPrice, String maxPrice, String place, String minState,
			String maxState,String typeID,String order,String text) throws Exception {
		List<TItem> list = new ArrayList<TItem>(50);
		for(String itemId : itemCache.keySet()){
			TItem item = itemCache.get(itemId);
			if(!Util.checkStrEmpty(typeID) && !item.getIType().equals(typeID)){
				continue;
			}
			if(!Util.checkStrEmpty(text)  && !item.getIName().contains(text)){
				continue;
			}
			if(!Util.checkStrEmpty(minPrice) && !Util.checkStrEmpty(maxPrice)) {
				float price = item.getIPrice();
				float minPriceFloat = Float.parseFloat(minPrice);
				float maxPriceFloat = Float.parseFloat(maxPrice);
				if(price < minPriceFloat || price > maxPriceFloat) {
					continue;
				}
			}else if(!Util.checkStrEmpty(minPrice) && Util.checkStrEmpty(maxPrice)){
				float minPriceFloat = Float.parseFloat(minPrice);
				float price = item.getIPrice();
				if(price < minPriceFloat) {
					continue;
				}
			}else if(Util.checkStrEmpty(minPrice) && !Util.checkStrEmpty(maxPrice)){
				float maxPriceFloat = Float.parseFloat(maxPrice);
				float price = item.getIPrice();
				if(price > maxPriceFloat) {
					continue;
				}
			}
			if(!Util.checkStrEmpty(place) && !item.getIPlace().contains(place)){
				continue;
			}
			if(!Util.checkStrEmpty(minState) && !Util.checkStrEmpty(maxState)) {
				float state = item.getIState();
				float minStateFloat = Float.parseFloat(minState);
				float maxStateFloat = Float.parseFloat(maxState);
				if(state < minStateFloat || state > maxStateFloat) {
					continue;
				}
			}else if(!Util.checkStrEmpty(minState) && Util.checkStrEmpty(maxState)){
				float state = item.getIState();
				float minStateFloat = Float.parseFloat(minState);
				if(state < minStateFloat) {
					continue;
				}
			}else if(Util.checkStrEmpty(minState) && !Util.checkStrEmpty(maxState)){
				float state = item.getIState();
				float maxStateFloat = Float.parseFloat(maxState);
				if(state > maxStateFloat) {
					continue;
				}
			}
			list.add(item);
		}
		if(!Util.checkStrEmpty(order) && order.equals("price")){
				Collections.sort(list, new ItemPriceComparator());
		}else {
			Collections.sort(list, new ItemDateComparator());
		}
		
		return list;
	} 
	
	public synchronized  List<TItem> getMembersByConditionsWithPage(String minPrice, String maxPrice, String place, String minState,
			String maxState,String typeID,String order,String text, int page, int pageSize) throws Exception {
		List<TItem> list = new ArrayList<TItem>(50);
		for(String itemId : itemCache.keySet()){
			TItem item = itemCache.get(itemId);
			if(!Util.checkStrEmpty(typeID) && !item.getIType().equals(typeID)){
				continue;
			}
			if(!Util.checkStrEmpty(text)  && !item.getIName().contains(text)){
				continue;
			}
			if(!Util.checkStrEmpty(minPrice) && !Util.checkStrEmpty(maxPrice)) {
				float price = item.getIPrice();
				float minPriceFloat = Float.parseFloat(minPrice);
				float maxPriceFloat = Float.parseFloat(maxPrice);
				if(price < minPriceFloat || price > maxPriceFloat) {
					continue;
				}
			}else if(!Util.checkStrEmpty(minPrice) && Util.checkStrEmpty(maxPrice)){
				float minPriceFloat = Float.parseFloat(minPrice);
				float price = item.getIPrice();
				if(price < minPriceFloat) {
					continue;
				}
			}else if(Util.checkStrEmpty(minPrice) && !Util.checkStrEmpty(maxPrice)){
				float maxPriceFloat = Float.parseFloat(maxPrice);
				float price = item.getIPrice();
				if(price > maxPriceFloat) {
					continue;
				}
			}
			if(!Util.checkStrEmpty(place) && !item.getIPlace().contains(place)){
				continue;
			}
			if(!Util.checkStrEmpty(minState) && !Util.checkStrEmpty(maxState)) {
				float state = item.getIState();
				float minStateFloat = Float.parseFloat(minState);
				float maxStateFloat = Float.parseFloat(maxState);
				if(state < minStateFloat || state > maxStateFloat) {
					continue;
				}
			}else if(!Util.checkStrEmpty(minState) && Util.checkStrEmpty(maxState)){
				float state = item.getIState();
				float minStateFloat = Float.parseFloat(minState);
				if(state < minStateFloat) {
					continue;
				}
			}else if(Util.checkStrEmpty(minState) && !Util.checkStrEmpty(maxState)){
				float state = item.getIState();
				float maxStateFloat = Float.parseFloat(maxState);
				if(state > maxStateFloat) {
					continue;
				}
			}
			list.add(item);
		}
		if(!Util.checkStrEmpty(order) && order.equals("price")){
			Collections.sort(list, new ItemPriceComparator());
		}else {
			Collections.sort(list, new ItemDateComparator());
		}
		if(list.size() <= pageSize){
			return list;
		}
		List<TItem> pageResult = new ArrayList<TItem>(50);
		if((page-1)*pageSize >= list.size()) {
			return pageResult;
		}
		int idx = (page-1)*pageSize;
		for(; idx < list.size(); idx++) {
			pageResult.add(list.get(idx));
		}
		return pageResult;
		
	} 
	
}
