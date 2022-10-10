package dds.grupo4.tpimpacto.services;

import dds.grupo4.tpimpacto.entities.geo.Localidad;
import dds.grupo4.tpimpacto.repositories.LocalidadRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LocalidadService extends BaseService<Localidad, LocalidadRepository> {
    public LocalidadService(LocalidadRepository repository) {
        super(repository);
    }

    @Transactional
    public Localidad getByNombre(String nombre) {
        return repository.getByNombre(nombre);
    }
}
