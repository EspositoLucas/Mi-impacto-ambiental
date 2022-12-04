package dds.grupo4.tpimpacto.controllers;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import dds.grupo4.tpimpacto.dtos.OrganizacionDto;
import dds.grupo4.tpimpacto.dtos.base.ResponseWithResults;
import dds.grupo4.tpimpacto.services.OrganizacionService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class OrganizacionControllerLiviano {
    private final OrganizacionService organizacionService;

    private final Handlebars handlebars;

    public OrganizacionControllerLiviano(OrganizacionService organizacionService) {
        this.organizacionService = organizacionService;
        this.handlebars = new Handlebars();
    }

    @GetMapping(value = "/organizacionLiviano", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> obtenerVistaDeOrganizaciones() throws IOException {

        //validar accion en capa modelo según roles o usuario asociados al idSesion
        Template template = handlebars.compile("/templates/cliente_liviano_listar_organizaciones");
        ResponseWithResults<OrganizacionDto> organizaciones = organizacionService.getAllDtos();

        Map<String, Object> model = new HashMap<>();
        model.put("listaorganizaciones", organizaciones.getResults());

        String html = template.apply(model);

        return ResponseEntity.status(200).body(html);
    }
}
