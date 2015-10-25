package ruijin;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;

/**
 * 传统定时器测试:在指定时间进行测试，只能使用main方法进行测试
 * http://yuyue.rjh.com.cn/Guahao/GetCardCredential?cardid=141771
 * <ID>141771</ID><CardNo>132002101445509</CardNo>
 * <CardHolderName>刘保</CardHolderName><CardHolderCredentialID>13063419850416313X</CardHolderCredentialID>
 * did=00000000010650
 * doctorid=00000000010650
 * 手动发送post请求参数为：OK
 * vefirycode=&usercardid=141771&ordertype=doctor&hospitalid=42502656400&templateid=14111000000285&doctorname=王曙&hospitalname=瑞金医院&resdeptl2=门诊内分泌&date=2014-12-16 星期二&time=14:00-14:59&fee=20&address=
 * 
 * @author Liubao
 * @2014年11月17日
 * 信息2：wasabili；wasabili1990
 * <ID>131713</ID><CardNo>132002101459006</CardNo>
 * <ID>134750</ID><CardNo>6010815597</CardNo>
 * 
 * 测试：
 * vefirycode=&usercardid=131713&ordertype=doctor&hospitalid=42502656400&templateid=14111000000285&doctorname=王曙&hospitalname=瑞金医院&resdeptl2=门诊内分泌&date=2014-12-16 星期二&time=14:00-14:59&fee=20&address=
 *
 *
 *步骤解析：
 *1.填写挂号时间信息，确认post单词提交方法打开，和获取验证码方法注释掉；
 *2.首先登录，确保session中存在当前用户信息；
 *3.获取并填写验证码verifyCode和Cookie信息；
 *4.确认main方法中的提交时间，开始提交post请求；
 *
 *
 *
 */
public class TimerTestMain {
    
    private static final SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    public static void main(String args[]) throws Exception {
        Timer timer = new Timer();
        // 测试一：任务在指定时间执行
        //timer.schedule(new MyTimerTask(), new Date());//立刻执行
        //timer.schedule(new MyTimerTask(), 3000);
        //new Timer().schedule(new MyTimerTask(), 3000);//3秒后执行
        //3秒后首次执行，然后每间隔2秒执行一次
        //new Timer().schedule(new MyTimerTask(), 3000,1000);
        //预约时间段2014-12-16 14:00-14:59
        Date date = sdf.parse("2014-11-17 13:13:01");
        //Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2014-11-18 07:30:01");
        timer.schedule(new MyTimerTask(), date);//在指定时间执行
        //new Timer().schedule(new MyTimerTask(), date,1000);//在指定时间首次执行，然后每间隔2秒执行一次
    }
    
    

}


 
