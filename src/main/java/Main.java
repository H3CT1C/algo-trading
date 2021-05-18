import messaging.EventManager;
import source.MarketDataManager;

public class Main {
    public static void main(String[] args) {
        EventManager eventManager = new EventManager();
        MarketDataManager marketDataManager = new MarketDataManager();
        marketDataManager.subscribeOrderBook("btcusdt");
    }
}
