package dds.grupo4.tpimpacto.services;

import dds.grupo4.tpimpacto.entities.medioTransporte.TipoServicioContratado;
import dds.grupo4.tpimpacto.repositories.TipoServicioContratadoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TipoServicioContratadoServiceImpl implements TipoServicioContratadoService {

    private final TipoServicioContratadoRepository tipoServicioContratadoRepository;

    public TipoServicioContratadoServiceImpl(TipoServicioContratadoRepository tipoServicioContratadoRepository) {
        this.tipoServicioContratadoRepository = tipoServicioContratadoRepository;
    }

    @Override
    @Transactional
    public void save(TipoServicioContratado obj) {
        if (tipoServicioContratadoRepository.getAll().contains(obj)) {
            tipoServicioContratadoRepository.merge(obj);
        } else {
            tipoServicioContratadoRepository.save(obj);
        }
    }

    @Override
    @Transactional
    public List<TipoServicioContratado> getAll() {
        return tipoServicioContratadoRepository.getAll();
    }
}
