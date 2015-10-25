package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * RMI远程调用测试服务端接口类
 * 
 * @author Liubao
 * @2014年10月28日
 * 
 */
public interface MyRemote extends Remote {
    public String sayHello() throws RemoteException;
}
