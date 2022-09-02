package dds.grupo4.tpimpacto;

import dds.grupo4.tpimpacto.services.TipoConsumoService;
import dds.grupo4.tpimpacto.services.TipoUnidadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("!test")
@Component
@Slf4j
public class DbInitializer implements ApplicationRunner {

    private final TipoUnidadService tipoUnidadService;
    private final TipoConsumoService tipoConsumoService;

    public DbInitializer(TipoUnidadService tipoUnidadService, TipoConsumoService tipoConsumoService) {
        this.tipoUnidadService = tipoUnidadService;
        this.tipoConsumoService = tipoConsumoService;
    }

    @Override
    public void run(ApplicationArguments args) {
        log.info("Corriendo el DbInitializer");

        tipoUnidadService.seedData();

        tipoConsumoService.seedData();
    }
}
