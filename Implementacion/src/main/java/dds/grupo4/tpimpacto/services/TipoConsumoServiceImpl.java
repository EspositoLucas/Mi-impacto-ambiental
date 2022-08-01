package dds.grupo4.tpimpacto.services;

import dds.grupo4.tpimpacto.entities.medicion.TipoConsumo;
import dds.grupo4.tpimpacto.repositories.TipoConsumoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TipoConsumoServiceImpl implements TipoConsumoService {

    private final TipoConsumoRepository tipoConsumoRepository;

    public TipoConsumoServiceImpl(TipoConsumoRepository tipoConsumoRepository) {
        this.tipoConsumoRepository = tipoConsumoRepository;
    }

    @Override
    @Transactional
    public void save(TipoConsumo tipoConsumo) {
        tipoConsumoRepository.save(tipoConsumo);
    }

    @Override
    @Transactional
    public List<TipoConsumo> getAll() {
        return tipoConsumoRepository.getAll();
    }

    @Override
    @Transactional
    public Optional<TipoConsumo> getByNombre(String nombreTipo) {
        return tipoConsumoRepository.getByNombre(nombreTipo);
    }

}
