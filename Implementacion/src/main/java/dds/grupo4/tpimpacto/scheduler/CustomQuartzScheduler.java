package dds.grupo4.tpimpacto.scheduler;

import dds.grupo4.tpimpacto.services.EmailContactsFinder;
import dds.grupo4.tpimpacto.services.NotificationSender;
import lombok.Setter;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Date;
import java.text.ParseException;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Configuration
@Setter
public class CustomQuartzScheduler {

    // Explicacion del Cron: http://www.quartz-scheduler.org/documentation/quartz-2.3.0/tutorials/crontrigger.html
    private static final String CRON_CADA_UN_MINUTO = "0 * * * * ?";

    private static final String JOB_GROUP = "JobGroup";
    private static final String TRIGGER_GROUP = "TriggerGroup";

    @Autowired
    @Qualifier("emailSender")
    private NotificationSender emailSender;

    @Bean("jobDetailRecordatorioRecomendacionesPorEmail")
    public JobDetail jobDetailRecordatorioRecomendacionesPorEmail() {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put(RecordatorioRecomendacionesJob.NOTIFICATION_SENDER_MAP_KEY, emailSender);
        dataMap.put(RecordatorioRecomendacionesJob.NOTIFICATION_CONTACTS_FINDER_MAP_KEY, new EmailContactsFinder());
        String jobDescription = "Job que envia recordatorios por mail";
        return buildJobDetail(RecordatorioRecomendacionesJob.class, JOB_GROUP, jobDescription, dataMap);
    }

    @Bean
    public Trigger triggerRecordatorioRecomendacionesPorEmail(@Qualifier("jobDetailRecordatorioRecomendacionesPorEmail") JobDetail jobDetailRecordatorioRecomendacionesPorEmail) throws ParseException {
        String triggerDescription = "Trigger para el Job que envia recordatorios por mail";
        ZonedDateTime startAt = ZonedDateTime.now();
        CronExpression cronExpression = new CronExpression(CRON_CADA_UN_MINUTO);
        return buildTrigger(jobDetailRecordatorioRecomendacionesPorEmail, TRIGGER_GROUP, triggerDescription, startAt, cronExpression);
    }

    private JobDetail buildJobDetail(Class<? extends Job> jobClass, String jobGroup, String jobDescription, Map<?, ?> dataMap) {
        JobBuilder jobBuilder = JobBuilder.newJob(jobClass)
                .withIdentity(UUID.randomUUID().toString(), jobGroup)
                .withDescription(jobDescription)
                .storeDurably(true);

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
