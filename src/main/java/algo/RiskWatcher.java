package algo;

import messaging.EventListener;
import source.LocalOrderBook;
import source.ScheduleEvent;

public class RiskWatcher implements EventListener {
    @Override
    public void handleEvent(LocalOrderBook orderBook) {
    }

    @Override
    public void handleEvent(ScheduleEvent timer) {
    }
}
