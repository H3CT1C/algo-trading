package messaging;

import source.LocalOrderBook;
import source.ScheduleEvent;

public interface EventListener {
    void handleEvent(LocalOrderBook orderBook);
    void handleEvent(ScheduleEvent timer);
}
