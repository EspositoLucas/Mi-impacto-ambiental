package dds.grupo4.tpimpacto.services;

import dds.grupo4.tpimpacto.repositories.TipoUnidadRepository;
import dds.grupo4.tpimpacto.units.TipoUnidad;
import dds.grupo4.tpimpacto.units.Unidad;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Slf4j
public class TipoUnidadService extends BaseService<TipoUnidad, TipoUnidadRepository> {

    public TipoUnidadService(TipoUnidadRepository repository) {
        super(repository);
    }

    @Transactional
    public void seedData() {
        if (this.hasData()) {
            log.debug("Ya existen registros de TipoUnidad creados");
            return;
        }

        log.debug("Se crean los TipoUnidad iniciales");

        TipoUnidad longitud = new TipoUnidad("Longitud");
        Unidad KM = new Unidad("km", "Kilometro", true, 1d);
        Unidad M = new Unidad("m", "Metro", false, 1d / 1000);
        longitud.addUnidades(Arrays.asList(KM, M));

        TipoUnidad masa = new TipoUnidad("Masa");
        Unidad KG = new Unidad("kg", "Kilogramo", true, 1d);
        Unidad G = new Unidad("g", "Gramo", false, 1d / 1000);
        masa.addUnidades(Arrays.asList(KG, G));

        TipoUnidad volumen = new TipoUnidad("Volumen");
        Unidad M3 = new Unidad("m3", "Metro cubico", true, 1d);
        Unidad LT = new Unidad("lt", "Litro", false, 1d / 1000);
        volumen.addUnidades(Arrays.asList(M3, LT));

        TipoUnidad energia = new TipoUnidad("Energía");
        Unidad KWH = new Unidad("kWh", "Kilowatt-Hora", true, 1d);
        energia.addUnidad(KWH);

        TipoUnidad equivalenteCarbono = new TipoUnidad("Equivalente carbono");
        Unidad GCO2eq = new Unidad("gCO2eq", "Gramo equivalente carbono", true, 1d);
        Unidad KGCO2eq = new Unidad("kgCO2eq", "Kilogramo equivalente carbono", false, 1000d);
        Unidad TNCO2eq = new Unidad("tnCO2eq", "Tonelada equivalente carbono", false, 1_000_000d);
        equivalenteCarbono.addUnidades(Arrays.asList(GCO2eq, KGCO2eq, TNCO2eq));

        this.saveAll(Arrays.asList(longitud, masa, volumen, energia, equivalenteCarbono));
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
