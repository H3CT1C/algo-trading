import algo.AnalyticManager;
import messaging.EventManager;
import org.quartz.SchedulerException;
import source.data.MarketDataManager;
import source.scheduling.SchedulerManager;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws SchedulerException {
        EventManager eventManager = new EventManager();
        SchedulerManager schedulerManager = new SchedulerManager(eventManager);
        MarketDataManager marketDataManager = new MarketDataManager("btcusdt", eventManager);
        AnalyticManager analyticManager = new AnalyticManager(eventManager, schedulerManager,
                5, 10);

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(marketDataManager);
        executorService.execute(analyticManager);
    }
}
