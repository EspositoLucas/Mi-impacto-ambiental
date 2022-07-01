package dds.grupo4.tpimpacto.scheduler;

import org.quartz.*;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.text.ParseException;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.UUID;

@Component
public class CustomQuartzScheduler {

    // Explicacion del Cron: http://www.quartz-scheduler.org/documentation/quartz-2.3.0/tutorials/crontrigger.html

    private static final String JOB_GROUP = "JobGroup";
    private static final String TRIGGER_GROUP = "TriggerGroup";

    private final Scheduler scheduler;

    public CustomQuartzScheduler(Scheduler scheduler) throws SchedulerException, ParseException {
        this.scheduler = scheduler;

        scheduleRecordatorioEmail();
    }

    public void scheduleRecordatorioEmail() throws SchedulerException, ParseException {
        String jobDescription = "Job que envia recordatorios por mail";
        JobDetail jobDetail = buildJobDetail(RecordatorioRecomendacionesJob.class, JOB_GROUP, jobDescription, null);
        String triggerDescription = "Trigger para el Job que envia recordatorios por mail";
        ZonedDateTime startAt = ZonedDateTime.now();
        String strCronExpression = "0 * * * * ?";
        CronExpression cronExpression = new CronExpression(strCronExpression);
        Trigger trigger = buildTrigger(jobDetail, TRIGGER_GROUP, triggerDescription, startAt, cronExpression);
        scheduler.scheduleJob(jobDetail, trigger);
    }

    private JobDetail buildJobDetail(Class<? extends Job> jobClass, String jobGroup, String jobDescription, Map<?, ?> dataMap) {
        JobBuilder jobBuilder = JobBuilder.newJob(jobClass)
                .withIdentity(UUID.randomUUID().toString(), jobGroup)
                .withDescription(jobDescription);

        if (dataMap != null) {
            JobDataMap jobDataMap = new JobDataMap(dataMap);
            jobBuilder = jobBuilder.usingJobData(jobDataMap);
        }

        return jobBuilder.build();
    }

    private Trigger buildTrigger(JobDetail jobDetail, String triggerGroup, String triggerDescription, ZonedDateTime startAt, CronExpression jobCronExpression) {
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity(jobDetail.getKey().getName(), triggerGroup)
                .withDescription(triggerDescription)
                .startAt(Date.from(startAt.toInstant()))
                .withSchedule(CronScheduleBuilder.cronSchedule(jobCronExpression))
                .build();
    }

}
