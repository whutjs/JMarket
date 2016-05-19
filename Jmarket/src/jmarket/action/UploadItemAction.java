package jmarket.action;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.management.loading.PrivateClassLoader;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jmarket.dao.TItemDAO;
import jmarket.dao.TItemphotoDAO;
import jmarket.pojo.TItem;
import jmarket.pojo.TItemphoto;
import jmarket.pojo.TUser;
import jmarket.util.ConstantValue;
import jmarket.util.Util;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UploadItemAction extends ActionSupport implements ModelDriven<TItem>,ServletRequestAware {
	private TItem item=new TItem();
	private String type1;
	private String type2;
	private String state;
	private String campus;
	private String area;
	private String building;
	public String getCampus() {
		return campus;
	}
	public void setCampus(String campus) {
		this.campus = campus;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getBuilding() {
		return building;
	}
	public void setBuilding(String building) {
		this.building = building;
	}
	public String getType1() {
		return type1;
	}
	public void setType1(String type1) {
		this.type1 = type1;
	}
	public String getType2() {
		return type2;
	}
	public void setType2(String type2) {
		this.type2 = type2;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	private HttpSession session;
	private String delete;

	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.session=request.getSession();
	}
	public String execute(){
		if(session.getAttribute("user")==null){
			TUser user=(TUser)session.getAttribute("user");
			return "nologin";
		}else if(item==null){
			return ERROR;
		}else{
			try {
			String[] deletes;
			ArrayList<String> deleteArrayList=new ArrayList<String>();
			if(Util.checkStrEmpty(delete) && delete.length()>0){
		
			delete=delete.substring(1);
			deletes=delete.split("@");		
			for(int i=0;i<deletes.length;i++){
				deleteArrayList.add(deletes[i]);
			}
			}
			TUser user=(TUser)session.getAttribute("user");
			System.out.println(user.getUId());
			TItemDAO tiDao=new TItemDAO();
			TItemphoto itemphoto=new TItemphoto();
			TItemphotoDAO tipd=new TItemphotoDAO();
			Calendar calendar=Calendar.getInstance();
			Date date=new Date();
			calendar.setTime(date);
			item.setTUser(user);
			item.setIId(Util.createfilename(user.getUId()+"@"));
			Timestamp ts=new Timestamp(System.currentTimeMillis());
			item.setIDate(ts);
			item.setIFlag("0");
			item.setIPlace(parseplace(campus, area, building));
			HashMap<String, String> content=Util.Parse(null, null, null, type1, type2, null);
			String[] temp=content.get("typeID").split("@");
			//item.setIType(temp[0]);
			if(temp==null){
				item.setIType("0");
			}else{
				item.setIType(temp[0]);
			}
			if(state!=null&&(!state.equals("0"))){
			item.setIState((float) (11-Integer.parseInt(state)));
			}
			tiDao.save(item);
			HashMap<String, String> itemimage=(HashMap<String, String>)session.getAttribute("itemimage");
			if(itemimage==null){
				itemphoto.setPId(Util.getUUID());
				itemphoto.setTItem(item);
				itemphoto.setPPath("images/noPic.png");		
				tipd.save(itemphoto);
			}else{
				Iterator iter = itemimage.entrySet().iterator();
				while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				itemphoto.setPId(Util.getUUID());
				itemphoto.setTItem(item);
				Object key = entry.getKey();
				Object val = entry.getValue();
				if(deleteArrayList.contains((String)key)){
					File file=new File(ServletActionContext.getServletContext().getRealPath(ConstantValue.ITEM_IMG_PATH)+(String)val);//may be wrong
					if(!file.delete()){
						//return ERROR;
						session.removeAttribute("itemimage");
						return SUCCESS;
					}
					continue;
				}
				itemphoto.setPPath((String)val);
				tipd.save(itemphoto);
				}
			}
			session.removeAttribute("itemimage");
			return SUCCESS;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return ERROR;
			}
		}

	}
	@Override
	public TItem getModel() {
		// TODO Auto-generated method stub
		return item;
	}
	public String getDelete() {
		return delete;
	}
	public void setDelete(String delete) {
		this.delete = delete;
	}
	public String parseplace(String campus,String area,String building){
		String place="";
		if(campus!=null&&(!campus.equals("0"))){
			if(campus.equals("2")){
				place=place+"闵行校区";
			}
			if(campus.equals("1")){
				place=place+"徐汇校区";
			}
		}
		if(area!=null&&(!area.equals("-请选择-"))){
			place =place+area;
		}
		if(building!=null&&(!building.equals("-请选择-"))){
			place =place+building;
		}
		return place;
	}

}
