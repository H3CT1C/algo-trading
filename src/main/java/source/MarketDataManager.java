package source;

import java.util.HashMap;
import java.util.Map;

public class MarketDataManager implements Runnable {

    private Map<String, BinanceGateway> subscriptions;

    public MarketDataManager() {
        this.subscriptions = new HashMap<>();
    }

    public void subscribeOrderBook(String symbol) {
        if (!subscriptions.containsKey(symbol)) {
            subscriptions.put(symbol, new BinanceGateway(symbol));
        }
        subscriptions.get(symbol).startDepthEventStreaming(symbol);
    }

    public void subscribeTrades(String symbol) {
        if (!subscriptions.containsKey(symbol)) {
            subscriptions.put(symbol, new BinanceGateway(symbol));
        }
        subscriptions.get(symbol).startAggTradesEventStreaming(symbol);
    }

    @Override
    public void run() {
    }
}
