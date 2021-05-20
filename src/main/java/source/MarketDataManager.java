package source;

import messaging.EventManager;

public class MarketDataManager implements Runnable {
    private String symbol;
    private BinanceGateway binanceGateway;
    private EventManager eventManager;

    public MarketDataManager(String symbol, EventManager eventManager) {
        this.symbol = symbol;
        this.binanceGateway = new BinanceGateway(symbol);
        this.eventManager = eventManager;
    }

    public void subscribeOrderBook() {
        binanceGateway.startDepthEventStreaming(symbol, eventManager);
    }

    public void subscribeTrades() {
        binanceGateway.startAggTradesEventStreaming(symbol, eventManager);
    }

    @Override
    public void run() {
        subscribeOrderBook();
    }
}
