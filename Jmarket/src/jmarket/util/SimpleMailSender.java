package jmarket.util;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SimpleMailSender {
	
	/**   
	  * ���ı���ʽ�����ʼ�   
	  * @param mailInfo ���͵��ʼ�����Ϣ   
	  */    
	    public boolean sendTextMail(MailSenderInfo mailInfo) {    
	      System.out.println("sendTextMail");
	      MyAuthenticator authenticator = null;    
	      Properties pro = mailInfo.getProperties();   
	      if (mailInfo.isValidate()) {    
	      // �����Ҫ�����֤���򴴽�һ��������֤��    
	        authenticator = new MyAuthenticator(mailInfo.getMyEmailName(), mailInfo.getMyEmailpass());    
	      }   
	      // ����ʼ��Ự���Ժ�������֤������һ�������ʼ���session    
	      Session sendMailSession = Session.getDefaultInstance(pro,authenticator);    
	      try {    
		      // ���session����һ���ʼ���Ϣ    
		      Message mailMessage = new MimeMessage(sendMailSession);    
		      // ���������ߵ��ʼ���ַ�ͷ������ǳ�    
		      Address from = new InternetAddress(mailInfo.getMyEmailAddress(), mailInfo.getMyName());    
		      // �����ʼ���Ϣ�ķ�����    
		      mailMessage.setFrom(from);
		      // �����ʼ��Ľ����ߵ�ַ�������õ��ʼ���Ϣ��    
		      Address to = new InternetAddress(mailInfo.getSendToAddress());
		      mailMessage.setRecipient(Message.RecipientType.TO,to);    
		      // �����ʼ���Ϣ������    
		      mailMessage.setSubject(mailInfo.getTitle());    
		      // �����ʼ���Ϣ���͵�ʱ��    
		      mailMessage.setSentDate(new Date());    
		      // �����ʼ���Ϣ����Ҫ����    
		      String mailContent = mailInfo.getContent();    
		      mailMessage.setText(mailContent);
		      Transport.send(mailMessage);
		      System.out.println("Done");
		      return true;    
	      } catch (MessagingException ex) {    
	          ex.printStackTrace();    
	      } catch (UnsupportedEncodingException e) {
	        e.printStackTrace();
	    }    
	      return false;    
	    }    
	       	  
}
