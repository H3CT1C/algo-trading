package source;

import messaging.EventManager;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class SchedulerManager implements Runnable {

    private Scheduler scheduler;
    private EventManager eventManager;

    public SchedulerManager() throws SchedulerException {
        this.scheduler = StdSchedulerFactory.getDefaultScheduler();
    }
    void periodicCallBack(int intervalMillis, ScheduleEvent event) throws SchedulerException {
        Trigger trigger = TriggerBuilder.newTrigger()
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInMilliseconds(intervalMillis)
                        .repeatForever())
                .build();
        JobDetail jobDetail = JobBuilder.newJob(event.getClass()).build();
        scheduler.scheduleJob(jobDetail, trigger);
    }

    @Override
    public void run() {
    }
}
