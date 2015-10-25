package http;

import java.util.Iterator;
import java.util.Map;
/**
 * URL相关操作工具类
 * @author Liubao
 * @2014年12月10日
 *
 */
public class URLUtil {
    
    /**
     * 获取post请求字符串参数
     */
    public static String buildURL(Map parameters) {
        StringBuffer url = new StringBuffer();
        if (parameters != null && parameters.size() > 0) {
            url.append("?");
            for (Iterator iter = parameters.keySet().iterator(); iter.hasNext();) {
                String key = (String) iter.next();
                String[] values = (String[]) parameters.get(key);
                for (int i = 0; i < values.length; i++) {
                    url.append(key).append("=").append(values[i]).append("&");
                }
            }
        }
        return url.toString();
    }
}
