package jmarket.action;

import java.util.List;

import jmarket.dao.TUserDAO;
import jmarket.pojo.TUser;
import jmarket.util.LocalUserCache;
import jmarket.util.MailSenderInfo;
import jmarket.util.SimpleMailSender;
import jmarket.util.Util;

import com.opensymphony.xwork2.ActionSupport;

public class FindPasswordAction extends ActionSupport {
	private String email;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String execute() {
		// TODO Auto-generated method stub
//		TUserDAO tud=new TUserDAO();
//		List<TUser> users=tud.findByUEmail(email);
//		TUser user=new TUser();
//		if(users==null){
//			return ERROR;
//		}else{
//			if(users.size()!=1){
//				return ERROR;
//			}else{
//				user=users.get(0);
//			}
//		}
		TUser user = LocalUserCache.getLocalUserCache().getUserByEmail(email);
		if(user == null) {
			return ERROR;
		}
    	String title = "JMarket邮箱找回密码";
    	// authCode需要存到session里
    	String authCode = Util.createRandomCharData(5);
    	String content = "您的密码码是:" + user.getUPassword();
        MailSenderInfo mailInfo = new MailSenderInfo();    
        mailInfo.setValidate(true);   
        mailInfo.setSendToAddress(email);    
        mailInfo.setTitle(title);    
        mailInfo.setContent(content);    


        SimpleMailSender sms = new SimpleMailSender();   
        
        boolean flag = sms.sendTextMail(mailInfo);
        if(!flag) {
        	this.addActionError("发送验证邮件失败");
        	return ERROR;
        }
        return SUCCESS;
	}
}
