package dds.grupo4.tpimpacto.services;

import dds.grupo4.tpimpacto.entities.medicion.TipoConsumo;
import dds.grupo4.tpimpacto.repositories.TipoConsumoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
public class TipoConsumoService extends BaseService<TipoConsumo, TipoConsumoRepository> {

    public TipoConsumoService(TipoConsumoRepository tipoConsumoRepository) {
        super(tipoConsumoRepository);
    }

    @Transactional
    public void seedData() {
        if (this.hasData()) {
            log.debug("Ya existen registros de TipoConsumo creados");
            return;
        }

        log.debug("Se crean los TipoConsumo iniciales");
    }

    @Transactional
    public Optional<TipoConsumo> getByNombre(String nombreTipo) {
        return repository.getByNombre(nombreTipo);
    }

}
