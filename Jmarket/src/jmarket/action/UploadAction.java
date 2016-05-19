package jmarket.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jmarket.pojo.TUser;
import jmarket.util.ConstantValue;
import jmarket.util.Util;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;

public class UploadAction extends ActionSupport implements ServletRequestAware {

	private File file = null; // 和在JS中指定的fileObjName的值相同
	private String fileFileName = null; // [fileName]FileName 获得上传文件的名称
	private String fileContentType = null;// [fileName]ContentType 获得上传文件的类型
	private HttpSession session;
	private Map<String, Object> jsonMap = new HashMap<String, Object>();

	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		session = request.getSession();
	}

	@SuppressWarnings("unchecked")
	public String execute() {
		FileOutputStream fos = null;
		FileInputStream fis = null;
		TUser user=(TUser)session.getAttribute("user");
		if(user==null){
			jsonMap.put("message", 3);
			return SUCCESS;
		}
		HashMap<String, String> itemimage=new HashMap<String, String>();
		try {
			if(session.getAttribute("itemimage")==null){
				session.setAttribute("itemimage", itemimage);
			}else{
				itemimage=(HashMap<String, String>)session.getAttribute("itemimage");
			}
			String path = ServletActionContext.getServletContext().getRealPath(ConstantValue.ITEM_IMG_PATH);
			String newFileName = Util.createfilename(user.getUEmail()) + fileFileName.substring(fileFileName.lastIndexOf(".")).toLowerCase();
			File outFile =new File(path, newFileName);
			fos = new FileOutputStream(outFile); // 输出流
			fis = new FileInputStream(file); // 输出流
			jsonMap.put("path", ConstantValue.ITEM_IMG_PATH+"/"+newFileName);
			jsonMap.put("name", newFileName);
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = fis.read(buffer)) != -1) {
				fos.write(buffer);
			}
			jsonMap.put("message", 0);
			itemimage.put(newFileName,ConstantValue.ITEM_IMG_PATH+"/"+newFileName);
			session.removeAttribute("itemimage");
			session.setAttribute("itemimage", itemimage);
			fos.flush();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			jsonMap.put("message", 1);
		} finally {
			try {
				fos.close();
				fis.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				jsonMap.remove("message");
				jsonMap.put("message", 2);
			} finally {
				fos = null;
				fis = null;
			}
		}
		return SUCCESS; // 这里不需要返回任何东西
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public Map<String, Object> getJsonMap() {
		return jsonMap;
	}

	public void setJsonMap(Map<String, Object> jsonMap) {
		this.jsonMap = jsonMap;
	}

}
