package source.data;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;

public class LocalOrderBook {
    private Map<String, NavigableMap<BigDecimal, BigDecimal>> orderBook;

    public LocalOrderBook() {
        this.orderBook = new HashMap<>();
    }

    public NavigableMap<BigDecimal, BigDecimal> getAsks() {
        return orderBook.get("ASKS");
    }

    public NavigableMap<BigDecimal, BigDecimal> getBids() {
        return orderBook.get("BIDS");
    }

    public void put(String string, NavigableMap<BigDecimal, BigDecimal> map) {
        orderBook.put(string, map);
    }

    /**
     * @return the best ask in the order book
     */
    public Map.Entry<BigDecimal, BigDecimal> getBestAsk() {
        return getAsks().lastEntry();
    }

    public Map.Entry<BigDecimal, BigDecimal> getSecondBestAsk() {
        return getAsks().lowerEntry(getBestAsk().getKey());
    }

    public Map.Entry<BigDecimal, BigDecimal> getThirdBestAsk() {
        return getAsks().lowerEntry(getSecondBestAsk().getKey());
    }

    /**
     * @return the best bid in the order book
     */
    public Map.Entry<BigDecimal, BigDecimal> getBestBid() {
        return getBids().firstEntry();
    }

    public Map.Entry<BigDecimal, BigDecimal> getSecondBestBid() {
        return getAsks().higherEntry(getBestBid().getKey());
    }

    public Map.Entry<BigDecimal, BigDecimal> getThirdBestBid() {
        return getAsks().higherEntry(getSecondBestBid().getKey());
    }

    /**
     * @return a depth cache, containing two keys (ASKs and BIDs), and for each, an ordered list of book entries.
     */
    public Map<String, NavigableMap<BigDecimal, BigDecimal>> getLocalOrderBook() {
        return orderBook;
    }
}
