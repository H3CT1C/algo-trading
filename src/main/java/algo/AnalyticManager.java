package algo;

import messaging.EventListener;
import messaging.EventManager;
import source.data.LocalOrderBook;
import source.scheduling.ScheduleEvent;
import source.scheduling.SchedulerManager;

import java.util.NavigableMap;
import java.util.TreeMap;

public class AnalyticManager implements EventListener, Runnable {
    private EventManager eventManager;
    private SchedulerManager schedulerManager;
    private SimpleMovingAverage sma1;
    private SimpleMovingAverage sma2;
    private NavigableMap<Long, LocalOrderBook> orderBookCache = new TreeMap<>();
    private long orderBookId = 0L;

    public AnalyticManager(EventManager eventManager, SchedulerManager schedulerManager, int period1, int period2) {
        this.eventManager = eventManager;
        this.schedulerManager = schedulerManager;
        this.sma1 = new SimpleMovingAverage(period1);
        this.sma2 = new SimpleMovingAverage(period2);
    }

    @Override
    public void handleEvent(LocalOrderBook orderBook) {
        orderBookCache.put(orderBookId, orderBook);
        orderBookId++;
    }

    @Override
    public void handleEvent(ScheduleEvent timer) {
        // print the orderbook once every 5 seconds and once every 10 seconds
        // on a separate thread
    }

    @Override
    public void run() {
    }
}
