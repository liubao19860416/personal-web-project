package observer.observer2;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Observable;
import java.util.Observer;

/**
 * 另一个观察者
 * 
 * @author Liubao
 * @2015年3月11日
 * 
 */
public class TradingFoolObserver implements Observer {
    private String symbol;
    private float close;

    public TradingFoolObserver(Observable observable) {
        observable.addObserver(this);// 注册关系
    }

    public void update(Observable observable, Object args) {
        if (observable instanceof StockDataSubject) {
            StockDataSubject stockData = (StockDataSubject) observable;
            this.symbol = stockData.getSymbol();
            this.close = stockData.getClose();
            display();
        }
    }

    public void display() {
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        DecimalFormat priceFormat = new DecimalFormat("###.00", dfs);
        System.out.println("Trading Fool says... ");
        System.out.println("\t" + symbol + " is currently trading at $"
                + priceFormat.format(close) + " per share.");
        System.out.println();
    }
}