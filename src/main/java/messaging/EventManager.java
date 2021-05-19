package messaging;

import source.LocalOrderBook;
import source.ScheduleEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class EventManager {
    private BlockingQueue<LocalOrderBook> orderBooks;
    private List<EventListener> listeners;
    public EventManager() {
        this.orderBooks = new ArrayBlockingQueue<LocalOrderBook>(100);
        this.listeners = new ArrayList<EventListener>();
    }

    // pass the events to the listeners

    void publish(LocalOrderBook orderBook) {
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
