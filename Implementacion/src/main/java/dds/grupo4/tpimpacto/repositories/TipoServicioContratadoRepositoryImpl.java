package dds.grupo4.tpimpacto.repositories;

import dds.grupo4.tpimpacto.entities.TipoServicioContratado;

import java.util.ArrayList;
import java.util.List;

public class TipoServicioContratadoRepositoryImpl implements TipoServicioContratadoRepository {

    private final List<TipoServicioContratado> tiposServicioContratado = new ArrayList<>();

    @Override
    public void save(TipoServicioContratado obj) {
        tiposServicioContratado.add(obj);
    }

    @Override
    public List<TipoServicioContratado> getAll() {
        return tiposServicioContratado;
    }
}
