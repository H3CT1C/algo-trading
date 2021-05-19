package algo;

import com.binance.api.client.domain.market.OrderBook;
import messaging.EventListener;
import source.ScheduleEvent;

public class AnalyticManager implements EventListener {
    @Override
    public void handleEvent(OrderBook orderBook) {
        // buffer the orderbooks
    }

    @Override
    public void handleEvent(ScheduleEvent timer) {
        // print the orderbook once every 5 seconds and once every 10 seconds
        // on a separate thread
    }
}
