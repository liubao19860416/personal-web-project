package ruijin;

import http.HttpUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimerTask;

/**
 * 要执行的post方法
*
*/
public class MyTimerTask extends TimerTask {
   
   @SuppressWarnings({ "deprecation", "unused" })
   @Override
   public void run() {
       System.out.println("任务执行时间为："+new Date().toLocaleString()+",秒钟为："+new Date().getSeconds());
       
       //测试get方式
       //String getString="";
       //String get = HttpUtil.sendGet("http://www.baidu.com", getString);
       //System.out.println(get);
       
       //验证码获取程序
       String validateCodeURL="http://yuyue.rjh.com.cn/Guahao/GetValidateCode";
       Map<String, String> map1=new HashMap<String, String>();
       map1.put("time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
       
       // TODO 添加Cookie
       String cookie="ASP.NET_SessionId=gahaodabn1bw5hxec35akrtk; __RequestVerificationToken_Lw__=VLK/UfGWkd8Q2q+JJ5clVfHsJkvF9NiO8qpIPUw/6qBB7H4dOmvv3uBS/RRs79IaSiYGhfVgDebrNkNenYQ3a2RoeIRf1wAlYBAgmeWcTGE=; .ASPXAUTH=5D357A8B24316B99838726A495D91736C3FCBD37CB8E62F3AF6CF897D4EC1C130FC8C7CCAF3A68146B0FCA67341B106448ADCD28E714988EC70BA7EE30FD14B28C22839EB886909A32024CDC79EFBC2A";
       
       map1.put("Cookie", cookie);
       
       String validateCodePostString=HttpUtil.transFromMapToPostString(map1);
       System.out.println("## 获取验证码请求参数 ：" + validateCodePostString);
       
       //1.测试获取验证码图片
       String validateCode = null;
       boolean flag = false;
       
       //循环发送获取验证码
       /*for (int i = 0; i < 10; i++) {
            // 暂停1秒钟
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            //2. 获取验证码字符串
            // 发送获取验证码请求
            validateCode = HttpUtil.sendPostGetValidateCode(validateCodeURL,validateCodePostString, map1);
            
            // 校验该验证是否可用
            if(validateCode!=null){
                validateCode=checkValidateCode(validateCode);
            }
            if (validateCode != null) {
                flag = true;
                break;
            }

            if (i == 9 && validateCode == null) {
                System.out.println("10次验证码都获取失败！！！");
            } else if( validateCode == null) {
                System.out.println("第" + (i + 1) + "次验证码获取失败！！！");
            }else{
                System.out.println("验证码获取成功为;"+validateCode);
            }
       }*/
       
       // 发送获取验证码请求
       //validateCode = HttpUtil.sendPostGetValidateCode(validateCodeURL,validateCodePostString, map1);
       
       
       //3.执行自定义的需要调度的具体任务，发送一个HTTP POST请求，提交预约挂号相关信息
       String url="http://yuyue.rjh.com.cn/Guahao/SaveReservation";
       Map<String, String> params=new HashMap<String, String>();
       //拼装post请求参数（医生信息1）
       params.put("address", "");
       params.put("doctorname", "王曙");
       params.put("fee", "20");
       params.put("hospitalid", "42502656400");
       params.put("hospitalname", "瑞金医院");
       params.put("ordertype", "doctor");
       params.put("resdeptl2", "门诊内分泌");
       params.put("templateid", "14111000000285");
       params.put("usercardid", "141771");
       params.put("Cookie", cookie);
       
       //  拼装post请求参数（医生信息2）
       /*params.put("address", "");
       params.put("doctorname", "门诊内分泌");
       params.put("fee", "14");
       params.put("hospitalid", "42502656400");
       params.put("hospitalname", "瑞金医院");
       params.put("ordertype", "common");
       params.put("resdeptl2", "门诊内分泌");
       params.put("templateid", "14111600000476");
       params.put("date", "2015-02-09 星期一");
       params.put("time", "11:00-11:59");
       params.put("usercardid", "131713");
       params.put("Cookie", cookie);
       params.put("vefirycode", "9EW8");*/
       
       // TODO 预约时间及个人信息
       params.put("date", "2014-12-16 星期二");
       params.put("time", "14:00-14:59");
       params.put("vefirycode", "");
       params.put("vefirycode", validateCode);
       
       //4.测试发送表单提交
       String postString = HttpUtil.transFromMapToPostString(params);
       System.out.println("Post提交表单请求参数为："+postString);

       //符合条件时，提交表单请求
       String postResult = "failure";
       if(flag){
           // 发送post请求，提交挂号订单请求信息
           //postResult = HttpUtil.sendPost(url, postString,params);
       }
       
       //测试
       //postResult = HttpUtil.sendPost(url, postString,params);
       
       // 拼装HTTP消息头参数？？？
       
       //5.查看post请求结果
       System.out.println("提交表单响应结果："+postResult);
       
       //6.关闭当前定时器任务调度
       Thread.currentThread().stop();
       
   }

   /**
    * 校验验证码是否OCR解析OK
    * @param validateCode
    * @return
    */
   public String checkValidateCode(String validateCode) {
       
       System.out.println("过滤前的验证码为："+validateCode);
       //过滤非数字及字母的字符串
       String regex="[^\\w]||[^0-9]";
       validateCode= validateCode.replaceAll(regex, "");
       System.out.println("过滤后的验证码为："+validateCode);
       
       if(validateCode!=null&&validateCode.trim().length()==4){
           return validateCode;
       }
       return null;
   }
   
}
