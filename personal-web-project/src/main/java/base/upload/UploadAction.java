package base.upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
/**
 * struts2的文件上传
 * 
 * @author Liubao
 * @2014年11月25日
 *
 */
public class UploadAction /*extends ActionSupport*/{
	private File resource;
	private String resourceContentType;//文件的类型
	private String resourceFileName;//文件的名称
	
	private InputStream inputStream;

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getResourceContentType() {
		return resourceContentType;
	}

	public void setResourceContentType(String resourceContentType) {
		this.resourceContentType = resourceContentType;
	}

	public String getResourceFileName() {
		return resourceFileName;
	}

	public void setResourceFileName(String resourceFileName) {
		this.resourceFileName = resourceFileName;
	}

	public File getResource() {
		return resource;
	}

	public void setResource(File resource) {
		this.resource = resource;
	}
	
	public String upload(){
		System.out.println(this.resourceContentType);
		System.out.println(this.resourceFileName);
		String path = UploadUtils.saveUploadFile(resource,this.resourceFileName);
		/**
		 * 把上传的文件的名称保存在session中
		 * 上传的文件的路径保存在session中
		 */
		ServletActionContext.getRequest().getSession().setAttribute("fileName", this.resourceFileName);
		ServletActionContext.getRequest().getSession().setAttribute("filePath", path+this.resourceFileName);
		return "success";
	}
	
	public String download() throws Exception{
		/**
		 * 要下载的文件的名称
		 */
		String fileName = ServletActionContext.getRequest().getSession().getAttribute("fileName").toString();
		/**
		 * 要下载的文件的路径
		 */
		String filePath = ServletActionContext.getRequest().getSession().getAttribute("filePath").toString();
		//这个编码对中文传输很重要
		fileName = URLEncoder.encode(fileName, "utf-8");
		
		ActionContext.getContext().put("fileName", fileName);
		this.inputStream = new FileInputStream(new File(filePath));
		return "download";
	}
}
