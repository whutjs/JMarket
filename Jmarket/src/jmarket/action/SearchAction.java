package jmarket.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javassist.compiler.ast.NewExpr;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jmarket.dao.*;
import jmarket.pojo.TItem;
import jmarket.pojo.TItemphoto;
import jmarket.pojo.TItemtype;
import jmarket.pojo.TUser;
import jmarket.util.LocalItemCache;
import jmarket.util.LocalPhotoCache;
import jmarket.util.Util;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import java.sql.Timestamp;

public class SearchAction extends ActionSupport implements ServletRequestAware {

	private static final long serialVersionUID = 2233L;
	// private String price="5";
	// private String campus;
	// private String quality="5";
	// private String type1="1";
	// private String type2="100";
	// private String page="1";
	// private String text="自行车";
	// private String order="1";
	private String price;
	private String campus;
	private String quality;
	private String first_cate;
	private String second_cate;
	private String pagination;
	// 关键字
	private String search_item;
	private String sort_by;
	private TMessageDAO tmd = new TMessageDAO();
	@SuppressWarnings("rawtypes")
	private List itemIDs = tmd.getItemtIDByComment();

	private HttpSession session;
	private Cookie[] cookie;
	private HttpServletResponse response=ServletActionContext.getResponse();

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.session = request.getSession();
		this.cookie=request.getCookies();
	}

	@SuppressWarnings("unchecked")
	public String execute() {
		setCookie("0", "0");
		HashMap<String, String> content = Util.Parse(price, quality, campus,
				first_cate, second_cate, sort_by);
		TItemDAO tid = new TItemDAO();
		TItem[] itemarray = new TItem[24];
		HashMap<String, String> photoiteMap = new HashMap<String, String>();
		try {

			if (pagination == null && price == null && campus == null
					&& quality == null && first_cate == null
					&& second_cate == null && search_item == null) {
				init();
				return SUCCESS;
			}
			@SuppressWarnings("rawtypes")
			List items = new ArrayList();
			if (pagination == null) {
				pagination = "1";
			}
			if ((content.get("order") != null && !content.get("order").equals(
					"hot"))
					|| content.get("order") == null) {
				if (!Util.checkStrEmpty(content.get("typeID"))) {
					String[] idStrings = content.get("typeID").split("@");
					for (int i = 0; i < idStrings.length; i++) {
//						items.addAll(cutAddress(getTItem(tid.getMembersByConditions(
//								content.get("minPrice"),
//								content.get("maxPrice"), content.get("place"),
//								content.get("minState"),
//								content.get("maxState"), idStrings[i],
//								content.get("order"), search_item))));
						items.addAll(cutAddress((LocalItemCache.getLocalItemCache().getMembersByConditions(
								content.get("minPrice"),
								content.get("maxPrice"), content.get("place"),
								content.get("minState"),
								content.get("maxState"), idStrings[i],
								content.get("order"), search_item))));
						// items=tid.getMembersByConditions(content.get("minPrice"),
						// content.get("maxPrice"), content.get("place"),
						// content.get("minState"), content.get("maxState"),
						// content.get("typeID"),content.get("order"),
						// search_item);
					}
				} else {
//					items = cutAddress(getTItem(tid.getMembersByConditions(
//							content.get("minPrice"), content.get("maxPrice"),
//							content.get("place"), content.get("minState"),
//							content.get("maxState"), content.get("typeID"),
//							content.get("order"), search_item)));
					items = cutAddress(LocalItemCache.getLocalItemCache().getMembersByConditions(
							content.get("minPrice"), content.get("maxPrice"),
							content.get("place"), content.get("minState"),
							content.get("maxState"), content.get("typeID"),
							content.get("order"), search_item));
				}
				if (items == null || items.size() == 0) {
					if (!Util.checkStrEmpty(content.get("typeID"))) {
						String[] idStrings = content.get("typeID").split("@");
						for (int i = 0; i < idStrings.length; i++) {
//							items.addAll(cutAddress(getTItem(tid
//									.getMembersByConditionsVague(
//											content.get("minPrice"),
//											content.get("maxPrice"),
//											content.get("place"),
//											content.get("minState"),
//											content.get("maxState"),
//											idStrings[i],
//											content.get("order"), search_item))));
							
							items.addAll(cutAddress(LocalItemCache.getLocalItemCache()
									.getMembersByConditions(
											content.get("minPrice"),
											content.get("maxPrice"),
											content.get("place"),
											content.get("minState"),
											content.get("maxState"),
											idStrings[i],
											content.get("order"), search_item)));
							// items=tid.getMembersByConditions(content.get("minPrice"),
							// content.get("maxPrice"), content.get("place"),
							// content.get("minState"), content.get("maxState"),
							// content.get("typeID"),content.get("order"),
							// search_item);
						}
					} else {
//						items = cutAddress(getTItem(tid.getMembersByConditionsVague(
//								content.get("minPrice"),
//								content.get("maxPrice"), content.get("place"),
//								content.get("minState"),
//								content.get("maxState"), content.get("typeID"),
//								content.get("order"), search_item)));
						
						items = cutAddress(LocalItemCache.getLocalItemCache().getMembersByConditions(
								content.get("minPrice"),
								content.get("maxPrice"), content.get("place"),
								content.get("minState"),
								content.get("maxState"), content.get("typeID"),
								content.get("order"), search_item));
					}
				}
				if (items == null || items.size() == 0) {
					return "noresult";
				}

				if (items.size() <= Integer.parseInt(pagination) * 24) {
					for (int i = 0; ((Integer.parseInt(pagination) - 1) * 24 + i) < items
							.size(); i++) {
						itemarray[i] = (TItem) items.get((Integer
								.parseInt(pagination) - 1) * 24 + i);
					}
				} else {
					for (int i = 0; (Integer.parseInt(pagination) - 1) * 24 + i < Integer
							.parseInt(pagination) * 24; i++) {
						itemarray[i] = (TItem) items.get((Integer
								.parseInt(pagination) - 1) * 24 + i);
					}
				}
				session.removeAttribute("size");
				session.setAttribute("size", items.size());
				session.removeAttribute("page");
				session.setAttribute("page",
						(int) Math.ceil(items.size() / (double) 24));
				session.removeAttribute("items");
				session.setAttribute("items", itemarray);
				photoiteMap = matchPicture(items);
				session.removeAttribute("itemphoto");
				session.setAttribute("itemphoto", photoiteMap);
				session.removeAttribute("currentpage");
				if (pagination == null || pagination.equals("0")) {
					
					session.setAttribute("currentpage", 1);
				} else {
					session.setAttribute("currentpage",
							Integer.parseInt(pagination));
				}
				setCookie(first_cate, second_cate);
				return SUCCESS;
			} else {
				if (!Util.checkStrEmpty(content.get("typeID"))) {
					String[] idStrings = content.get("typeID").split("@");
					for (int i = 0; i < idStrings.length; i++) {
//						items.addAll(cutAddress(getTItem(tid.getMembersByConditions(
//								content.get("minPrice"),
//								content.get("maxPrice"), content.get("place"),
//								content.get("minState"),
//								content.get("maxState"), idStrings[i],
//								content.get("order"), search_item))));
						
						items.addAll(cutAddress(LocalItemCache.getLocalItemCache().getMembersByConditions(
								content.get("minPrice"),
								content.get("maxPrice"), content.get("place"),
								content.get("minState"),
								content.get("maxState"), idStrings[i],
								content.get("order"), search_item)));
						// items=tid.getMembersByConditions(content.get("minPrice"),
						// content.get("maxPrice"), content.get("place"),
						// content.get("minState"), content.get("maxState"),
						// content.get("typeID"),content.get("order"),
						// search_item);
					}
				} else {
//					items = cutAddress(getTItem(tid.getMembersByConditions(
//							content.get("minPrice"), content.get("maxPrice"),
//							content.get("place"), content.get("minState"),
//							content.get("maxState"), content.get("typeID"),
//							content.get("order"), search_item)));
					
					items = cutAddress(LocalItemCache.getLocalItemCache().getMembersByConditions(
							content.get("minPrice"), content.get("maxPrice"),
							content.get("place"), content.get("minState"),
							content.get("maxState"), content.get("typeID"),
							content.get("order"), search_item));
				}
				if (items == null || items.size() == 0) {
					if (!Util.checkStrEmpty(content.get("typeID"))) {
						String[] idStrings = content.get("typeID").split("@");
						for (int i = 0; i < idStrings.length; i++) {
//							items.addAll(cutAddress(getTItem(tid
//									.getMembersByConditionsVague(
//											content.get("minPrice"),
//											content.get("maxPrice"),
//											content.get("place"),
//											content.get("minState"),
//											content.get("maxState"),
//											idStrings[i],
//											content.get("order"), search_item))));
							items.addAll(cutAddress(LocalItemCache.getLocalItemCache()
									.getMembersByConditions(
											content.get("minPrice"),
											content.get("maxPrice"),
											content.get("place"),
											content.get("minState"),
											content.get("maxState"),
											idStrings[i],
											content.get("order"), search_item)));
							// items=tid.getMembersByConditions(content.get("minPrice"),
							// content.get("maxPrice"), content.get("place"),
							// content.get("minState"), content.get("maxState"),
							// content.get("typeID"),content.get("order"),
							// search_item);
						}
					} else {
//						items = cutAddress(getTItem(tid.getMembersByConditionsVague(
//								content.get("minPrice"),
//								content.get("maxPrice"), content.get("place"),
//								content.get("minState"),
//								content.get("maxState"), content.get("typeID"),
//								content.get("order"), search_item)));
						items = cutAddress(LocalItemCache.getLocalItemCache().getMembersByConditions(
								content.get("minPrice"),
								content.get("maxPrice"), content.get("place"),
								content.get("minState"),
								content.get("maxState"), content.get("typeID"),
								content.get("order"), search_item));
					}
				}
				if (items == null || items.size() == 0) {
					return "noresult";
				}
				List<String> itemids = new ArrayList<String>();
				HashMap<String, TItem> tempmap = new HashMap<String, TItem>();
				TItem tempItem = new TItem();
				for (int i = 0; i < items.size(); i++) {
					tempItem = (TItem) items.get(i);
					itemids.add(tempItem.getIId());
					tempmap.put(tempItem.getIId(), tempItem);
				}
				changePage(Integer.parseInt(pagination), itemids, tempmap);
				// photoiteMap=matchPicture(items);
				// session.setAttribute("itemphoto", photoiteMap);
				setCookie(first_cate, second_cate);
				return SUCCESS;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}

	}

	@SuppressWarnings("unchecked")
	public List getTItem(List itemlist) {
		List<TItem> itemsjparse = new ArrayList<TItem>();

		TUser userjparse = new TUser();
		Iterator itr = itemlist.iterator();
		while (itr.hasNext()) {
			TItem itemjparse = new TItem();
			Object[] obj = (Object[]) itr.next();
			itemjparse.setIId(String.valueOf(obj[0]));
			itemjparse.setIName(String.valueOf(obj[1]));
			itemjparse.setIDescription(String.valueOf(obj[2]));
			itemjparse.setIDate((Timestamp)obj[3]);
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

	private String SelectClass(String condition) {
		String[] temp;
		temp = condition.split("-");
		TItemtypeDAO titd = new TItemtypeDAO();
		TItemDAO tid = new TItemDAO();
		List itemTypeList;
		@SuppressWarnings("rawtypes")
		List itemList = new ArrayList();
		if (temp.length == 1) {
			itemTypeList = titd.findByTMainclass(temp[0]);
		} else if (temp.length == 2) {
			itemTypeList = titd.findByTSecondclass(temp[1]);
		} else {
			return ERROR;
		}
		if (itemTypeList == null) {
			return "noresult";
		} else {
			for (int i = 0; i < itemTypeList.size(); i++) {
				TItemtype tit = (TItemtype) itemTypeList.get(i);
				itemList.addAll(tid.findByIType(tit.getTId()));
				if (itemList.size() == 0) {
					return "noresult";
				}
			}
			session.setAttribute("item", itemList);
			return SUCCESS;
		}
	}

	private String SelectPrice(String condition) {
		String[] temp;
		temp = condition.split("-");
		TItemDAO tid = new TItemDAO();
		List itemList = new ArrayList();
		if (temp.length == 1) {
			itemList = tid.findByIPriceBiger(temp[0]);
		} else if (temp.length == 2) {
			if (temp[0].equals("")) {
				itemList = tid.findByIPriceRange("0", temp[1]);
			} else {
				itemList = tid.findByIPriceRange(temp[0], temp[1]);
			}
		}
		if (itemList.size() == 0) {
			return "noresult";
		} else {
			session.setAttribute("item", itemList);
			return SUCCESS;
		}
	}

	private String SelectPlace(String condition) {
		TItemDAO tid = new TItemDAO();
		List itemList = new ArrayList();
		itemList = tid.findByIPlace(condition);
		if (itemList.size() == 0) {
			return "noresult";
		} else {
			session.setAttribute("item", itemList);
			return SUCCESS;
		}
	}

	private String SelectAppearance(String condition) {
		String[] temp;
		temp = condition.split("-");
		TItemDAO tid = new TItemDAO();
		List itemList = new ArrayList();
		if (temp.length == 1) {
			itemList = tid.findByIPriceBiger(temp[0]);
		} else if (temp.length == 2) {
			if (temp[0].equals("")) {
				itemList = tid.findByIPriceRange("0", temp[1]);
			} else {
				itemList = tid.findByIPriceRange(temp[0], temp[1]);
			}
		}
		if (itemList.size() == 0) {
			return "noresult";
		} else {
			session.setAttribute("item", itemList);
			return SUCCESS;
		}
	}

	// 在execute方法里变要执行判断
	public HashMap<String, String> matchPicture(List items) {
		HashMap<String, String> photoiteMap = new HashMap<String, String>();
		TItem item = new TItem();
//		TItemphotoDAO tipdDao = new TItemphotoDAO();
		TItemphoto itemphoto = new TItemphoto();
		for (int i = 0; i < items.size(); i++) {
			item = (TItem) items.get(i);
//			List itemphotos = tipdDao.findByPItem(item);
			List itemphotos = LocalPhotoCache.getLocalMsgCache().getPhotoByItem(item);
			if (itemphotos == null) {
				photoiteMap.put("", item.getIId());
			} else if (itemphotos.size() == 0) {
				photoiteMap.put("", item.getIId());
			} else {
				itemphoto = (TItemphoto) itemphotos.get(0);
				photoiteMap.put(item.getIId(), itemphoto.getPPath());
			}
		}
		return photoiteMap;

	}

	private String[] getTypeID(String class1, String class2) {
		List itemTypeList;
		TItemtypeDAO titd = new TItemtypeDAO();
		if (class1 != null && class2 == null) {
			itemTypeList = titd.findByTMainclass(class1);
		} else if (class2 != null) {
			itemTypeList = titd.findByTSecondclass(class2);
		} else {
			return null;
		}
		String[] typeId = new String[itemTypeList.size()];
		for (int i = 0; i < itemTypeList.size(); i++) {
			TItemtype tit = (TItemtype) itemTypeList.get(i);
			typeId[i] = tit.getTId();
		}
		return typeId;
	}

	private void init() {
		TItem[] items = new TItem[24];
		TItemDAO tiDao = new TItemDAO();
		TItem item = new TItem();
		ArrayList<TItem> itemstorage = new ArrayList<TItem>();
		// TODO itemIDs = null
		itemIDs = null;
		if (itemIDs != null && itemIDs.size() != 0) {
			for (int i = 0; i < itemIDs.size(); i++) {
				itemstorage.add((TItem) tiDao.findById(((TItem) itemIDs.get(i))
						.getIId()));
			}

		}
//		List item4display = tiDao.findAll();
		List item4display = LocalItemCache.getLocalItemCache().getAllItem();
		for (int i = 0; i < item4display.size(); i++) {
			item = (TItem) item4display.get(i);
			if (itemIDs != null && itemIDs.contains(item.getIId())) {
				continue;
			}
			itemstorage.add(item);
		}
		itemstorage=(ArrayList<TItem>) cutAddress(itemstorage);
		if (itemstorage.size() < 24) {
			for (int i = 0; i < itemstorage.size(); i++) {
				items[i] = itemstorage.get(i);
			}
		} else {
			for (int i = 0; i < 24; i++) {
				items[i] = itemstorage.get(i);
			}
		}
		HashMap<String, String> photoiteMap = new HashMap<String, String>();
		photoiteMap = matchPicture(itemstorage);
		int temppage = (int) Math.ceil(itemstorage.size() / (double) 24);
		session.removeAttribute("size");
		session.setAttribute("size", itemstorage.size());
		session.removeAttribute("page");
		session.setAttribute("page", temppage);
		session.removeAttribute("itemphoto");
		session.setAttribute("itemphoto", photoiteMap);
		session.removeAttribute("items");
		session.setAttribute("items", items);
		session.removeAttribute("currentpage");
		if (pagination == null || pagination.equals("0")) {
			session.setAttribute("currentpage", 1);
		} else {
			session.setAttribute("currentpage", Integer.parseInt(pagination));
		}
		setCookie(first_cate, second_cate);
	}
	private void changePage(int j, List<String> itemids,
			HashMap<String, TItem> tempmap) {
		TItem[] items = new TItem[24];
		TItemDAO tiDao = new TItemDAO();
		TItem item = new TItem();
		List<String> itemidlist = new ArrayList<String>();
		if (itemIDs != null && itemIDs.size() != 0 && itemids != null
				&& itemids.size() != 0) {
			for (int i = 0; i < itemIDs.size(); i++) {
				if (itemids.contains(((TItem) itemIDs.get(i)).getIId())) {
					itemidlist.add(((TItem) itemIDs.get(i)).getIId());
				}
			}
			for (int i = 0; i < itemids.size(); i++) {
				if (!itemidlist.contains(itemids.get(i))) {
					itemidlist.add(itemids.get(i));
				}
			}
		}

		ArrayList<TItem> itemstorage = new ArrayList<TItem>();
		for (int i = 0; i < itemidlist.size(); i++) {
			itemstorage.add(tempmap.get(itemidlist.get(i)));
		}
		itemstorage=(ArrayList<TItem>) cutAddress(itemstorage);
		// List item4display = tiDao.findAll();
		// for (int i = 0; i < item4display.size(); i++) {
		// item=(TItem)item4display.get(i);
		// if(itemIDs.contains(item.getIId())){
		// continue;
		// }
		// itemstorage.add(item);
		// }
		if (itemstorage.size() < 24) {
			for (int i = (j - 1) * 24; i < itemstorage.size(); i++) {
				items[i] = itemstorage.get(i);
			}
		} else {
			for (int i = 0; (j - 1) * 24 + i < j * 24; i++) {
				items[i] = itemstorage.get((j - 1) * 24 + i);
			}
		}
		HashMap<String, String> photoiteMap = new HashMap<String, String>();
		photoiteMap = matchPicture(itemstorage);
		session.removeAttribute("size");
		session.setAttribute("size", itemstorage.size());
		session.removeAttribute("page");
		session.setAttribute("page",
				(int) Math.ceil(itemstorage.size() / (double) 24));
		session.removeAttribute("itemphoto");
		session.setAttribute("itemphoto", photoiteMap);
		session.removeAttribute("items");
		session.setAttribute("items", items);
		session.removeAttribute("currentpage");
		if (pagination == null || pagination.equals("0")) {
			session.setAttribute("currentpage", 1);
		} else {
			session.setAttribute("currentpage", Integer.parseInt(pagination));
		}
		// TItem[] items=new TItem[24];
		// TItemDAO tiDao=new TItemDAO();
		// if(itemIDs.size()<=j*24){
		// for(int i=(j-1)*24;i<itemIDs.size();i++){
		// items[i]=(TItem)tiDao.findById((String)itemIDs.get(i));
		// }
		// }else{
		// for(int i=0;(j-1)*24+i<j*24;i++){
		// items[i]=(TItem)tiDao.findById((String)itemIDs.get((j-1)*24+i));
		// }
		// }
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getCompus() {
		return campus;
	}

	public void setCompus(String compus) {
		this.campus = compus;
	}

	public String getQuality() {
		return quality;
	}

	public void setQuality(String quality) {
		this.quality = quality;
	}

	public String getCampus() {
		return campus;
	}

	public void setCampus(String campus) {
		this.campus = campus;
	}

	public String getFirst_cate() {
		return first_cate;
	}

	public void setFirst_cate(String first_cate) {
		this.first_cate = first_cate;
	}

	public String getSecond_cate() {
		return second_cate;
	}

	public void setSecond_cate(String second_cate) {
		this.second_cate = second_cate;
	}

	public String getPagination() {
		return pagination;
	}

	public void setPagination(String pagination) {
		this.pagination = pagination;
	}

	public String getSearch_item() {
		return search_item;
	}

	public void setSearch_item(String search_item) {
		this.search_item = search_item;
	}

	public String getSort_by() {
		return sort_by;
	}

	public void setSort_by(String sort_by) {
		this.sort_by = sort_by;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}
	public List<TItem> cutAddress(List items) {
		ArrayList<TItem> itemList=new ArrayList<TItem>();
		if(items!=null){
		for(int i=0;i<items.size();i++){
			TItem item=new TItem();
			item=(TItem)items.get(i);
			if(item.getIPlace().length()>4){
			item.setIPlace(item.getIPlace().substring(0, 4));
			}
			itemList.add(item);	
		}
		return itemList;
		}else{
			return null;
		}
		
	}
	private void setCookie(String fisrt_cate,String second_cate) {
		boolean first=false;
		boolean second=false;
		for(int i=0;i<cookie.length;i++){
			if(cookie[i].getName().equals("first_cate")&& first_cate!=null){
				cookie[i].setValue(fisrt_cate);
				first=true;
			}
			if(cookie[i].getName().equals("scond_cate")&& second_cate!=null){
				cookie[i].setValue(second_cate);
				second=true;
			}
		}
		if(!first && first_cate!=null){
			Cookie tempCookie=new Cookie("first_cate", fisrt_cate);
			tempCookie.setMaxAge(24*60*60);
			response.addCookie(tempCookie);
		}else{
			Cookie tempCookie=new Cookie("first_cate", "0");
			tempCookie.setMaxAge(24*60*60);
			response.addCookie(tempCookie);
		}
		if(!second && second_cate!=null){
			Cookie tempCookie=new Cookie("second_cate", "0");
			tempCookie.setMaxAge(24*60*60);
			response.addCookie(tempCookie);
		
		}
		
	}

}
