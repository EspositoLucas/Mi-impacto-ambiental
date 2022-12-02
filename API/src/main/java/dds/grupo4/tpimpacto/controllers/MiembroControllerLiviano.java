package dds.grupo4.tpimpacto.controllers;

import antlr.collections.List;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import dds.grupo4.tpimpacto.dtos.MiembroDto;
import dds.grupo4.tpimpacto.dtos.base.ResponseWithResults;
import dds.grupo4.tpimpacto.entities.organizacion.Miembro;
import dds.grupo4.tpimpacto.services.MiembroService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class MiembroControllerLiviano {

    private final MiembroService miembroService;

    private final Handlebars handlebars;

    public MiembroControllerLiviano(MiembroService miembroService) {
        this.miembroService = miembroService;
        this.handlebars = new Handlebars() ;
    }
    @GetMapping(value = "/miembroLiviano", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> obtenerVistaDeMiembros() throws IOException {

        //validar accion en capa modelo según roles o usuario asociados al idSesion
        Template template = handlebars.compile("/templates/cliente_liviano_listar_miembros");
        ResponseWithResults miembros = miembroService.listarMiembros();

        Map<String, Object> model = new HashMap<>();
        model.put("listamiembros", miembros.getResults());

        String html = template.apply(model);

        return ResponseEntity.status(200).body(html);
    }
}
