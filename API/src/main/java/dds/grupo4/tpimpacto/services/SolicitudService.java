package dds.grupo4.tpimpacto.services;

import dds.grupo4.tpimpacto.entities.organizacion.Solicitud;
import dds.grupo4.tpimpacto.repositories.SolicitudRepository;
import dds.grupo4.tpimpacto.services.base.BaseService;
import org.springframework.stereotype.Service;

@Service
public class SolicitudService extends BaseService<Solicitud, SolicitudRepository> {

    public SolicitudService(SolicitudRepository repository) {
        super(repository);
    }

}
