package browerchecker;

import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

/** 
 * 根据USER-AGENT判断用户的浏览器类型，并进行对应的URLEncoder编码 
 */
public class UserAgentUtil2 {
    
    /**
     * 新添加方法
     * 根据浏览器类型，自动对request中传递的字符串进行编码返回，解决传输乱码问题；
     */
    public static String getEncodedRequestString(String strName,HttpServletResponse response,HttpServletRequest request) throws Exception{  
        // 清空response缓存中的信息
        response.reset();
        response.setContentType("application/html; charset=UTF-8");
        strName= new String(strName.getBytes(), "UTF-8");
       
        //这是校验用户浏览器信息的部分，可以有效的解决乱码问题
        final String userAgent = request.getHeader("USER-AGENT");
        if (userAgent.contains("MSIE") || userAgent.contains("rv:11.0")) {
            // IE浏览器 (IE 11的userAgent已更改，需根据rv:11.0来判别)
            strName = URLEncoder.encode(strName, "UTF-8");
        } else if (userAgent.contains("Mozilla")) {
            // google,火狐浏览器
            strName = new String(strName.getBytes(), "ISO8859-1");
        } else {
            // 其他浏览器
            strName = URLEncoder.encode(strName, "UTF-8");
        }
        return strName;
    }
    
    /** 
     * 同版本1代码
     */  
    public static UserAgent getUserAgent(String userAgent) {  
        if (StringUtils.isBlank(userAgent)) {  
            return null;  
        }   
        if (userAgent.contains("Windows")) {//主流应用靠前  
            if (userAgent.contains("Windows NT 6.2")) {//Windows 8  
                return judgeBrowser(userAgent, "Windows", "8" , null);//判断浏览器  
            } else if (userAgent.contains("Windows NT 6.1")) {//Windows 7  
                return judgeBrowser(userAgent, "Windows", "7" , null);  
            } else if (userAgent.contains("Windows NT 6.0")) {//Windows Vista  
                return judgeBrowser(userAgent, "Windows", "Vista" , null);  
            } else if (userAgent.contains("Windows NT 5.2")) {//Windows XP x64 Edition  
                return judgeBrowser(userAgent, "Windows", "XP" , "x64 Edition");  
            } else if (userAgent.contains("Windows NT 5.1")) {//Windows XP  
                return judgeBrowser(userAgent, "Windows", "XP" , null);  
            } else if (userAgent.contains("Windows NT 5.01")) {//Windows 2000, Service Pack 1 (SP1)  
                return judgeBrowser(userAgent, "Windows", "2000" , "SP1");  
            } else if (userAgent.contains("Windows NT 5.0")) {//Windows 2000  
                return judgeBrowser(userAgent, "Windows", "2000" , null);  
            } else if (userAgent.contains("Windows NT 4.0")) {//Microsoft Windows NT 4.0  
                return judgeBrowser(userAgent, "Windows", "NT 4.0" , null);  
            } else if (userAgent.contains("Windows 98; Win 9x 4.90")) {//Windows Millennium Edition (Windows Me)  
                return judgeBrowser(userAgent, "Windows", "ME" , null);  
            } else if (userAgent.contains("Windows 98")) {//Windows 98  
                return judgeBrowser(userAgent, "Windows", "98" , null);  
            } else if (userAgent.contains("Windows 95")) {//Windows 95  
                return judgeBrowser(userAgent, "Windows", "95" , null);  
            } else if (userAgent.contains("Windows CE")) {//Windows CE  
                return judgeBrowser(userAgent, "Windows", "CE" , null);  
            }   
        } else if (userAgent.contains("Mac OS X")) {  
            if (userAgent.contains("iPod")) {  
                return judgeBrowser(userAgent, "iPod", null , null);//判断浏览器  
            }  
        }  
        return null;  
    }  
      
    /** 
     * 用途：根据客户端 User Agent Strings 判断其浏览器 
     * 根据浏览器的用户使用量降序排列，这样对于大多数用户来说可以少判断几次即可拿到结果： 
     */  
    private static UserAgent judgeBrowser(String userAgent, String platformType, String platformSeries, String platformVersion) {  
        if (userAgent.contains("Chrome")) {  
            String temp = userAgent.substring(userAgent.indexOf("Chrome/") + 7);//拿到User Agent String "Chrome/" 之后的字符串,结果形如"24.0.1295.0 Safari/537.15"或"24.0.1295.0"  
            String chromeVersion = null;  
            if (temp.indexOf(" ") < 0) {//temp形如"24.0.1295.0"  
                chromeVersion = temp;  
            } else {//temp形如"24.0.1295.0 Safari/537.15"  
                chromeVersion = temp.substring(0, temp.indexOf(" "));  
            }  
            return new UserAgent("Chrome", chromeVersion, platformType, platformSeries, platformVersion);  
        } else if (userAgent.contains("Firefox")) {  
            String temp = userAgent.substring(userAgent.indexOf("Firefox/") + 8);//拿到User Agent String "Firefox/" 之后的字符串,结果形如"16.0.1 Gecko/20121011"或"16.0.1"  
            String ffVersion = null;  
            if (temp.indexOf(" ") < 0) {//temp形如"16.0.1"  
                ffVersion = temp;  
            } else {//temp形如"16.0.1 Gecko/20121011"  
                ffVersion = temp.substring(0, temp.indexOf(" "));  
            }  
            return new UserAgent("Firefox", ffVersion, platformType, platformSeries, platformVersion);  
        } else if (userAgent.contains("MSIE")) {  
            if (userAgent.contains("MSIE 10.0")) {//Internet Explorer 10  
                return new UserAgent("Internet Explorer", "10", platformType, platformSeries, platformVersion);  
            } else if (userAgent.contains("MSIE 9.0")) {//Internet Explorer 9  
                return new UserAgent("Internet Explorer", "9", platformType, platformSeries, platformVersion);  
            } else if (userAgent.contains("MSIE 8.0")) {//Internet Explorer 8  
                return new UserAgent("Internet Explorer", "8", platformType, platformSeries, platformVersion);  
            } else if (userAgent.contains("MSIE 7.0")) {//Internet Explorer 7  
                return new UserAgent("Internet Explorer", "7", platformType, platformSeries, platformVersion);  
            } else if (userAgent.contains("MSIE 6.0")) {//Internet Explorer 6  
                return new UserAgent("Internet Explorer", "6", platformType, platformSeries, platformVersion);  
            }  
        } else { 
            return new UserAgent(null, null, platformType, platformSeries, platformVersion);  
        }  
        return null;  
    }  
}
