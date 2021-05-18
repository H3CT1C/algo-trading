package messaging;

import com.binance.api.client.domain.market.OrderBook;
import source.ScheduleEvent;

public interface EventManager {
    void publish(OrderBook orderBook);
    void publish(ScheduleEvent timer);
}
