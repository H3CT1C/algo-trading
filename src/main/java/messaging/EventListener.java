package messaging;

import com.binance.api.client.domain.market.OrderBook;
import source.ScheduleEvent;

public interface EventListener {
    void handleEvent(OrderBook orderBook);
    void handleEvent(ScheduleEvent timer);
}
