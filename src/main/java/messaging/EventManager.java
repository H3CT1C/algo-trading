package messaging;

import com.binance.api.client.domain.market.OrderBook;
import source.ScheduleEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class EventManager {
    private BlockingQueue<OrderBook> orderBooks;
    private List<EventListener> listeners;
    public EventManager() {
        this.orderBooks = new ArrayBlockingQueue<OrderBook>(100);
        this.listeners = new ArrayList<EventListener>();
    }
    void publish(OrderBook orderBook) {
        orderBooks.add(orderBook);
    }

    void publish(ScheduleEvent timer) {

    }

    void addListener(EventListener listener) {
        if (!listeners.contains(listener)) {
            listeners.add(listener);
        }
    }

    void removeListener(EventListener listener) {
        if (listeners.contains(listener)) {
            listeners.remove(listener);
        }
    }
}
