package source.scheduling;

import messaging.EventManager;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class SchedulerManager {

    private Scheduler scheduler;
    private EventManager eventManager;

    public SchedulerManager(EventManager eventManager) throws SchedulerException {
        this.scheduler = StdSchedulerFactory.getDefaultScheduler();
        this.eventManager = eventManager;
    }

    public void periodicCallBack(int intervalMillis, String tag) throws SchedulerException {
        Trigger trigger = TriggerBuilder.newTrigger()
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInMilliseconds(intervalMillis)
                        .repeatForever())
                .build();
        JobDetail timer = JobBuilder.newJob(Timer.class).build();
        timer.getJobDataMap().put("em", eventManager);
        timer.getJobDataMap().put("tag", tag);
        scheduler.scheduleJob(timer, trigger);
        scheduler.start();
    }
}
