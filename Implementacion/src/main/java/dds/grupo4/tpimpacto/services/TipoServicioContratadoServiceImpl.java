package dds.grupo4.tpimpacto.services;

import dds.grupo4.tpimpacto.dtos.CrearTipoServicioContratadoRequest;
import dds.grupo4.tpimpacto.dtos.base.BaseResponse;
import dds.grupo4.tpimpacto.entities.medioTransporte.TipoServicioContratado;
import dds.grupo4.tpimpacto.repositories.TipoServicioContratadoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TipoServicioContratadoServiceImpl extends BaseServiceImpl<TipoServicioContratado, TipoServicioContratadoRepository> implements TipoServicioContratadoService {

    public TipoServicioContratadoServiceImpl(TipoServicioContratadoRepository tipoServicioContratadoRepository) {
        super(tipoServicioContratadoRepository);
    }

    @Override
    @Transactional
    public BaseResponse crearTipoServicioContratado(CrearTipoServicioContratadoRequest request) {
        if (repository.getByNombre(request.getNombre()).isPresent()) {
            return new BaseResponse(HttpStatus.BAD_REQUEST, "Ya existe un TipoServicioContratado con ese nombre");
        }
        TipoServicioContratado tipoServicioContratado = new TipoServicioContratado(request.getNombre());
        this.save(tipoServicioContratado);
        return new BaseResponse(HttpStatus.CREATED);
    }

}
