package algo;

import messaging.EventListener;
import source.LocalOrderBook;
import source.ScheduleEvent;

public class AnalyticManager implements EventListener {
    @Override
    public void handleEvent(LocalOrderBook orderBook) {
        // buffer the orderbooks
    }

    @Override
    public void handleEvent(ScheduleEvent timer) {
        // print the orderbook once every 5 seconds and once every 10 seconds
        // on a separate thread
    }
}
