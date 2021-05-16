package algo;

import com.binance.api.client.domain.market.OrderBook;
import messaging.EventListener;

public class AnalyticManager implements EventListener {
    @Override
    public void handleEvent(OrderBook orderBook) {
    }

    @Override
    public void handleEvent(ScheduleEvent timer) {
    }
}
