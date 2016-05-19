package jmarket.util;

public class ConstantValue {
	
	public static String FLAG_KEY = "flag";
	public static String FLAG_SUCC_VALUE = "success";
	public static String FLAG_FAIL_VALUE = "fail";
	
	public static String MSG_KEY = "msg";
	public static String MSG_BADREQUEST = "bad request";
	public static String MSG_USEREXISTS = "user already exists";
	public static String MSG_USER_NOTEXISTS = "user not exists";
	public static String MSG_PSW_INCORRECT = "password incorrect";
	public static String MSG_SEND_EMAIL_FAIL = "fail to send email";
	public static String MSG_INVALID_TOKEN = "invalid token";
	
	public static String TOKEN_KEY = "token";
	public static String USER_INFO_KEY = "user_info";
	
	// for mobile email
	public static String AUTH_CODE_KEY = "auth_code";
	
	// for register
	public static String USERNAME_KEY = "username";
	public static String PSW_KEY = "password";
	public static String EMAIL_KEY = "email";
	
	
	// for user_info
	public static String QQ_KEY = "qq";
	public static String NEW_PSW_KEY = "new_password";
	public static String WECHAT_KEY = "wechat";
	public static String PHONE_KEY = "phone";
	public static String UID_KEY = "user_id";
	public static String REGTIME_KEY = "register_time";
	public static String USR_IMAGE_KEY = "image";
	
	// 上传照片之后返回给用户的图片路径
	public static String ITEM_IMG_KEY = "image_path";
	
	// 商品图片路径
//	public static String ITEM_IMG_PATH = "/var/lib/tomcat7/webapps/jmarket/item_img";
	public static String ITEM_IMG_PATH = "item_img";
	
	public static String USER_AVATAR_PATH = "avatar";
	
	/* 发布商品信息需要的JSON_KEY */
	public static String FIRST_TYPE_KEY = "first_type";
	public static String SECOND_TYPE_KEY = "second_type";
	public static String QUALITY_KEY = "quality";
	public static String PRICE_KEY = "price";
	public static String POST_DATE_KEY = "postDate";
	public static String TITLE_KEY = "title";
	public static String DETAIL_KEY = "detail";
	public static String LOCATION_KEY = "location";
	public static String IMGSRC_KEY = "imgSrc";
	public static String IMGSIZE_KEY = "imgSize";
	
	/* 客户端获取item 
	 */
	public static String ITEMS_KEY = "items";
	public static String PAGE_KEY = "page";
	public static String PAGE_SIZE_KEY = "page_size";
	public static String MIN_PRICE_KEY = "min_price";
	public static String MAX_PRICE_KEY = "max_price";
	public static String MIN_STATE_KEY = "min_state";
	public static String MAXN_STATE_KEY = "max_state";
	public static String PLACE_KEY = "place";
	public static String ORDER_KEY = "order";
	
	public static String MSG_MISS_FIRST_TYPE = "first type missing";
	public static String MSG_MISS_PAGE = "param 'page' missing";
	public static String MSG_MISS_PAGE_SIZE = "param 'page_size' missing";
	
	/* 客户端搜索 */
	public static String KEYWORD_KEY = "keyword";
	
	/* 客户端评论 */
	public static String MSG_INFO_KEY = "message";
	public static String ITEM_ID_KEY = "item_id";
	public static String CONTENT_KEY = "content";
	public static String PARENT_MSG_ID_KEY = "parent_msg_id";
}
