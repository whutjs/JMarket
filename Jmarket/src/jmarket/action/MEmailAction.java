package jmarket.action;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import jmarket.util.ConstantValue;
import jmarket.util.MailSenderInfo;
import jmarket.util.SimpleMailSender;
import jmarket.util.Util;

import com.opensymphony.xwork2.ActionSupport;

public class MEmailAction   extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private Map<String,Object> jsonMap = new HashMap<String, Object>();
	public Map<String, Object> getJsonMap() {
		return jsonMap;
	}
	public void setJsonMap(Map<String, Object> jsonMap) {
		this.jsonMap = jsonMap;
	}
	
    @Override
    public String execute() throws Exception {
    	HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		response.setHeader("Access-Control-Allow-Origin", "*"); //允许哪些url可以跨域请求到本域
		response.setHeader("Access-Control-Allow-Methods","POST"); //允许的请求方法，一般是GET,POST,PUT,DELETE,OPTIONS
		response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type"); //允许哪些请求头可以跨域

    	HttpServletRequest request = ServletActionContext.getRequest();
		StringBuffer jb = new StringBuffer();
	    String line = null;
		  try {
		    BufferedReader reader = request.getReader();
		    while ((line = reader.readLine()) != null)
		      jb.append(line);
		  } catch (Exception e) {
			  System.out.println(e.getMessage());
			  e.printStackTrace();
		  }
		  String postData = jb.toString();
		  if(postData == null || postData.isEmpty()) {
			  	jsonMap.clear();
				jsonMap.put(ConstantValue.FLAG_KEY, ConstantValue.FLAG_FAIL_VALUE);
				jsonMap.put(ConstantValue.MSG_KEY, ConstantValue.MSG_BADREQUEST);
				return SUCCESS;
		  }
		  JSONObject jsonObject = null;
		  try{
			  jsonObject = JSONObject.fromObject(postData);
		  }catch (JSONException e) {
			  System.out.println(e.getMessage());
			  e.printStackTrace();
			  jsonMap.clear();
			  jsonMap.put(ConstantValue.FLAG_KEY, ConstantValue.FLAG_FAIL_VALUE);
			  jsonMap.put(ConstantValue.MSG_KEY, ConstantValue.MSG_BADREQUEST);
			  return SUCCESS;
		  }
//		  String userName = jsonObject.optString(ConstantValue.USERNAME_KEY, "");
		  String email = jsonObject.optString(ConstantValue.EMAIL_KEY, "");
		  if(email.equals("")) {
			  jsonMap.clear();
			  jsonMap.put(ConstantValue.FLAG_KEY, ConstantValue.FLAG_FAIL_VALUE);
			  jsonMap.put(ConstantValue.MSG_KEY, ConstantValue.MSG_BADREQUEST);
			  return SUCCESS;
		  }
    	String title = "JMarket邮箱验证";
    	// authCode需要存到session里
    	String authCode = Util.createRandomCharData(5);
    	String content = "您的验证码是:" + authCode;
        /*������*/
        MailSenderInfo mailInfo = new MailSenderInfo();    
        mailInfo.setValidate(true);   
        mailInfo.setSendToAddress(email);    
        mailInfo.setTitle(title);    
        mailInfo.setContent(content);    

        //�����ʼ�
        SimpleMailSender sms = new SimpleMailSender();   
        
        boolean flag = sms.sendTextMail(mailInfo);
        if(flag) {
        	jsonMap.clear();
			jsonMap.put(ConstantValue.FLAG_KEY, ConstantValue.FLAG_SUCC_VALUE);
			jsonMap.put(ConstantValue.AUTH_CODE_KEY, authCode);
			System.out.println("authCode="+authCode);
			Util.mobileAuthCodeEmail.put(authCode, email);
			  
        }else{
        	jsonMap.clear();
			jsonMap.put(ConstantValue.FLAG_KEY, ConstantValue.FLAG_SUCC_VALUE);
			jsonMap.put(ConstantValue.MSG_KEY, ConstantValue.MSG_SEND_EMAIL_FAIL);
        }
        return SUCCESS;
    }
}
