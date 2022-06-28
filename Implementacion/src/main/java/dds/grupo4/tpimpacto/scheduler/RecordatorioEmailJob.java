package dds.grupo4.tpimpacto.scheduler;

import dds.grupo4.tpimpacto.services.EmailService;
import dds.grupo4.tpimpacto.services.OrganizacionService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.util.List;

@Component
public class RecordatorioEmailJob extends QuartzJobBean {

    private final OrganizacionService organizacionService;
    private final EmailService emailService;

    public RecordatorioEmailJob(OrganizacionService organizacionService, EmailService emailService) {
        this.organizacionService = organizacionService;
        this.emailService = emailService;
    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        List<String> mailsContactos = organizacionService.getMailsDeContactos();

        String asunto = "Guia de Recomendaciones";
        String cuerpo = "<p>Haga click <a href=\"https://www.google.com\">aqui</a> para ver la guia de recomendaciones.</p>";

        try {
            emailService.send(mailsContactos, asunto, cuerpo);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}
