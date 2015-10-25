package observer;

/**
 * 自定义的一个观察者
 * 
 * @author Liubao
 * @2015年3月11日
 * 
 */
public class MyTopicObserver implements Observer {

    private String name;
    private Subject topic;

    public MyTopicObserver(String nm) {
        this.name = nm;
    }

    @Override
    public void update() {
        String msg = (String) topic.getUpdate(this);
        if (msg == null) {
            System.out.println(name + ":: No new message");
        } else
            System.out.println(name + ":: Consuming message::" + msg);
    }

    @Override
    public void setSubject(Subject sub) {
        this.topic = sub;
    }

}