package dds.grupo4.tpimpacto.controllers;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import dds.grupo4.tpimpacto.dtos.OrganizacionDto;
import dds.grupo4.tpimpacto.dtos.base.ResponseWithResults;
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

    private final Handlebars handlebars;

    public OrganizacionControllerLiviano(OrganizacionService organizacionService) {
        this.organizacionService = organizacionService;
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
}
