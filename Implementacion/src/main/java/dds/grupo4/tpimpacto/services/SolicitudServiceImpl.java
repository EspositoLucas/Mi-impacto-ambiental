package dds.grupo4.tpimpacto.services;

import dds.grupo4.tpimpacto.entities.organizacion.Solicitud;
import dds.grupo4.tpimpacto.repositories.SolicitudRepository;
import org.springframework.stereotype.Service;

@Service
public class SolicitudServiceImpl extends BaseServiceImpl<Solicitud, SolicitudRepository> implements SolicitudService {

    public SolicitudServiceImpl(SolicitudRepository repository) {
        super(repository);
    }

}
