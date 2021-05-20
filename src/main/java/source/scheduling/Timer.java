package source.scheduling;

import messaging.EventManager;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class Timer implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        EventManager eventManager = (EventManager) jobExecutionContext.getJobDetail().getJobDataMap().get("em");
        String tag = (String) jobExecutionContext.getJobDetail().getJobDataMap().get("tag");
        try {
            eventManager.publish(new ScheduleEvent(tag));
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
