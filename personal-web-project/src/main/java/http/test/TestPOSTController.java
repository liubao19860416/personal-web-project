package http.test;

import http.HttpUtil;
import http.ResponseUtil;

import org.junit.Assert;
import org.junit.Test;


public class TestPOSTController {

	 String url = "http://localhost:8080/ROOT/";

	//String url = "http://10.32.17.125:8080/dop/";

	@Test
	public void pushPubMsg() {
		String param = "{\"content\":\"真是個坑哪。。。。\"}";
		String method = "message/operate/pushPubMsg/0";
		long startTime = System.currentTimeMillis();
		String str = HttpUtil.sendPost(url + method, param,null);
		long endTime = System.currentTimeMillis();
		System.out.println("response is " + str);
		System.out.println("total-time:" + (endTime - startTime));
		Assert.assertTrue(ResponseUtil.issuccess(str));
	}
	
	@Test
	public void getPubMsgList() {
		String param = "{\"pageIndex\":1,\"pageSize\":5}";
		String method = "message/operate/getPubMsgList/0";
		long startTime = System.currentTimeMillis();
		String str = HttpUtil.sendPost(url + method, param,null);
		long endTime = System.currentTimeMillis();
		System.out.println("response is " + str);
		System.out.println("total-time:" + (endTime - startTime));
		Assert.assertTrue(ResponseUtil.issuccess(str));
	}
	
	
}
