package observer.observer2;

import java.util.Observable;

/**
 * 相当于是自定义了一个主题，保存需要访问的数据等信息
 * 
 * 这里使用的是extends Observable
 * 
 * @author Liubao
 * @2015年3月11日
 * 
 */
public class StockDataSubject extends Observable {
    private String symbol;
    private float close;
    private float high;
    private float low;
    private long volume;

    public StockDataSubject() {
    }

    public String getSymbol() {
        return symbol;
    }

    public float getClose() {
        return close;
    }

    public float getHigh() {
        return high;
    }

    public float getLow() {
        return low;
    }

    public long getVolume() {
        return volume;
    }

    public void sendStockData() {
        // 设置状态改变
        setChanged();
        // 通知所有的观察者没信息改变了
        notifyObservers();
    }

    public void setStockData(String symbol, float close, float high, float low,
            long volume) {
        this.symbol = symbol;
        this.close = close;
        this.high = high;
        this.low = low;
        this.volume = volume;
        sendStockData();
    }
}