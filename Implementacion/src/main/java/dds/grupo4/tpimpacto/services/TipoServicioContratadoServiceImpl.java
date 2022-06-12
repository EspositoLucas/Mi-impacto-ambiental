package dds.grupo4.tpimpacto.services;

import dds.grupo4.tpimpacto.entities.TipoServicioContratado;
import dds.grupo4.tpimpacto.repositories.TipoServicioContratadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoServicioContratadoServiceImpl implements TipoServicioContratadoService {

    private final TipoServicioContratadoRepository tipoServicioContratadoRepository;

    public TipoServicioContratadoServiceImpl(TipoServicioContratadoRepository tipoServicioContratadoRepository) {
        this.tipoServicioContratadoRepository = tipoServicioContratadoRepository;
    }

    @Override
    public void save(TipoServicioContratado obj) {
        tipoServicioContratadoRepository.save(obj);
    }

    @Override
    public List<TipoServicioContratado> getAll() {
        return tipoServicioContratadoRepository.getAll();
    }
}
