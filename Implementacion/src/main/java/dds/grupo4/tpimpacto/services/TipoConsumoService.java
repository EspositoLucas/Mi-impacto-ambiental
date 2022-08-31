package dds.grupo4.tpimpacto.services;

import dds.grupo4.tpimpacto.entities.medicion.TipoConsumo;
import dds.grupo4.tpimpacto.repositories.TipoConsumoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TipoConsumoService extends BaseService<TipoConsumo, TipoConsumoRepository> {

    public TipoConsumoService(TipoConsumoRepository tipoConsumoRepository) {
        super(tipoConsumoRepository);
    }

    @Transactional
    public Optional<TipoConsumo> getByNombre(String nombreTipo) {
        return repository.getByNombre(nombreTipo);
    }

}
