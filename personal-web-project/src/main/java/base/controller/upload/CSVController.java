package base.controller.upload;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(value = "csv")
public class CSVController {
	@RequestMapping(value = "CSVupload")
	public String toCSV() {
		return "uploadCVS";
	}
	@RequestMapping(value = "uploadInfo")
	public String uploadInfo() {
		return "uploadInfo";
	}
	@RequestMapping(value = "uploadForDealer")
	public String uploadForDealer() {
		return "uploadForDealer";
	}


	@RequestMapping(value = "upload")
	@ResponseBody
	public boolean upload(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		// 别名
		String[] alaises = ServletRequestUtils.getStringParameters(request,
				"alais");

		String[] params = new String[] { "alais" };
		Map<String, Object[]> values = new HashMap<String, Object[]>();
		values.put("alais", alaises);

//		List<ReadForCSVEntity> result = FileOperateUtil.uploadCSV(request,
//				params, values);

		boolean isSucess = false;

		OutputStream out = null;
		OutputStreamWriter osw = null;
		BufferedWriter bw = null;
		String name  = new Date().toLocaleString();
		try {
			response.setContentType("octets/stream");
			response.addHeader("Content-Disposition",
					"attachment;filename="+name+"统计数据.csv");
			out = response.getOutputStream();
			osw = new OutputStreamWriter(out);
			bw = new BufferedWriter(osw);
//			if (result != null && !result.isEmpty()) {
//
//				bw.append("手机号,").append("姓名,").append("总金额,").append("100元券,").append("50元券,")
//						.append("10元券,").append("剩余数").append("\r");
//				for (ReadForCSVEntity readForCSVEntity : result) {
//					bw.append(readForCSVEntity.getPhoneNumber()+",")
//							.append(readForCSVEntity.getName()+",")
//							.append(readForCSVEntity.getTotalPrice()+",")
//							.append(readForCSVEntity.getCouponList().get(0).toString()+",")
//							.append(readForCSVEntity.getCouponList().get(1).toString()+",")
//							.append(readForCSVEntity.getCouponList().get(2).toString()+",")
//							.append(readForCSVEntity.getCouponList().get(3).toString()).append("\r");
//				
//				}
//			}
			isSucess = true;
		} catch (Exception e) {
			isSucess = false;
		} finally {
			if (bw != null) {
				try {
					bw.close();
					bw = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (osw != null) {
				try {
					osw.close();
					osw = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (out != null) {
				try {
					out.close();
					out = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return isSucess;

	}

@RequestMapping(value = "uploadForInfo")
@ResponseBody
public boolean uploadForInfo(HttpServletRequest request,
		HttpServletResponse response) throws Exception {
  
	Map<String, Object> map = new HashMap<String, Object>();
//   ArrayList<String> price100=CouponUtil.stringToArray( request.getParameter("100_price").split(","));
//   ArrayList<String> price50=CouponUtil.stringToArray( request.getParameter("50_price").split(","));
//   ArrayList<String> price10=CouponUtil.stringToArray( request.getParameter("10_price").split(","));
   String date=request.getParameter("date");
	// 别名
	String[] alaises = ServletRequestUtils.getStringParameters(request,
			"alais");

	String[] params = new String[] { "alais" };
	Map<String, Object[]> values = new HashMap<String, Object[]>();
	values.put("alais", alaises);

//	List<ReadForCSVEntity> result = FileOperateUtil.uploadCSV(request,
//			params, values);

	boolean isSucess = false;

	OutputStream out = null;
	OutputStreamWriter osw = null;
	BufferedWriter bw = null;
	String name  = new Date().toLocaleString();
	try {
		response.setContentType("octets/stream");
		response.addHeader("Content-Disposition",
				"attachment;filename="+name+"发送清单.csv");
		out = response.getOutputStream();
		osw = new OutputStreamWriter(out);
		bw = new BufferedWriter(osw);
//		if (result != null && !result.isEmpty()) {
//
//			bw.append("手机号码,").append("短信内容").append("\r");;
//			for (ReadForCSVEntity readForCSVEntity : result) {
//				String price_100 = "",price_50= "",price_10= "";
//				String content = "\""+readForCSVEntity.getName()+"你好"+date+"期间您参加的保养管家活动奖励金额为"+
//						readForCSVEntity.getTotalPrice()+"元，奖励形式为1号店电子券，";
//				   if((int)readForCSVEntity.getCouponList().get(0)!=0){
//					   content = content+"100元面值"+readForCSVEntity.getCouponList().get(0)+"张,";
//					   for(int i=0;i<(int)readForCSVEntity.getCouponList().get(0);i++){
//						   price_100 = price_100+price100.get(0)+",";
//						   price100.remove(0);
//					   }
//				   }
//				   if((int)readForCSVEntity.getCouponList().get(1)!=0){
//					   content = content+"50元面值"+readForCSVEntity.getCouponList().get(1)+"张,";
//					   for(int i=0;i<(int)readForCSVEntity.getCouponList().get(1);i++){
//						   price_50 = price_50+price50.get(0)+",";
//						   price50.remove(0);
//					   }
//				   }
//				   if((int)readForCSVEntity.getCouponList().get(2)!=0){
//					   content = content+"10元面值"+readForCSVEntity.getCouponList().get(2)+"张,";
//					   for(int i=0;i<(int)readForCSVEntity.getCouponList().get(2);i++){
//						   price_10 = price_10+price10.get(0)+",";
//						   price10.remove(0);
//					   }
//				   }
//				   content = content+"券号："+price_100+price_50+price_10+"感谢您对车享的支持。"+"\"";
//				bw.append(readForCSVEntity.getPhoneNumber()+",")
//						.append(content).append("\r");
//			
//			}
//		}
		isSucess = true;
	} catch (Exception e) {
		isSucess = false;
	} finally {
		if (bw != null) {
			try {
				bw.close();
				bw = null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (osw != null) {
			try {
				osw.close();
				osw = null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (out != null) {
			try {
				out.close();
				out = null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	return isSucess;

}

@RequestMapping(value = "uploadDealer")
@ResponseBody
public  boolean uploadDealer(HttpServletRequest request,
		HttpServletResponse response) throws Exception {

	Map<String, Object> map = new HashMap<String, Object>();

	// 别名
	String[] alaises = ServletRequestUtils.getStringParameters(request,
			"alais");

	String[] params = new String[] { "alais" };
	Map<String, Object[]> values = new HashMap<String, Object[]>();
	values.put("alais", alaises);

//	Map<String,DealerInfo> result = FileOperateUtil.uploadDealerCSV(request,
//			params, values);
	List res = new ArrayList();
//	ResultForDealerEmail rl=null;
//   Object[] keySet = result.keySet().toArray();
    
	boolean isSucess = false;

	OutputStream out = null;
	OutputStreamWriter osw = null;
	BufferedWriter bw = null;
	String name  = new Date().toLocaleString();
	try {
		response.setContentType("octets/stream");
		response.addHeader("Content-Disposition",
				"attachment;filename="+name+"经销商数据.csv");
		out = response.getOutputStream();
		osw = new OutputStreamWriter(out);
		bw = new BufferedWriter(osw);

			bw.append("经销商名称,").append("姓名,").append("总数").append("\r");
//			  for (int i = 0; i < keySet.length; i++) {
//		    	  DealerInfo dealerInfo = result.get(keySet[i]);
//		    	  rl = new ResultForDealerEmail();
//		  		bw.append(dealerInfo.getDealerName()+",")
//		  		.append(dealerInfo.getName()+",")
//		  		.append(dealerInfo.getCount()+",")
//		  		.append("\r");
//			}
		isSucess = true;
	} catch (Exception e) {
		isSucess = false;
	} finally {
		if (bw != null) {
			try {
				bw.close();
				bw = null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (osw != null) {
			try {
				osw.close();
				osw = null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (out != null) {
			try {
				out.close();
				out = null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	return isSucess;

}
}