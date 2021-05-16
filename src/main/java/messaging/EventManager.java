package messaging;

import com.binance.api.client.domain.market.OrderBook;

public interface EventManager {
    void publish(OrderBook orderBook);
    void publish(ScheduleEvent timer);
}
