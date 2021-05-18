package source;

public class MarketDataManager implements Runnable {
    private String symbol;
    private BinanceGateway gateway;

    public MarketDataManager(String symbol) {
        this.symbol = symbol;
        this.gateway = new BinanceGateway(symbol);
    }

    void subscribeOrderBook(String symbol) {
        gateway.startDepthEventStreaming(symbol);
    }
    void subscribeTrades(String symbol) {
        gateway.startAggTradesEventStreaming(symbol);
    }

    @Override
    public void run() {
        subscribeOrderBook(symbol);
        subscribeTrades(symbol);
    }
}
