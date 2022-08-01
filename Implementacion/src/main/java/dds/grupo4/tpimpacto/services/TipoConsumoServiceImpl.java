package dds.grupo4.tpimpacto.services;

import dds.grupo4.tpimpacto.entities.medicion.TipoConsumo;
import dds.grupo4.tpimpacto.repositories.TipoConsumoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TipoConsumoServiceImpl extends BaseServiceImpl<TipoConsumo, TipoConsumoRepository> implements TipoConsumoService {

    public TipoConsumoServiceImpl(TipoConsumoRepository tipoConsumoRepository) {
        super(tipoConsumoRepository);
    }

    @Override
    @Transactional
    public Optional<TipoConsumo> getByNombre(String nombreTipo) {
        return repository.getByNombre(nombreTipo);
    }

}
