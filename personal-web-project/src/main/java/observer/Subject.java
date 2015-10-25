package observer;

/**
 * 主题接口
 * 
 * @author Liubao
 * @2015年3月11日
 * 
 */
public interface Subject {

    // methods to register and unregister observers
    public void register(Observer obj);

    public void unregister(Observer obj);

    // method to notify observers of change
    public void notifyObservers();

    // method to get updates from subject
    public Object getUpdate(Observer obj);

}