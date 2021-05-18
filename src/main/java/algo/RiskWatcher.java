package algo;

import com.binance.api.client.domain.market.OrderBook;
import messaging.EventListener;
import source.ScheduleEvent;

public class RiskWatcher implements EventListener {
    @Override
    public void handleEvent(OrderBook orderBook) {
    }

    @Override
    public void handleEvent(ScheduleEvent timer) {
    }
}
