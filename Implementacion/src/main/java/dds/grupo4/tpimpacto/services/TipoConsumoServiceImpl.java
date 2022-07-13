package dds.grupo4.tpimpacto.services;

import java.util.List;
import java.util.Optional;

import dds.grupo4.tpimpacto.entities.medicion.TipoConsumo;
import dds.grupo4.tpimpacto.repositories.TipoConsumoRepository;

public class TipoConsumoServiceImpl implements TipoConsumoService {

    private final TipoConsumoRepository tipoConsumoRepository;

    public TipoConsumoServiceImpl(TipoConsumoRepository tipoConsumoRepository) {
        this.tipoConsumoRepository = tipoConsumoRepository;
    }

    @Override
    public void save(TipoConsumo tipoConsumo) {
        Optional<TipoConsumo> tipoConsumoConMismoNombreOptional = getByNombre(tipoConsumo.getNombre());
        if (tipoConsumoConMismoNombreOptional.isPresent()) {
            // Si ya existe un TipoConsumo con el mismo nombre, entonces se actualiza el
            // factor de emision
            TipoConsumo tipoConsumoConMismoNombre = tipoConsumoConMismoNombreOptional.get();
            tipoConsumoConMismoNombre.setFactorEmision(tipoConsumo.getFactorEmision());
            tipoConsumoRepository.update(tipoConsumoConMismoNombre);
        } else {
            tipoConsumoRepository.save(tipoConsumo);
        }
    }

    @Override
    public List<TipoConsumo> getAll() {
        return tipoConsumoRepository.getAll();
    }

    @Override
    public Optional<TipoConsumo> getByNombre(String nombreTipo) {
        return tipoConsumoRepository.getByNombre(nombreTipo);
    }

}
