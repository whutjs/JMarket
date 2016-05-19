package jmarket.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jmarket.dao.TUserDAO;
import jmarket.pojo.TUser;
import jmarket.util.LocalUserCache;
import jmarket.util.MailSenderInfo;
import jmarket.util.SimpleMailSender;
import jmarket.util.Util;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class RegisterAction extends ActionSupport implements ModelDriven<TUser>,ServletRequestAware {

	private static final long serialVersionUID = 111L;
	private TUser user=new TUser();
	private HttpSession session;
	@SuppressWarnings("unused")
	private String authCode = Util.createRandomCharData(5);
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.session=request.getSession();
	}
	public TUser getModel() {
		// TODO Auto-generated method stub
		return user;
	} 
	public String execute(){
		
//		TUserDAO tud=new TUserDAO();
//		@SuppressWarnings("rawtypes")
//		List userList=new ArrayList();
//		if(user.getUEmail()!=null)
//		{
//		userList=tud.findByUEmail(user.getUEmail());
//		if(userList.size()==1){			
//			this.addActionError("该邮箱已被注册");
//			return ERROR;
//		}
//		}
		if(user.getUEmail()!=null){
			TUser tuser = LocalUserCache.getLocalUserCache().getUserByEmail(user.getUEmail());
			if(tuser != null) {
				this.addActionError("该邮箱已被注册");
				return ERROR;
			}
		}
    	String title = "JMarket邮箱验证";
    	// authCode需要存到session里
    	String authCode = Util.createRandomCharData(5);
    	String content = "您的验证码是:" + authCode;
        MailSenderInfo mailInfo = new MailSenderInfo();    
        mailInfo.setValidate(true);   
        mailInfo.setSendToAddress(user.getUEmail());    
        mailInfo.setTitle(title);    
        mailInfo.setContent(content);    


        SimpleMailSender sms = new SimpleMailSender();   
        
        boolean flag = sms.sendTextMail(mailInfo);
        if(!flag) {
        	this.addActionError("发送验证邮件失败");
        	return ERROR;
        }
        
		String[] str=user.getUEmail().split("@");
		user.setUId("U"+str[0]);
		user.setUName("JMU"+str[0]);
		user.setUFlag("1");
		user.setUType("1");
		user.setUImage("images/user.png");
		Timestamp ts=new Timestamp(System.currentTimeMillis());
		user.setURegtime(ts);
		session.removeAttribute("user");
		session.setAttribute("user", user);
		session.removeAttribute("Code");
		session.setAttribute("Code",authCode);
		return SUCCESS;
	}


}
