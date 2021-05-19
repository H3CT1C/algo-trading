package messaging;

import com.binance.api.client.domain.event.AggTradeEvent;
import source.LocalOrderBook;
import source.ScheduleEvent;

public class EventManager {
    private EventBroker<LocalOrderBook> orderBookBroker = new EventBroker<>();
    private EventBroker<AggTradeEvent> aggTradeBroker = new EventBroker<>();
    private EventBroker<ScheduleEvent> scheduleEventBroker = new EventBroker<>();

    void publish(LocalOrderBook orderBook) throws InterruptedException {
        orderBookBroker.addEvent(orderBook);
    }

    public void publish(AggTradeEvent aggTradeEvent) throws InterruptedException {
        aggTradeBroker.addEvent(aggTradeEvent);
    }

    void publish(ScheduleEvent timer) throws InterruptedException {
        scheduleEventBroker.addEvent(timer);
    }

    void addListener(EventListener listener) {
        orderBookBroker.addListener(listener);
        aggTradeBroker.addListener(listener);
        scheduleEventBroker.addListener(listener);
    }

    void removeListener(EventListener listener) {
        orderBookBroker.removeListener(listener);
        aggTradeBroker.removeListener(listener);
        scheduleEventBroker.removeListener(listener);
    }

    public EventBroker<LocalOrderBook> getOrderBookBroker() {
        return orderBookBroker;
    }

    public EventBroker<AggTradeEvent> getAggTradeBroker() {
        return aggTradeBroker;
    }

    public EventBroker<ScheduleEvent> getScheduleEventBroker() {
        return scheduleEventBroker;
    }
}
