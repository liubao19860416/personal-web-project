package rmi;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * RMI远程调用测试服务端实现类
 * 
 * @author Liubao
 * @2014年10月28日
 * 
 */
@SuppressWarnings("serial")
public class MyRemoteImpl extends UnicastRemoteObject implements MyRemote {

    public String sayHello() {
        return "MyRemote say: hello,how are you!";
    }

    public MyRemoteImpl() throws RemoteException {
        super();
    }

    // 主函数测试代码
    public static void main(String[] args) {
        try {
            Runtime runtime = Runtime.getRuntime();
            Process process = runtime.exec("rmiregistry");
            MyRemote myRemote = new MyRemoteImpl();
            process.waitFor();
            Naming.rebind("RemoteName", myRemote);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
