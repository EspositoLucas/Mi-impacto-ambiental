package dds.grupo4.tpimpacto.services;

import dds.grupo4.tpimpacto.repositories.UnidadRepository;
import dds.grupo4.tpimpacto.units.Unidad;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UnidadService extends BaseService<Unidad, UnidadRepository> {
    public UnidadService(UnidadRepository repository) {
        super(repository);
    }

    @Transactional
    public Optional<Unidad> getBySimbolo(String simbolo) {
        return repository.getBySimbolo(simbolo);
    }
}
