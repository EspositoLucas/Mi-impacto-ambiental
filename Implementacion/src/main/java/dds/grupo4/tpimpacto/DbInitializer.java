package dds.grupo4.tpimpacto;

import dds.grupo4.tpimpacto.services.OrganizacionService;
import dds.grupo4.tpimpacto.services.TipoConsumoService;
import dds.grupo4.tpimpacto.services.UnidadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("!test")
@Component
@Slf4j
public class DbInitializer implements ApplicationRunner {

    private final UnidadService unidadService;
    private final TipoConsumoService tipoConsumoService;
    private final OrganizacionService organizacionService;

    public DbInitializer(UnidadService unidadService, TipoConsumoService tipoConsumoService, OrganizacionService organizacionService) {
        this.unidadService = unidadService;
        this.tipoConsumoService = tipoConsumoService;
        this.organizacionService = organizacionService;
    }

    @Override
    public void run(ApplicationArguments args) {
        log.info("Corriendo el DbInitializer");

        unidadService.seedData();
        tipoConsumoService.seedData();
        organizacionService.seedData();
    }
}
