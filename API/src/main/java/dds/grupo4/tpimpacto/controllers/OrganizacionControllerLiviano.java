package dds.grupo4.tpimpacto.controllers;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import dds.grupo4.tpimpacto.config.AppConfig;
import dds.grupo4.tpimpacto.dtos.OrganizacionDto;
import dds.grupo4.tpimpacto.dtos.SolicitudDto;
import dds.grupo4.tpimpacto.dtos.base.ResponseWithResults;
import dds.grupo4.tpimpacto.entities.seguridad.Usuario;
import dds.grupo4.tpimpacto.security.CurrentUserService;
import dds.grupo4.tpimpacto.services.OrganizacionService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/liviano/organizacion")
public class OrganizacionControllerLiviano {
    private final OrganizacionService organizacionService;
    private final CurrentUserService currentUserService;
    private final AppConfig appConfig;
    private final Handlebars handlebars;

    public OrganizacionControllerLiviano(OrganizacionService organizacionService, CurrentUserService currentUserService, AppConfig appConfig) {
        this.organizacionService = organizacionService;
        this.currentUserService = currentUserService;
        this.appConfig = appConfig;
        this.handlebars = new Handlebars();
    }

    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> obtenerVistaDeOrganizaciones() throws IOException {
        Template template = handlebars.compile("/templates/listar_organizaciones");
        ResponseWithResults<OrganizacionDto> organizaciones = organizacionService.getAllDtos();

        Map<String, Object> model = new HashMap<>();
        model.put("listaOrganizaciones", organizaciones.getResults());

        String html = template.apply(model);

        return ResponseEntity.status(200).body(html);
    }

    // @RequireAdminRole
    @GetMapping(value = "/solicitudes", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> obtenerVistaDeSolicitudes() throws IOException {
        Usuario currentUser = currentUserService.get();
        long idOrganizacion = currentUser.getMiembro().getOrganizacion().getId();

        // String baseUrl = appConfig.getApiUrl();

        // handlebars.registerHelper("apiUrl", );

        Template template = handlebars.compile("/templates/aceptar_solicitudes");
        ResponseWithResults<SolicitudDto> solicitudes = organizacionService.listarSolicitudes(idOrganizacion);

        Map<String, Object> model = new HashMap<>();
        model.put("solicitudes", solicitudes.getResults());

        String html = template.apply(model);

        return ResponseEntity.status(200).body(html);
    }
}
