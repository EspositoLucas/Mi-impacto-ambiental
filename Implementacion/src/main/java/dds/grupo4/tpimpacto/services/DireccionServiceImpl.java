package dds.grupo4.tpimpacto.services;

import dds.grupo4.tpimpacto.dtos.DireccionDto;
import dds.grupo4.tpimpacto.entities.trayecto.Direccion;
import dds.grupo4.tpimpacto.repositories.DireccionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class DireccionServiceImpl extends BaseServiceImpl<Direccion, DireccionRepository> implements DireccionService {
    public DireccionServiceImpl(DireccionRepository repository) {
        super(repository);
    }

    @Override
    @Transactional
    public Direccion getOrCreateDireccion(DireccionDto direccionDto) {
        Optional<Direccion> direccionOptional = repository.getByDireccionEntera(
                direccionDto.getCalle(),
                direccionDto.getAltura(),
                direccionDto.getPais(),
                direccionDto.getProvincia(),
                direccionDto.getMunicipio(),
                direccionDto.getLocalidad(),
                direccionDto.getBarrio(),
                direccionDto.getCodigoPostal()
        );
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
