package jmarket.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import jmarket.dao.TUserDAO;
import jmarket.pojo.TUser;
import jmarket.util.ConstantValue;
import jmarket.util.Util;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 上传头像
 * @author Jenson
 *
 */
public class MUploadAvatarAction  extends ActionSupport {
	private static final long serialVersionUID = 1L;

	private Map<String,Object> jsonMap = new HashMap<String, Object>();
	public Map<String, Object> getJsonMap() {
		return jsonMap;
	}
	public void setJsonMap(Map<String, Object> jsonMap) {
		this.jsonMap = jsonMap;
	}
	private File file;
	private String fileContentType;
	private String filename = "tmp.jpg";
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	private String Token;
	
	public String getToken() {
		return Token;
	}
	public void setToken(String token) {
		Token = token;
	}
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}
	
	 @Override
	public String execute() throws Exception {
		 HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/json");
			response.setHeader("Access-Control-Allow-Origin", "*"); //允许哪些url可以跨域请求到本域
			response.setHeader("Access-Control-Allow-Methods","POST"); //允许的请求方法，一般是GET,POST,PUT,DELETE,OPTIONS
			response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type"); //允许哪些请求头可以跨域
			if(Util.checkStrEmpty(Token)) {
				Token = ServletActionContext.getRequest().getParameter("token");
			}
		if(Util.checkStrEmpty(Token)) {		
			 jsonMap.clear();
			 jsonMap.put(ConstantValue.FLAG_KEY, ConstantValue.FLAG_FAIL_VALUE);
			 jsonMap.put(ConstantValue.MSG_KEY, "token is null");
			 return SUCCESS;
		}
		 if(Util.mobileGlobalTokenToEmail.containsKey(Token) == false) {
			 // invalid access 
			 jsonMap.clear();
			 jsonMap.put(ConstantValue.FLAG_KEY, ConstantValue.FLAG_FAIL_VALUE);
			 jsonMap.put(ConstantValue.MSG_KEY, ConstantValue.MSG_INVALID_TOKEN);
			 return SUCCESS;
		 }
		 String userEmail = Util.mobileGlobalTokenToEmail.get(Token);
		 TUserDAO userDao = new TUserDAO();
		 TUser user = (TUser) (userDao.findByUEmail(userEmail).get(0));
		 String returnImagePath = "";
		try {
			FileInputStream fis = new FileInputStream(file);
			String photosDir = ServletActionContext.getServletContext().getRealPath(ConstantValue.USER_AVATAR_PATH);
			File dir = new File(photosDir);
			if(!dir.exists()) {
				dir.mkdir();
			}		
			String newFileName = user.getUName() + filename.substring(filename.lastIndexOf(".")).toLowerCase();
			File fs = new File(photosDir, newFileName);
			if(fs.exists()) {
				// delete old file
				fs.delete();
			}
			FileOutputStream fos = new FileOutputStream(fs);
			int len = 0;
			byte[] buffer = new byte[1024];

			while ((len = fis.read(buffer)) != -1) {
				fos.write(buffer, 0, len);
			}
			fos.flush();
			fos.close();
			fis.close();
			returnImagePath = ConstantValue.USER_AVATAR_PATH +"/" + newFileName;
			user.setUImage(returnImagePath);
			userDao.save(user);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		jsonMap.clear();
		jsonMap.put(ConstantValue.FLAG_KEY, ConstantValue.FLAG_SUCC_VALUE);
		jsonMap.put(ConstantValue.ITEM_IMG_KEY, returnImagePath);
		return SUCCESS;
	}
}
