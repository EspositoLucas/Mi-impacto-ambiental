package dds.grupo4.tpimpacto.services;

import dds.grupo4.tpimpacto.entities.medioTransporte.TipoServicioContratado;
import dds.grupo4.tpimpacto.repositories.TipoServicioContratadoRepository;
import org.springframework.stereotype.Service;

@Service
public class TipoServicioContratadoServiceImpl extends BaseServiceImpl<TipoServicioContratado, TipoServicioContratadoRepository> implements TipoServicioContratadoService {

    public TipoServicioContratadoServiceImpl(TipoServicioContratadoRepository tipoServicioContratadoRepository) {
        super(tipoServicioContratadoRepository);
    }

}
