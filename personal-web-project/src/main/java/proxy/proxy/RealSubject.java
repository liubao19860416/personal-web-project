package proxy.proxy;

public class RealSubject extends Subject {

    public RealSubject() {
    }

    public void request() {
        System.out.println("From real subject.");
    }

}
