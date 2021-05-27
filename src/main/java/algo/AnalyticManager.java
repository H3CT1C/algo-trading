package algo;

import messaging.EventListener;
import messaging.EventManager;
import org.quartz.SchedulerException;
import source.data.LocalOrderBook;
import source.scheduling.ScheduleEvent;
import source.scheduling.SchedulerManager;

import java.util.NavigableMap;
import java.util.TreeMap;

public class AnalyticManager implements EventListener, Runnable {
    private EventManager eventManager;
    private SchedulerManager schedulerManager;
    private int period1;
    private int period2;

    private SimpleMovingAverage sma1;
    private SimpleMovingAverage sma2;
//    private NavigableMap<Long, LocalOrderBook> orderBookCache = new TreeMap<>();
    private LocalOrderBook orderBookCache;
    private long orderBookId = 0L;
    private RiskWatcher riskWatcher;



    public AnalyticManager(EventManager eventManager, SchedulerManager schedulerManager, int period1, int period2) {
        this.eventManager = eventManager;
        this.schedulerManager = schedulerManager;
        this.period1 = period1;
        this.period2 = period2;
        this.sma1 = new SimpleMovingAverage(period1);
        this.sma2 = new SimpleMovingAverage(period2);

    }

    @Override
    public void handleEvent(LocalOrderBook orderBook) {
//        orderBookCache.put(orderBookId, orderBook);
        orderBookId++;
        orderBookCache = orderBook;
    }

    @Override
    public void handleEvent(ScheduleEvent timer) {
        if (timer.getTag().equals("sma1")) {
            sma1.addValue(Math.weightedAverage(orderBookCache));
            System.out.println("sma1: " + sma1.getMovingAverage());
        } else if (timer.getTag().equals("sma2")) {
            sma2.addValue(Math.weightedAverage(orderBookCache));
            System.out.println("sma2: " + sma2.getMovingAverage());
        }



    }


    public Double computeImbalance(LocalOrderBook orderBook){
        Double bestbidQuantity = orderBook.getBestBid().getValue().doubleValue();
        Double bestaskQuantity = orderBook.getBestAsk().getValue().doubleValue();
        Double Imbalance = (bestbidQuantity-bestaskQuantity)/(bestaskQuantity+bestbidQuantity);
        return Imbalance;
    }


    @Override
    public void run() {
        try {
            this.schedulerManager.periodicCallBack(period1 * 500, "sma1");
            this.schedulerManager.periodicCallBack(period2 * 500, "sma2");
        } catch (SchedulerException ex) {
            ex.printStackTrace();
        }
        while (true) {
            try {
                handleEvent(eventManager.getOrderBookBroker().get());
                handleEvent(eventManager.getScheduleEventBroker().get());
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
