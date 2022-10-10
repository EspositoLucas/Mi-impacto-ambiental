package dds.grupo4.tpimpacto.services;

import dds.grupo4.tpimpacto.repositories.TipoUnidadRepository;
import dds.grupo4.tpimpacto.units.TipoUnidad;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Slf4j
public class TipoUnidadService extends BaseService<TipoUnidad, TipoUnidadRepository> {

    public TipoUnidadService(TipoUnidadRepository repository) {
        super(repository);
    }

    @Transactional
    public Optional<TipoUnidad> getByNombre(String nombre) {
        return repository.getByNombre(nombre);
    }

    @Transactional
    public TipoUnidad getLongitud() {
        return getOrThrow("Longitud");
    }

    @Transactional
    public TipoUnidad getMasa() {
        return getOrThrow("Masa");
    }

    @Transactional
    public TipoUnidad getVolumen() {
        return getOrThrow("Volumen");
    }

    @Transactional
    public TipoUnidad getEnergia() {
        return getOrThrow("Energia");
    }

    @Transactional
    public TipoUnidad getEquivalenteCarbono() {
        return getOrThrow("EquivalenteCarbono");
    }

    private TipoUnidad getOrThrow(String nombre) {
        return getByNombre(nombre)
                .orElseThrow(() -> new NoSuchElementException("No se cargo ningun TipoUnidad para " + nombre));
    }
}
