package jmarket.util;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import jmarket.dao.TItemtypeDAO;
import jmarket.pojo.TItemtype;

public class Util {
	// key=token, value=email
	public static Map<String, String> mobileGlobalTokenToEmail = new HashMap<String,String>(1000);
	// key = email, value = token
	public static Map<String, String> mobileGlobalEmailToToken = new HashMap<String,String>(1000);
	
	// key=authCode value=email,验证码
	public static Map<String, String> mobileAuthCodeEmail = new HashMap<String,String>(1000);
	
	/**   
     * 生成文件名 : 当前时间 + 随机数 + 用户email   
     */    
    public static String createfilename(String email) {     
        StringBuilder result = new StringBuilder();     
        // 得到 本地的 当前时间    
        String now = System.currentTimeMillis()+"";     
        // 在 1000W 内随机生成一个数字     
        int rand = new Random().nextInt(9999999);     
        // 去掉 - 去掉 : 去掉 空格 后,返回    
        email = email.substring(0, email.lastIndexOf("@"));
        result.append(now).append("_").append(rand).append("_").append(email);     
        return result.toString();     
    }     
	
    public static String getUUID() {
    	UUID uid = UUID.randomUUID();
    	return uid.toString();
    }		
    
	public static String createRandomCharData(int length)
    {
        StringBuilder sb=new StringBuilder();
        Random rand=new Random();//随机用以下三个随机生成器
        Random randdata=new Random();
        int data=0;
        for(int i=0;i<length;i++)
        {
            int index=rand.nextInt(3);
            //目的是随机选择生成数字，大小写字母
            switch(index)
            {
            case 0:
                 data=randdata.nextInt(10);//仅仅会生成0~9
                 sb.append(data);
                break;
            case 1:
                data=randdata.nextInt(26)+65;//保证只会产生65~90之间的整数
                sb.append((char)data);
                break;
            case 2:
                data=randdata.nextInt(26)+97;//保证只会产生97~122之间的整数
                sb.append((char)data);
                break;
            }
        }
        return sb.toString();
    }
	
	/**
	 * check whether string is empty
	 * @param str
	 * @return true=empty  false=not empty
	 */
	public static boolean checkStrEmpty(String str) {
		if(str != null && !str.equals("")) return false;
		return true;
		
	}
	public static String[] getTypeID(String class1, String class2){
		List itemTypeList;
		TItemtypeDAO titd=new TItemtypeDAO();
		if(!checkStrEmpty(class1) && checkStrEmpty(class2)){
			itemTypeList=titd.findByTMainclass(class1);
//			System.out.println("class1:"+class1+" itemTypeList.size="+itemTypeList.size());
		}else if(!checkStrEmpty(class2)){
			itemTypeList = titd.findByTSecondclass(class2);
//			System.out.println("class2:"+class2+" itemTypeList.size="+itemTypeList.size());
		}else{
			return null;
		}
		
		String[] typeId = new String[itemTypeList.size()];
		for(int i=0;i < itemTypeList.size();i++){
			TItemtype tit = (TItemtype)itemTypeList.get(i);
//			if(tit.getTMainclass()!=null&&tit.getTSecondclass()!=null&&class1!=null&&class2==null){
//				continue;
//			}
			typeId[i] = tit.getTId();
//			System.out.println("getTypeID:"+tit.getTId());
		}
		return typeId;
	}
	
	public static String[] parseType(String class1, String class2) {
		String[] res = new String[2];
		String type1 = "";
		String type2 = "";
		if(class1!=null){
			Integer temp=Integer.parseInt(class1);
			  switch (temp){
			    case 0: type1 = "未分类"; break;
			    case 1: type1 = "二手车"; break;
			    case 2: type1 = "二手手机" ; break;
			    case 3: type1 = "PC/PAD"; break;
			    case 4: type1 = "二手家电"; break;
			    case 5: type1 = "服装包箱"; break;
			    case 6: type1 = "图书音像"; break;
			    case 7: type1 = "生活用品"; break;
			    case 8: type1 = "体育器材"; break;
			    case 9: type1 = "未分类"; break;
			    default:;break;
			  }
		}
		if(class2!=null){
			Integer temp=Integer.parseInt(class2);
			  switch (temp){
			    case 100: type2 = "普通自行车"; break;
			    case 101: type2 = "山地自行车";break;
			    case 102: type2 = "折叠自行车";break;
			    case 103: type2 = "迷你自行车";break;
			    case 104: type2 = "公路自行车";break;
			    case 105: type2 = "电动车";break;
			    case 106: type2 = "其他车";break;
			    case 200: type2 = "苹果";break;
			    case 201: type2 = "三星";break;
			    case 202: type2 = "小米";break;
			    case 203: type2 = "华为";break;
			    case 204: type2 = "HTC";break;
			    case 205: type2 = "诺基亚";break;
			    case 206: type2 = "魅族";break;
			    case 207: type2 = "索尼";break;
			    case 208: type2 = "中兴";break;
			    case 209: type2 = "LG";break;
			    case 210: type2 = "联想";break;
			    case 211: type2 = "酷派";break;
			    case 212: type2 = "其他手机";break;
			    case 300: type2 = "台式机";break;
			    case 301: type2 = "ThinkPad/IBM";break;
			    case 302: type2 = "苹果(Mac)";break;
			    case 303: type2 = "联想(Lenovo)";break;
			    case 304: type2 = "戴尔(DELL)";break;
			    case 305: type2 = "华硕(ASUS)";break;
			    case 306: type2 = "惠普(HP)";break;
			    case 307: type2 = "索尼(Sony)";break;
			    case 308: type2 = "三星(Samsung)";break;
			    case 309: type2 = "其他电脑";break;
			    case 310: type2 = "iPad";break;
			    case 311: type2 = "Surface";break;
			    case 312: type2 = "三星平板";break;
			    case 313: type2 = "小米平板";break;
			    case 314: type2 = "联想平板";break;
			    case 315: type2 = "其他平板";break;
			    case 400: type2 = "洗衣机";break;
			    case 401: type2 = "厨房电器";break;
			    case 402: type2 = "冰箱/冰柜";break;
			    case 403: type2 = "数码相机";break;
			    case 404: type2 = "其他家电";break;
			    case 500: type2 = "T恤";break;
			    case 501: type2 = "衬衫";break;
			    case 502: type2 = "外套";break;
			    case 503: type2 = "裤子";break;
			    case 504: type2 = "西装";break;
			    case 505: type2 = "裙子";break;
			    case 506: type2 = "休闲鞋";break;
			    case 507: type2 = "运动鞋";break;
			    case 508: type2 = "帆布鞋";break;
			    case 509: type2 = "高跟鞋";break;
			    case 510: type2 = "皮鞋";break;
			    case 511: type2 = "单肩包";break;
			    case 512: type2 = "双肩包";break;
			    case 513: type2 = "书包";break;
			    case 514: type2 = "钱包";break;
			    case 515: type2 = "电脑包";break;
			    case 516: type2 = "箱子";break;
			    case 517: type2 = "其他服饰";break;
			    case 600: type2 = "专业书籍";break;
			    case 601: type2 = "考试(GRE/托福/雅思)";break;
			    case 602: type2 = "小说/文学";break;
			    case 603: type2 = "工具书/辅导书";break;
			    case 604: type2 = "生活类书籍";break;
			    case 605: type2 = "学生文具";break;
			    case 606: type2 = "学习机";break;
			    case 607: type2 = "乐器";break;
			    case 608: type2 = "音响";break;
			    case 609: type2 = "耳机";break;
			    case 610: type2 = "U盘/硬盘";break;
			    case 611: type2 = "MP3/iPod/iWatch";break;
			    case 612: type2 = "其他图书";break;
			    case 700: type2 = "电灯";break;
			    case 701: type2 = "雨伞";break;
			    case 702: type2 = "梳子/镜子";break;
			    case 703: type2 = "家具";break;
			    case 704: type2 = "桌椅板凳";break;
			    case 705: type2 = "垫褥床铺";break;
			    case 706: type2 = "茶杯/水杯";break;
			    case 707: type2 = "布偶/娃娃";break;
			    case 708: type2 = "其他生活用品";break;
			    case 800: type2 = "健身器材";break;
			    case 801: type2 = "网球";break;
			    case 802: type2 = "足球";break;
			    case 803: type2 = "篮球";break;
			    case 804: type2 = "乒乓球";break;
			    case 805: type2 = "羽毛球";break;
			    case 806: type2 = "户外用品";break;
			    case 807: type2 = "游泳用品";break;
			    case 808: type2 = "其他体育用品";break;
			  }
		}
		res[0] = type1;
		res[1] = type2;
		return res;
	}
	//tid.getMembersByConditions(content.get("minPrice"), content.get("maxPrice"), content.get("place"), content.get("minState"), content.get("maxState"), content.get("typeID"),content.get("order"), text);
	public static HashMap<String, String> Parse(String price,String quality,String campus,String class1,String class2,String order){
		HashMap<String, String> parseMap=new HashMap<String, String>();
		String minPrice=null;
		String maxPrice=null;
		String place=null;
		String minState=null;
		String maxState=null;
		String typeID=null;
		String sqlorder=null;
		String type1=null;
		String type2=null;
		if(!checkStrEmpty(price)){
			Integer temp=Integer.parseInt(price);
		    switch (temp){
		      case 1:minPrice="0";maxPrice="10";break;
		      case 2:minPrice="10";maxPrice="50";break;
		      case 3:minPrice="50";maxPrice="100";break;
		      case 4:minPrice="100";maxPrice="200";break;
		      case 5:minPrice="200";maxPrice="400";break;
		      case 6:minPrice="400";maxPrice="600";break;
		      case 7:minPrice="600";maxPrice="800";break;
		      case 8:minPrice="800";maxPrice="1000";break;
		      case 9:minPrice="1000";maxPrice="2000";break;
		      case 10:minPrice="2000";break;
		      default:break;
		    }
		}
		if(!checkStrEmpty(quality)){
			Integer temp=Integer.parseInt(quality);
		    switch (temp){
		      case 1:minState="10";maxState=null;break;
		      case 2:minState="9";break;
		      case 3:minState="8";break;
		      case 4:minState="7";maxState="9";break;
		      case 5:minState="6";maxState="8";break;
		      case 6:minState="5";maxState="7";break;
		      case 7:maxState="5";break;
		      default: break;

		    }
		}
		if(!checkStrEmpty(campus)){
			Integer temp=Integer.parseInt(campus);
		    switch (temp) {
		      case 1:place="闵行校区";break;
		      case 2:place="徐汇校区";break;
		      default:break;
		    }
		}
		if(!checkStrEmpty(class1)){
			Integer temp=Integer.parseInt(class1);
			  switch (temp){
			    case 0: type1 = null; break;
			    case 1: type1 = "二手车"; break;
			    case 2: type1 = "二手手机" ; break;
			    case 3: type1 = "PC/PAD"; break;
			    case 4: type1 = "二手家电"; break;
			    case 5: type1 = "服装包箱"; break;
			    case 6: type1 = "图书音像"; break;
			    case 7: type1 = "生活用品"; break;
			    case 8: type1 = "体育器材"; break;
			    case 9: type1 = "未分类"; break;
			    default:;break;
			  }
		}
		if(!checkStrEmpty(class2)){
			Integer temp=Integer.parseInt(class2);
			  switch (temp){
			    case 100: type2 = "普通自行车"; break;
			    case 101: type2 = "山地自行车";break;
			    case 102: type2 = "折叠自行车";break;
			    case 103: type2 = "迷你自行车";break;
			    case 104: type2 = "公路自行车";break;
			    case 105: type2 = "电动车";break;
			    case 106: type2 = "其他车";break;
			    case 200: type2 = "苹果";break;
			    case 201: type2 = "三星";break;
			    case 202: type2 = "小米";break;
			    case 203: type2 = "华为";break;
			    case 204: type2 = "HTC";break;
			    case 205: type2 = "诺基亚";break;
			    case 206: type2 = "魅族";break;
			    case 207: type2 = "索尼";break;
			    case 208: type2 = "中兴";break;
			    case 209: type2 = "LG";break;
			    case 210: type2 = "联想";break;
			    case 211: type2 = "酷派";break;
			    case 212: type2 = "其他手机";break;
			    case 300: type2 = "台式机";break;
			    case 301: type2 = "ThinkPad/IBM";break;
			    case 302: type2 = "苹果(Mac)";break;
			    case 303: type2 = "联想(Lenovo)";break;
			    case 304: type2 = "戴尔(DELL)";break;
			    case 305: type2 = "华硕(ASUS)";break;
			    case 306: type2 = "惠普(HP)";break;
			    case 307: type2 = "索尼(Sony)";break;
			    case 308: type2 = "三星(Samsung)";break;
			    case 309: type2 = "其他电脑";break;
			    case 310: type2 = "iPad";break;
			    case 311: type2 = "Surface";break;
			    case 312: type2 = "三星平板";break;
			    case 313: type2 = "小米平板";break;
			    case 314: type2 = "联想平板";break;
			    case 315: type2 = "其他平板";break;
			    case 400: type2 = "洗衣机";break;
			    case 401: type2 = "厨房电器";break;
			    case 402: type2 = "冰箱/冰柜";break;
			    case 403: type2 = "数码相机";break;
			    case 404: type2 = "其他家电";break;
			    case 500: type2 = "T恤";break;
			    case 501: type2 = "衬衫";break;
			    case 502: type2 = "外套";break;
			    case 503: type2 = "裤子";break;
			    case 504: type2 = "西装";break;
			    case 505: type2 = "裙子";break;
			    case 506: type2 = "休闲鞋";break;
			    case 507: type2 = "运动鞋";break;
			    case 508: type2 = "帆布鞋";break;
			    case 509: type2 = "高跟鞋";break;
			    case 510: type2 = "皮鞋";break;
			    case 511: type2 = "单肩包";break;
			    case 512: type2 = "双肩包";break;
			    case 513: type2 = "书包";break;
			    case 514: type2 = "钱包";break;
			    case 515: type2 = "电脑包";break;
			    case 516: type2 = "箱子";break;
			    case 517: type2 = "其他服饰";break;
			    case 600: type2 = "专业书籍";break;
			    case 601: type2 = "考试(GRE/托福/雅思)";break;
			    case 602: type2 = "小说/文学";break;
			    case 603: type2 = "工具书/辅导书";break;
			    case 604: type2 = "生活类书籍";break;
			    case 605: type2 = "学生文具";break;
			    case 606: type2 = "学习机";break;
			    case 607: type2 = "乐器";break;
			    case 608: type2 = "音响";break;
			    case 609: type2 = "耳机";break;
			    case 610: type2 = "U盘/硬盘";break;
			    case 611: type2 = "MP3/iPod/iWatch";break;
			    case 612: type2 = "其他图书";break;
			    case 700: type2 = "电灯";break;
			    case 701: type2 = "雨伞";break;
			    case 702: type2 = "梳子/镜子";break;
			    case 703: type2 = "家具";break;
			    case 704: type2 = "桌椅板凳";break;
			    case 705: type2 = "垫褥床铺";break;
			    case 706: type2 = "茶杯/水杯";break;
			    case 707: type2 = "布偶/娃娃";break;
			    case 708: type2 = "其他生活用品";break;
			    case 800: type2 = "健身器材";break;
			    case 801: type2 = "网球";break;
			    case 802: type2 = "足球";break;
			    case 803: type2 = "篮球";break;
			    case 804: type2 = "乒乓球";break;
			    case 805: type2 = "羽毛球";break;
			    case 806: type2 = "户外用品";break;
			    case 807: type2 = "游泳用品";break;
			    case 808: type2 = "其他体育用品";break;
			  }
		}
		if(!checkStrEmpty(order)){
			Integer temp=Integer.parseInt(order);
		    switch (temp) {
		      case 1:sqlorder="price";break;
		      case 2:sqlorder="hot";break;
		      default:break;
		    }
		}
		String [] ids=getTypeID(type1,type2);
		parseMap.put("minPrice", minPrice);
		parseMap.put("maxPrice", maxPrice);
		parseMap.put("place", place);
		parseMap.put("minState", minState);
		parseMap.put("maxState", maxState);
		
		if(ids==null){
			parseMap.put("typeID", null);

		}else if(ids.length>0) {
			String idString="";
			for(int i=0;i<ids.length;i++){
				idString=idString+ids[i]+"@";
			}
			idString=idString.substring(0, idString.length()-1);
			parseMap.put("typeID",idString );
		}else{
			parseMap.put("typeID", null);
		}
		parseMap.put("order", sqlorder);	
		return parseMap;
	}
	
	public static HashMap<String, String> MParse(String price,String quality,String campus,String class1,String class2,String order){
		HashMap<String, String> parseMap=new HashMap<String, String>();
		String minPrice=null;
		String maxPrice=null;
		String place=null;
		String minState=null;
		String maxState=null;
		String typeID=null;
		String sqlorder=null;
		String type1=null;
		String type2=null;
		if(price!=null){
			Integer temp=Integer.parseInt(price);
		    switch (temp){
		      case 1:minPrice="0";maxPrice="10";break;
		      case 2:minPrice="10";maxPrice="50";break;
		      case 3:minPrice="50";maxPrice="100";break;
		      case 4:minPrice="100";maxPrice="200";break;
		      case 5:minPrice="200";maxPrice="400";break;
		      case 6:minPrice="400";maxPrice="600";break;
		      case 7:minPrice="600";maxPrice="800";break;
		      case 8:minPrice="800";maxPrice="1000";break;
		      case 9:minPrice="1000";maxPrice="2000";break;
		      case 10:minPrice="2000";break;
		      default:break;
		    }
		}
		if(quality!=null){
			Integer temp=Integer.parseInt(quality);
		    switch (temp){
		      case 1:minState="10";maxState=null;break;
		      case 2:minState="9";break;
		      case 3:minState="8";break;
		      case 4:minState="7";maxState="9";break;
		      case 5:minState="6";maxState="8";break;
		      case 6:minState="5";maxState="7";break;
		      case 7:maxState="5";break;
		      default: break;

		    }
		}
		if(campus!=null){
			Integer temp=Integer.parseInt(campus);
		    switch (temp) {
		      case 1:place="闵行校区";break;
		      case 2:place="徐汇校区";break;
		      default:break;
		    }
		}
		if(class1!=null){
			Integer temp=Integer.parseInt(class1);
			  switch (temp){
			    case 0: type1 = null; break;
			    case 1: type1 = "二手车"; break;
			    case 2: type1 = "二手手机" ; break;
			    case 3: type1 = "PC/PAD"; break;
			    case 4: type1 = "二手家电"; break;
			    case 5: type1 = "服装包箱"; break;
			    case 6: type1 = "图书音像"; break;
			    case 7: type1 = "生活用品"; break;
			    case 8: type1 = "体育器材"; break;
			    case 9: type1 = "未分类"; break;
			    default:;break;
			  }
		}
		if(class2!=null){
			Integer temp=Integer.parseInt(class2);
			  switch (temp){
			    case 100: type2 = "普通自行车"; break;
			    case 101: type2 = "山地自行车";break;
			    case 102: type2 = "折叠自行车";break;
			    case 103: type2 = "迷你自行车";break;
			    case 104: type2 = "公路自行车";break;
			    case 105: type2 = "电动车";break;
			    case 106: type2 = "其他车";break;
			    case 200: type2 = "苹果";break;
			    case 201: type2 = "三星";break;
			    case 202: type2 = "小米";break;
			    case 203: type2 = "华为";break;
			    case 204: type2 = "HTC";break;
			    case 205: type2 = "诺基亚";break;
			    case 206: type2 = "魅族";break;
			    case 207: type2 = "索尼";break;
			    case 208: type2 = "中兴";break;
			    case 209: type2 = "LG";break;
			    case 210: type2 = "联想";break;
			    case 211: type2 = "酷派";break;
			    case 212: type2 = "其他手机";break;
			    case 300: type2 = "台式机";break;
			    case 301: type2 = "ThinkPad/IBM";break;
			    case 302: type2 = "苹果(Mac)";break;
			    case 303: type2 = "联想(Lenovo)";break;
			    case 304: type2 = "戴尔(DELL)";break;
			    case 305: type2 = "华硕(ASUS)";break;
			    case 306: type2 = "惠普(HP)";break;
			    case 307: type2 = "索尼(Sony)";break;
			    case 308: type2 = "三星(Samsung)";break;
			    case 309: type2 = "其他电脑";break;
			    case 310: type2 = "iPad";break;
			    case 311: type2 = "Surface";break;
			    case 312: type2 = "三星平板";break;
			    case 313: type2 = "小米平板";break;
			    case 314: type2 = "联想平板";break;
			    case 315: type2 = "其他平板";break;
			    case 400: type2 = "洗衣机";break;
			    case 401: type2 = "厨房电器";break;
			    case 402: type2 = "冰箱/冰柜";break;
			    case 403: type2 = "数码相机";break;
			    case 404: type2 = "其他家电";break;
			    case 500: type2 = "T恤";break;
			    case 501: type2 = "衬衫";break;
			    case 502: type2 = "外套";break;
			    case 503: type2 = "裤子";break;
			    case 504: type2 = "西装";break;
			    case 505: type2 = "裙子";break;
			    case 506: type2 = "休闲鞋";break;
			    case 507: type2 = "运动鞋";break;
			    case 508: type2 = "帆布鞋";break;
			    case 509: type2 = "高跟鞋";break;
			    case 510: type2 = "皮鞋";break;
			    case 511: type2 = "单肩包";break;
			    case 512: type2 = "双肩包";break;
			    case 513: type2 = "书包";break;
			    case 514: type2 = "钱包";break;
			    case 515: type2 = "电脑包";break;
			    case 516: type2 = "箱子";break;
			    case 517: type2 = "其他服饰";break;
			    case 600: type2 = "专业书籍";break;
			    case 601: type2 = "考试(GRE/托福/雅思)";break;
			    case 602: type2 = "小说/文学";break;
			    case 603: type2 = "工具书/辅导书";break;
			    case 604: type2 = "生活类书籍";break;
			    case 605: type2 = "学生文具";break;
			    case 606: type2 = "学习机";break;
			    case 607: type2 = "乐器";break;
			    case 608: type2 = "音响";break;
			    case 609: type2 = "耳机";break;
			    case 610: type2 = "U盘/硬盘";break;
			    case 611: type2 = "MP3/iPod/iWatch";break;
			    case 612: type2 = "其他图书";break;
			    case 700: type2 = "电灯";break;
			    case 701: type2 = "雨伞";break;
			    case 702: type2 = "梳子/镜子";break;
			    case 703: type2 = "家具";break;
			    case 704: type2 = "桌椅板凳";break;
			    case 705: type2 = "垫褥床铺";break;
			    case 706: type2 = "茶杯/水杯";break;
			    case 707: type2 = "布偶/娃娃";break;
			    case 708: type2 = "其他生活用品";break;
			    case 800: type2 = "健身器材";break;
			    case 801: type2 = "网球";break;
			    case 802: type2 = "足球";break;
			    case 803: type2 = "篮球";break;
			    case 804: type2 = "乒乓球";break;
			    case 805: type2 = "羽毛球";break;
			    case 806: type2 = "户外用品";break;
			    case 807: type2 = "游泳用品";break;
			    case 808: type2 = "其他体育用品";break;
			  }
		}
		if(order!=null){
			Integer temp=Integer.parseInt(order);
		    switch (temp) {
		      case 0:sqlorder="date";break;
		      case 1:sqlorder="price";break;
		      default:break;
		    }
		}
		String [] ids=getTypeID(type1,type2);
		parseMap.put("minPrice", minPrice);
		parseMap.put("maxPrice", maxPrice);
		parseMap.put("place", place);
		parseMap.put("minState", minState);
		parseMap.put("maxState", maxState);
		
		if(ids==null){
			parseMap.put("typeID", null);
		}else if(ids.length>0) {
			String idString="";
			for(int i=0;i<ids.length;i++){
				idString=idString+ids[i]+"@";
			}
			idString=idString.substring(0, idString.length()-1);
			parseMap.put("typeID",idString );
		}else{
			parseMap.put("typeID", null);
		}
		parseMap.put("order", sqlorder);	
		return parseMap;
	}
}
