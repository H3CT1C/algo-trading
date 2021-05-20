import messaging.EventManager;
import source.data.MarketDataManager;

public class Main {
    public static void main(String[] args) {
        EventManager eventManager = new EventManager();
        MarketDataManager marketDataManager = new MarketDataManager("ethbtc", eventManager);
    }
}
