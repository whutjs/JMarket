package jmarket.util;

import java.util.Properties;

/**
 * �����ʼ���Ҫ����Ϣ
 * @author Jenson
 *
 */
public class MailSenderInfo {

		 // �����ʼ��ķ�������IP�Ͷ˿�    
	    private String mailServerHost = "smtp.exmail.qq.com";    
	    private String mailServerPort = "465";    
	    // �ʼ������ߵĵ�ַ    
	    private String myEmailAddress = "jmarket@xiaodevil.me"; 
	    //�ʼ������ߵ��ǳ�
	    private String myName = "Jmarket";   
	    // �ʼ������ߵĵ�ַ    
	    private String sendToAddress = "";    
	    // ��½�ʼ����ͷ��������û��������    
	    private String myEmailName = "jmarket@xiaodevil.me";    
	    private String myEmailpass = "Jmarket2015";    
	    // �Ƿ���Ҫ�����֤    
	    private boolean validate = false;    
	    // �ʼ�����    
	    private String Title;    
	    // �ʼ����ı�����    
	    private String content;    
	    // �ʼ��������ļ���    
	    private String[] attachFileNames;      
	    /**   
	      * ����ʼ��Ự����   
	      */    
	    public Properties getProperties(){    
	      Properties p = new Properties();    
	      p.put("mail.smtp.host", this.mailServerHost);    
	      p.put("mail.smtp.port", this.mailServerPort);    
	      p.put("mail.smtp.auth", validate ? "true" : "false");   
	      p.put("mail.smtp.socketFactory.port", "465");
	      p.put("mail.smtp.socketFactory.class",
	              "javax.net.ssl.SSLSocketFactory");
	      return p;    
	    }    
	    public String getMailServerHost() {    
	      return mailServerHost;    
	    }    
	    public void setMailServerHost(String mailServerHost) {    
	      this.mailServerHost = mailServerHost;    
	    }   
	    public String getMailServerPort() {    
	      return mailServerPort;    
	    }   
	    public void setMailServerPort(String mailServerPort) {    
	      this.mailServerPort = mailServerPort;    
	    }   
	    public boolean isValidate() {    
	      return validate;    
	    }   
	    public void setValidate(boolean validate) {    
	      this.validate = validate;    
	    }   
	    public String[] getAttachFileNames() {    
	      return attachFileNames;    
	    }   
	    public void setAttachFileNames(String[] fileNames) {    
	      this.attachFileNames = fileNames;    
	    }
	    
	    public String getMyEmailAddress() {
	        return myEmailAddress;
	    }
	    public void setMyEmailAddress(String myEmailAddress) {
	        this.myEmailAddress = myEmailAddress;
	    }
	    public String getSendToAddress() {
	        return sendToAddress;
	    }
	    public void setSendToAddress(String sendToAddress) {
	        this.sendToAddress = sendToAddress;
	    }
	    public String getMyEmailName() {
	        return myEmailName;
	    }
	    public void setMyEmailName(String myEmailName) {
	        this.myEmailName = myEmailName;
	    }
	    public String getMyEmailpass() {
	        return myEmailpass;
	    }
	    public void setMyEmailpass(String myEmailpass) {
	        this.myEmailpass = myEmailpass;
	    }
	    public String getTitle() {
	        return Title;
	    }
	    public void setTitle(String title) {
	        Title = title;
	    }
	    public String getContent() {    
	      return content;    
	    }   
	    public void setContent(String textContent) {    
	      this.content = textContent;    
	    }
	    public String getMyName() {
	        return myName;
	    }
	    public void setMyName(String myName) {
	        this.myName = myName;
	    }    
}
