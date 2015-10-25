package base.controller.upload;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;



/**
* 
* @author geloin
* @date 2012-5-5 上午11:56:35
*/
@Controller
@RequestMapping(value = "file")
public class FileOperateController {
	/**
	 * 到上传文件的位置
	 * 
	 * @author geloin
	 * @date 2012-3-29 下午4:01:31
	 * @return
	 */
	@RequestMapping(value = "to_upload")
	public ModelAndView toUpload() {
		return new ModelAndView("upload");
	}

	/**
	 * 上传文件
	 * 
	 * @author geloin
	 * @date 2012-3-29 下午4:01:41
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "upload")
	public ModelAndView upload(HttpServletRequest request) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		// 别名
		String[] alaises = ServletRequestUtils.getStringParameters(request,
				"alais");

		String[] params = new String[] { "alais" };
		Map<String, Object[]> values = new HashMap<String, Object[]>();
		values.put("alais", alaises);

		List<Map<String, Object>> result = FileOperateUtil.upload(request,
				params, values);

		map.put("result", result);
		map.put("alais", alaises);
		

		return new ModelAndView("list", map);
	}

	/**
	 * 下载
	 * 
	 * @author geloin
	 * @date 2012-3-29 下午5:24:14
	 * @param attachment
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "download")
	public ModelAndView download(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String storeName = request.getParameter("storeName");
		String realName = request.getParameter("realName");
		String contentType = "application/octet-stream";

		FileOperateUtil.download(request, response, storeName, contentType,
				realName);

		return null;
	}
	@RequestMapping(value = "downloadZip")
	public ModelAndView downloadZip(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String storeName = request.getParameter("storeName");
		String realName = request.getParameter("realName");
		 realName =realName+ ".zip";
		String contentType = "application/octet-stream";

		FileOperateUtil.download(request, response, storeName, contentType,
				realName);

		return null;
	}
}
