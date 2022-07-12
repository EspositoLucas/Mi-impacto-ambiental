package dds.grupo4.tpimpacto.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import dds.grupo4.tpimpacto.entities.TipoConsumo;

public class TipoConsumoRepositoryImpl implements TipoConsumoRepository {

    private final List<TipoConsumo> tiposConsumo = new ArrayList<>();

    @Override
    public void save(TipoConsumo tipoConsumo) {
        tiposConsumo.add(tipoConsumo);
    }

    @Override
    public void update(TipoConsumo tipoConsumo) {
        int index = tiposConsumo.indexOf(tipoConsumo);
        tiposConsumo.set(index, tipoConsumo);
    }

    @Override
    public List<TipoConsumo> getAll() {
        return tiposConsumo;
    }

    @Override
    public Optional<TipoConsumo> getByNombre(String nombreTipo) {
        return tiposConsumo.stream()
                .filter(tipoConsumo -> tipoConsumo.getNombre().equals(nombreTipo))
                .findFirst();
    }

}
