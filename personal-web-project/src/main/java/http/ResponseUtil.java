package http;

import java.util.Map;

import com.meidusa.fastjson.JSON;

public class ResponseUtil {

	@SuppressWarnings({ "unchecked", "unused" })
    public static boolean  issuccess(String respString){
		Map<String, Object> map=JSON.parseObject(respString,Map.class); 
		String code=map.get("errorCode").toString();	
		
		if ("0".equals(code)){
			//String result=map.get("result").toString();
			String result=JSON.toJSONString(map.get("result"));
			System.out.println("errorCode is: "+code);
			return true;
		}else {
			String errorMessage=map.get("errorMessage").toString();
			System.out.println("errorCode is: "+code+" \t errorMessage is: "+errorMessage);
		}
		
		return false;
	}
	public static boolean  issuccess(String respString,String rightCode){
		Map<String, Object> map=JSON.parseObject(respString); 
		String code=map.get("errorCode").toString();
		if ("0".equals(code)||rightCode.equals(code)){
			return true;
		}
		System.out.println("code is: "+code+" \t result is: "+map.get("result").toString());
		return false;
	}
}
