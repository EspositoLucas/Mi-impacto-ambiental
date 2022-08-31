package dds.grupo4.tpimpacto.services;

import dds.grupo4.tpimpacto.dtos.DireccionDto;
import dds.grupo4.tpimpacto.entities.trayecto.Direccion;
import dds.grupo4.tpimpacto.repositories.DireccionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class DireccionService extends BaseService<Direccion, DireccionRepository> {
    public DireccionService(DireccionRepository repository) {
        super(repository);
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
                    direccionDto.getPais(),
                    direccionDto.getProvincia(),
                    direccionDto.getMunicipio(),
                    direccionDto.getLocalidad(),
                    direccionDto.getBarrio(),
                    direccionDto.getCodigoPostal()
            );
        }

        if (direccionOptional.isPresent()) {
            return direccionOptional.get();
        }

        Direccion direccion = new Direccion(
                direccionDto.getCalle(),
                direccionDto.getAltura(),
                direccionDto.getPais(),
                direccionDto.getProvincia(),
                direccionDto.getMunicipio(),
                direccionDto.getLocalidad(),
                direccionDto.getBarrio(),
                direccionDto.getCodigoPostal()
        );
        return this.save(direccion);
    }
}
