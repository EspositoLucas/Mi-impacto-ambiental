package dds.grupo4.tpimpacto.services;

import dds.grupo4.tpimpacto.entities.organizacion.Miembro;
import dds.grupo4.tpimpacto.repositories.MiembroRepository;
import org.springframework.stereotype.Service;

@Service
public class MiembroServiceImpl extends BaseServiceImpl<Miembro, MiembroRepository> implements MiembroService {

    public MiembroServiceImpl(MiembroRepository miembroRepository) {
        super(miembroRepository);
    }

    /*
    public CrearMiembro.Response crearMiembro(CrearMiembro.Request request) {

    }
    */
}
