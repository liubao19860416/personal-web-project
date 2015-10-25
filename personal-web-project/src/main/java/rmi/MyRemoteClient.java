package rmi;

import java.rmi.Naming;

/**
 * 远程调用的客户端代码
 * 
 * @author Liubao
 * @2014年10月28日
 * 
 */
public class MyRemoteClient {
    public void go() {
        try {
            MyRemote service = (MyRemote) Naming
                    .lookup("rmi://127.0.0.1/RemoteName");
            String result = service.sayHello();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //测试主函数入口
    public static void main(String[] args) {
        new MyRemoteClient().go();
        
    }

}
