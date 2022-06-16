package dds.grupo4.tpimpacto.repositories;

import dds.grupo4.tpimpacto.entities.TipoServicioContratado;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TipoServicioContratadoRepositoryImpl implements TipoServicioContratadoRepository {

    private final List<TipoServicioContratado> tiposServicioContratado = new ArrayList<>();

    @Override
    public void save(TipoServicioContratado obj) {
        tiposServicioContratado.add(obj);
    }

    @Override
    public void update(TipoServicioContratado obj) {
        int index = tiposServicioContratado.indexOf(obj);
        tiposServicioContratado.set(index, obj);
    }

    @Override
    public List<TipoServicioContratado> getAll() {
        return tiposServicioContratado;
    }
}
