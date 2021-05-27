package algo;


import messaging.EventListener;
import messaging.EventManager;
import org.quartz.SchedulerException;
import source.data.LocalOrderBook;
import source.scheduling.ScheduleEvent;
import source.scheduling.SchedulerManager;

import java.util.NavigableMap;
import java.util.TreeMap;


public class RiskWatcher implements EventListener, Runnable {
    private EventManager eventManager;
    private SchedulerManager schedulerManager;
    private double imbalance;
    private int period3;
    private LocalOrderBook orderBookCache;

    public RiskWatcher(EventManager eventManager, SchedulerManager schedulerManager,int period3){
        this.eventManager = eventManager;
        this.schedulerManager = schedulerManager;
        this.period3 = period3;
        this.imbalance= 0;
    }
    public void handleEvent(LocalOrderBook orderBook) {
        this.imbalance= computeImbalance(orderBook);
    }


    public void handleEvent(ScheduleEvent timer){
        String tag = timer.getTag();
        if(tag.equals("orderImbalance")){
            if(imbalance<0){
                System.out.println("SELL!");
            }
            else if(imbalance>0){
                System.out.println("BUY");
            }
        }
        else{
           return;
        }

    }

    public Double computeImbalance(LocalOrderBook orderBook){
        Double bestbidQuantity = orderBook.getBestBid().getValue().doubleValue();
        Double bestaskQuantity = orderBook.getBestAsk().getValue().doubleValue();
        Double Imbalance = (bestbidQuantity-bestaskQuantity)/(bestaskQuantity+bestbidQuantity);
        return Imbalance;
    }

    public void run(){
        try {
            this.schedulerManager.periodicCallBack(period3 * 500, "orderImbalance");
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
