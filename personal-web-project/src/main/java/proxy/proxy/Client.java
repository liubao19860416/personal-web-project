package proxy.proxy;

public class Client {
    public static void main(String[] args) {
        Subject sub = new ProxySubject(new RealSubject());
        sub.request();
    }
}
