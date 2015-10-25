package proxy.proxy;

public class ProxySubject extends Subject {
    private Subject realSubject;
//    private RealSubject realSubject;

    public ProxySubject(Subject s) {
        this.realSubject=s;
    }

    public void request()

    {
        preRequest();

        if (realSubject == null) {
            realSubject = new RealSubject();
        }

        realSubject.request();

        postRequest();
    }

    private void preRequest() {
        System.out.println("统一代理的对象执行操作前处理。。。");
        // something you want to do before requesting
    }

    private void postRequest() {
        System.out.println("统一代理的对象执行操作后处理。。。");
        // something you want to do after requesting
    }

}
