package observer;

/**
 * 自定义观察者接口
 * 
 * @author Liubao
 * @2015年3月11日
 * 
 */
public interface Observer {

    // method to update the observer, used by subject
    public void update();

    // attach with subject to observe
    public void setSubject(Subject sub);
}