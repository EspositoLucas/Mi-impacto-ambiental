package dds.grupo4.tpimpacto.services;

import dds.grupo4.tpimpacto.dtos.DireccionDto;
import dds.grupo4.tpimpacto.entities.geo.Localidad;
import dds.grupo4.tpimpacto.entities.trayecto.Direccion;
import dds.grupo4.tpimpacto.repositories.DireccionRepository;
import dds.grupo4.tpimpacto.repositories.LocalidadRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class DireccionService extends BaseService<Direccion, DireccionRepository> {

    private final LocalidadRepository localidadRepository;

    public DireccionService(DireccionRepository repository, LocalidadRepository localidadRepository) {
        super(repository);
        this.localidadRepository = localidadRepository;
    }

    @Transactional
    public Direccion getOrCreateDireccion(DireccionDto direccionDto) {
        Optional<Direccion> direccionOptional;
        if (direccionDto.getId() != null && direccionDto.getId() != 0) {
            Direccion direccion = repository.getById(direccionDto.getId());
            direccionOptional = Optional.ofNullable(direccion);
        } else {
            direccionOptional = repository.getByDireccionEntera(
                    direccionDto.getCalle(),
                    direccionDto.getAltura(),
                    direccionDto.getLocalidad().getId()
            );
        }

        if (direccionOptional.isPresent()) {
            return direccionOptional.get();
        }

        Direccion direccion = new Direccion(direccionDto.getCalle(), direccionDto.getAltura());
        Localidad localidad = localidadRepository.getById(direccionDto.getLocalidad().getId());
        localidad.addDireccion(direccion);
        return this.save(direccion);
    }
}
