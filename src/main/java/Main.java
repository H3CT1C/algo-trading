import algo.AnalyticManager;
import messaging.EventManager;
import org.quartz.SchedulerException;
import source.data.MarketDataManager;
import source.scheduling.SchedulerManager;
import algo.RiskWatcher;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws SchedulerException {
        EventManager eventManager = new EventManager();
        EventManager eventManager1= new EventManager();

        SchedulerManager schedulerManager = new SchedulerManager(eventManager);
        SchedulerManager schedulerManager1= new SchedulerManager(eventManager1);

        MarketDataManager marketDataManager = new MarketDataManager("btcusdt", eventManager);
        MarketDataManager marketDataManager1= new MarketDataManager("btcusdt", eventManager1);

        AnalyticManager analyticManager = new AnalyticManager(eventManager, schedulerManager,
                5, 10);
        RiskWatcher riskWatcher =new RiskWatcher(eventManager1,schedulerManager1,10);

        ExecutorService executorService = Executors.newFixedThreadPool(4);
        executorService.execute(marketDataManager);
        executorService.execute(analyticManager);
        executorService.execute(marketDataManager1);
        executorService.execute(riskWatcher);

    }
}
