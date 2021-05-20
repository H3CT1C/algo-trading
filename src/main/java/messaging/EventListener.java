package messaging;

import source.data.LocalOrderBook;
import source.scheduling.ScheduleEvent;

public interface EventListener {
    void handleEvent(LocalOrderBook orderBook);
    void handleEvent(ScheduleEvent timer);
}
