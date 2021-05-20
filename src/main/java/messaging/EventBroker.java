package messaging;

import source.data.LocalOrderBook;
import source.scheduling.ScheduleEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class EventBroker<T> {
    private BlockingQueue<T> eventQueue = new ArrayBlockingQueue<T>(1024);
    private List<EventListener> listenerList = new ArrayList<>();

    public void addEvent(T event) throws InterruptedException {
        eventQueue.put(event);
    }

    public void broadcast() throws InterruptedException {
        if (eventQueue.isEmpty()) {
            System.out.println("No events to broadcast.");
        } else {
            while (!eventQueue.isEmpty()) {
                T event = eventQueue.remove();
                sendToListeners(event);
            }
        }
    }

    public void sendToListeners(T event) throws InterruptedException {
        for (EventListener listener : listenerList) {
            if (event instanceof LocalOrderBook) {
                listener.handleEvent((LocalOrderBook) event);
            } else if (event instanceof ScheduleEvent) {
                listener.handleEvent((ScheduleEvent) event);
            }
        }
    }

    public void addListener(EventListener listener) {
        if (!listenerList.contains(listener)) {
            listenerList.add(listener);
        } else {
            System.out.println("Listener already exists in list.");
        }
    }

    public void removeListener(EventListener listener) {
        if (listenerList.contains(listener)) {
            listenerList.remove(listener);
        } else {
            System.out.println("Listener does not exist in list");
        }
    }
}
