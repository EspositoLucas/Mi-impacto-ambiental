package dds.grupo4.tpimpacto.scheduler;

import dds.grupo4.tpimpacto.services.NotificationContactsFinder;
import dds.grupo4.tpimpacto.services.NotificationSender;
import dds.grupo4.tpimpacto.services.OrganizacionService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.util.List;

@Component
@Setter
@Slf4j
@DisallowConcurrentExecution
public class RecordatorioRecomendacionesJob extends QuartzJobBean {

    public static final String NOTIFICATION_SENDER_MAP_KEY = "NotificationSender";
    public static final String NOTIFICATION_CONTACTS_FINDER_MAP_KEY = "NotificationContactsFinder";

    private final OrganizacionService organizacionService;

    public RecordatorioRecomendacionesJob(OrganizacionService organizacionService) {
        this.organizacionService = organizacionService;
    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.info("!! Empezando Job de envio de recordatorios !!");

        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        NotificationSender notificationSender = (NotificationSender) jobDataMap.get(NOTIFICATION_SENDER_MAP_KEY);
        NotificationContactsFinder notificationContactsFinder = (NotificationContactsFinder) jobDataMap.get(NOTIFICATION_CONTACTS_FINDER_MAP_KEY);

        // Mails o numeros de telefono, dependiendo de si es por Mail o por WhatsApp
        List<String> destinos = notificationContactsFinder.findContacts(organizacionService);

        String asunto = "Guia de Recomendaciones";
        String cuerpo = "<p>Haga click <a href=\"https://www.google.com\">aqui</a> para ver la guia de recomendaciones.</p>";

        try {
            notificationSender.send(destinos, asunto, cuerpo);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        log.info("!! Job de recordatorios finalizado !!");
    }

}
