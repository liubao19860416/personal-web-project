package http;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import ocr.OCR;

public class HttpUtil {
    
    /**
     * 将request中的key和value拼装成map集合返回
     */
    public static Map<String, Object> transRequestToMap(HttpServletRequest request){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        // 方式1：一个一个的获取拼装参数即可
        //if(!StringUtils.isEmpty(request.getParameter("tabName"))){
        //    params.put("tabName", request.getParameter("tabName").toString());
        //}
        //方式2：反射获取
        Map<?, ?> parameterMap = request.getParameterMap();
        Enumeration<?> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String key = (String)parameterNames.nextElement();
            Object value = parameterMap.get(key);
            resultMap.put(key, value);
        }
        return resultMap;
    }
    
    /**
     * 将map中的key和value拼装成post请求参数的格式的字符串
     */
    public static String transFromMapToPostString(Map<String,String> params){
        StringBuilder sb=new StringBuilder();
        for (String key : params.keySet()) {
            if(sb.length()==0){
                //sb.append("?");
                sb.append(key+"="+params.get(key));
            }else{
                sb.append("&"+key+"="+params.get(key));
            }
            //System.out.println(key + "--->" + params.get(key));
        }
        return sb.toString();
    }
    
    /**
     * 发送post请求，获取验证码相关信息（顺便获取当前cookie信息？）
     * 
     */
    public static String sendPostGetValidateCode(String url, String postParam,Map<String,String> params) {
        PrintWriter out = null;
        BufferedInputStream in2 = null;
        BufferedOutputStream out2 = null;
        String result = "OK";
        File file=null;
        String filePath="d:\\temp\\verifyCode.jpg";
        try {
            //创建URL对象
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性，即HTTP消息头参数
            conn.setRequestProperty("Accept", "image/png,image/*;q=0.8,*/*;q=0.5");
            conn.setRequestProperty("Accept-Encoding", "gzip, deflate");
            conn.setRequestProperty("Accept-Language", "zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");
            conn.setRequestProperty("Connection", "keep-alive");
            conn.setRequestProperty("Host", "yuyue.rjh.com.cn");
            conn.setRequestProperty("Referer", "http://yuyue.rjh.com.cn/Guahao/Index");
            conn.setRequestProperty("User-Agent", " Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0");
            conn.setRequestProperty("Content-Type", " application/x-www-form-urlencoded; charset=UTF-8");
            conn.setRequestProperty("X-Requested-With", " XMLHttpRequest");
            conn.setRequestProperty("Content-Length", "222");
            conn.setRequestProperty("Pragma", " no-cache");
            conn.setRequestProperty("Cache-Control", "no-cache");
            
            //从map中获取
            conn.setRequestProperty("Cookie", params.get("Cookie"));
            
            //提交表单请求头参数
            //conn.setConnectTimeout(1000);
             
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            
            //发送post请求参数为time：2014-11-17 11:44:02
            out.print(postParam);
            
            // flush输出流的缓冲
            out.flush();
            
            //在这里输出为本地图片文件格式存储（d:\\temp\\verifyCode.jpg）
            in2 = new BufferedInputStream(conn.getInputStream());
            file=new File(filePath);
            if(!file.exists()){
                file.createNewFile();
            }
            out2=new BufferedOutputStream(new FileOutputStream((file)));
            
            //读取响应参数
            int length=-1;
            byte[] buf=new byte[1024];
            while ((length = in2.read(buf)) != -1) {
                out2.write(buf,0,length);
            }
        } catch (Exception e) {
            System.out.println("发送 sendPostGetValidateCode 请求出现异常！" + e);
            e.printStackTrace();
        }
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (out2 != null) {
                    out2.close();
                }
                if (in2 != null) {
                    in2.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }finally{
                out=null;
                out2=null;
                in2=null;
            }
        }
        
        // 解析验证码图片信息，进行ocr解析
        try {
            result=new OCR().recognizeText(file,"jpg");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("OCR解析图片出现异常！" + e);
        }
        
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     * 
     * 发送请求的 URL 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String postParam,Map<String,String> params) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            long startTime = System.currentTimeMillis();
            System.out.println("@@ startTime is "+ new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date(startTime)));
            System.out.println("## url is " + url + "     \n\t\t" + postParam);
            //创建URL对象
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            
            // 设置通用的请求属性，即HTTP消息头参数
            // conn.setRequestProperty("accept", "*/*");
            // conn.setRequestProperty("connection", "Keep-Alive");
            // conn.setRequestProperty("Content-Type", "application/json");
            // conn.setRequestProperty("user-agent",
            // "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // conn.setRequestProperty("saikeMobileHead",
            // "{\"deviceId\":\"1220524\",\"appCode\":\"10000\",\"appVersion\":\"10000\"}");
            // conn.setRequestProperty("deviceId", "1220524");
            // conn.setRequestProperty("appCode", "10000");
            
            //提交表单请求头参数
            //conn.setConnectTimeout(1000);
            conn.setRequestProperty("Host", "yuyue.rjh.com.cn");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0");
            conn.setRequestProperty("Accept", " */*");
            conn.setRequestProperty("Accept-Language", "zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");
            conn.setRequestProperty("Accept-Encoding", "gzip, deflate");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            conn.setRequestProperty("X-Requested-With", "XMLHttpRequest");
            conn.setRequestProperty("Referer", "http://yuyue.rjh.com.cn/Guahao/Index");
            conn.setRequestProperty("Content-Length", "222");
            conn.setRequestProperty("Connection", "keep-alive");
            conn.setRequestProperty("Pragma", "no-cache");
            conn.setRequestProperty("Cache-Control", "no-cache");
            
            // 添加Cookie
            conn.setRequestProperty("Cookie", params.get("Cookie"));
             
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            
            // 发送POST请求参数
            out.print(postParam);
            
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    conn.getInputStream(), "utf-8"));
            //读取响应参数
            String line="";
            while ((line = in.readLine()) != null) {
                result += line;
            }
            long endTime = System.currentTimeMillis();
            System.out.println("## response is  " + result);
            System.out.println("@@ endTime is "
                    + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
                            .format(new Date(endTime)));
            System.out.println("@@ total-time:" + (endTime - startTime));
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }finally{
                in=null;
                out=null;
            }
        }
        return result;
    }

    /**
     * 向指定URL发送GET方法的请求 发送请求的URL 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            long startTime = System.currentTimeMillis();
            System.out.println("@@ startTime is "
                    + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
                            .format(new Date(startTime)));
            String urlNameString = url + "?" + param;
            System.out.println("## url is " + urlNameString);
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            long endTime = System.currentTimeMillis();
            System.out.println("## response is " + result);
            System.out.println("@@ endTime is "
                    + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
                            .format(new Date(endTime)));
            System.out.println("@@ total-time:" + (endTime - startTime));
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

}