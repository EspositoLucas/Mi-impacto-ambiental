package dds.grupo4.tpimpacto.scheduler;

import dds.grupo4.tpimpacto.services.NotificationSender;
import dds.grupo4.tpimpacto.services.OrganizacionService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.util.List;

//@Component
public class RecordatorioRecomendacionesJob extends QuartzJobBean {

    private final OrganizacionService organizacionService;
    private final List<NotificationSender> notificationSenders; // Inyecta todas las implementaciones de NotificationSender (por ejemplo Mail o WhatsApp)

    public RecordatorioRecomendacionesJob(OrganizacionService organizacionService, List<NotificationSender> notificationSenders) {
        this.organizacionService = organizacionService;
        this.notificationSenders = notificationSenders;
    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        List<String> mailsContactos = organizacionService.getMailsDeContactos();

        String asunto = "Guia de Recomendaciones";
        String cuerpo = "<p>Haga click <a href=\"https://www.google.com\">aqui</a> para ver la guia de recomendaciones.</p>";

        notificationSenders.forEach(notificationSender -> {
            try {
                notificationSender.send(mailsContactos, asunto, cuerpo);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        });
    }

}
