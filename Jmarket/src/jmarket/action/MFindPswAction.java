package jmarket.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import jmarket.dao.TUserDAO;
import jmarket.pojo.TUser;
import jmarket.util.ConstantValue;
import jmarket.util.MailSenderInfo;
import jmarket.util.SimpleMailSender;
import jmarket.util.Util;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class MFindPswAction   extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private Map<String,Object> jsonMap = new HashMap<String, Object>();
	public Map<String, Object> getJsonMap() {
		return jsonMap;
	}
	public void setJsonMap(Map<String, Object> jsonMap) {
		this.jsonMap = jsonMap;
	}
	
	private String email = "";
	@Override
    public String execute() throws Exception {
    	HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		response.setHeader("Access-Control-Allow-Origin", "*"); //允许哪些url可以跨域请求到本域
		response.setHeader("Access-Control-Allow-Methods","POST"); //允许的请求方法，一般是GET,POST,PUT,DELETE,OPTIONS
		response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type"); //允许哪些请求头可以跨域
		
		email = ServletActionContext.getRequest().getParameter(ConstantValue.EMAIL_KEY);
		if(Util.checkStrEmpty(email)) {
			 jsonMap.clear();
			 jsonMap.put(ConstantValue.FLAG_KEY, ConstantValue.FLAG_FAIL_VALUE);
			 jsonMap.put(ConstantValue.MSG_KEY, "email is null");
			 return SUCCESS;
		}
		TUserDAO usrDao = new TUserDAO();
		List result = usrDao.findByUEmail(email);
		if(result.size() <= 0) {
			if(Util.checkStrEmpty(email)) {
				 jsonMap.clear();
				 jsonMap.put(ConstantValue.FLAG_KEY, ConstantValue.FLAG_FAIL_VALUE);
				 jsonMap.put(ConstantValue.MSG_KEY, "email not exits");
				 return SUCCESS;
			}
		}
		TUser usr = (TUser) result.get(0);
		String title = "JMarket找回密码";
    	String psw = usr.getUPassword();
    	String content = "您的密码是:" + psw;
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
			  
        }else{
        	jsonMap.clear();
			jsonMap.put(ConstantValue.FLAG_KEY, ConstantValue.FLAG_SUCC_VALUE);
			jsonMap.put(ConstantValue.MSG_KEY, ConstantValue.MSG_SEND_EMAIL_FAIL);
        }
        usrDao.closeSession();
        return SUCCESS;
	}
}
