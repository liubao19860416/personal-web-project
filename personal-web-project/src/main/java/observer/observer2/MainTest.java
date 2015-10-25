package observer.observer2;

/**
 * 测试类
 * 
 * @author Liubao
 * @2015年3月11日
 * 
 */
public class MainTest {
    
    public static void main(String[] args) {
        System.out.println();
        System.out.println("-- Stock Quote Application --");
        System.out.println();

        StockDataSubject stockData = new StockDataSubject();

        // register observers...
        new TradingFoolObserver(stockData);
        new BigBuyerObserver(stockData);

        // generate changes to stock data...
        stockData.setStockData("JUPM", 16.10f, 16.15f, 15.34f, (long) 481172);
        stockData.setStockData("SUNW", 4.84f, 4.90f, 4.79f, (long) 68870233);
        stockData.setStockData("MSFT", 23.17f, 23.37f, 23.05f, (long) 75091400);
    }
}