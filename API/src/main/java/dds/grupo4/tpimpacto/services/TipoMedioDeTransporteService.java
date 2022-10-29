package dds.grupo4.tpimpacto.services;

import dds.grupo4.tpimpacto.entities.medicion.FactorDeEmision;
import dds.grupo4.tpimpacto.entities.medioTransporte.TipoMedioDeTransporte;
import dds.grupo4.tpimpacto.repositories.TipoMedioDeTransporteRepository;
import dds.grupo4.tpimpacto.repositories.UnidadRepository;
import dds.grupo4.tpimpacto.services.base.BaseService;
import dds.grupo4.tpimpacto.units.Cantidad;
import dds.grupo4.tpimpacto.units.Unidad;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Service
@Slf4j
public class TipoMedioDeTransporteService extends BaseService<TipoMedioDeTransporte, TipoMedioDeTransporteRepository> {

    private final UnidadRepository unidadRepository;

    public TipoMedioDeTransporteService(TipoMedioDeTransporteRepository repository, UnidadRepository unidadRepository) {
        super(repository);
        this.unidadRepository = unidadRepository;
    }

    @Transactional
    public TipoMedioDeTransporte getByNombre(String nombre) {
        return repository.getByNombre(nombre);
    }

    @Transactional
    public void seedData() {
        if (this.hasData()) {
            log.debug("Seed: ya hay TiposMedioDeTransporte creados");
            return;
        }

        log.debug("Seed: se crean los TiposMedioDeTransporte iniciales");

        Unidad GCO2eq_SOBRE_KM = unidadRepository.getBySimbolo("gCO2eq/km").get();

        TipoMedioDeTransporte tren = new TipoMedioDeTransporte("Tren");
        tren.setFactorDeEmision(new FactorDeEmision(new Cantidad(GCO2eq_SOBRE_KM, 1)));

        TipoMedioDeTransporte subte = new TipoMedioDeTransporte("Subte");
        subte.setFactorDeEmision(new FactorDeEmision(new Cantidad(GCO2eq_SOBRE_KM, 1)));

        TipoMedioDeTransporte colectivo = new TipoMedioDeTransporte("Colectivo");
        colectivo.setFactorDeEmision(new FactorDeEmision(new Cantidad(GCO2eq_SOBRE_KM, 1)));

        TipoMedioDeTransporte bicicleta = new TipoMedioDeTransporte("Bicicleta");
        bicicleta.setFactorDeEmision(new FactorDeEmision(new Cantidad(GCO2eq_SOBRE_KM, 0)));

        TipoMedioDeTransporte aPie = new TipoMedioDeTransporte("A pie");
        aPie.setFactorDeEmision(new FactorDeEmision(new Cantidad(GCO2eq_SOBRE_KM, 0)));

        TipoMedioDeTransporte monopatin = new TipoMedioDeTransporte("Monopatín");
        monopatin.setFactorDeEmision(new FactorDeEmision(new Cantidad(GCO2eq_SOBRE_KM, 0)));

        TipoMedioDeTransporte moto = new TipoMedioDeTransporte("Moto");
        moto.setFactorDeEmision(new FactorDeEmision(new Cantidad(GCO2eq_SOBRE_KM, 1)));

        TipoMedioDeTransporte auto = new TipoMedioDeTransporte("Auto");
        auto.setFactorDeEmision(new FactorDeEmision(new Cantidad(GCO2eq_SOBRE_KM, 1)));

        TipoMedioDeTransporte camioneta = new TipoMedioDeTransporte("Camioneta");
        camioneta.setFactorDeEmision(new FactorDeEmision(new Cantidad(GCO2eq_SOBRE_KM, 1)));

        TipoMedioDeTransporte camionDeCarga = new TipoMedioDeTransporte("Camión de carga");
        camionDeCarga.setFactorDeEmision(new FactorDeEmision(new Cantidad(GCO2eq_SOBRE_KM, 1)));

        TipoMedioDeTransporte utilitarioLiviano = new TipoMedioDeTransporte("Utilitario liviano");
        utilitarioLiviano.setFactorDeEmision(new FactorDeEmision(new Cantidad(GCO2eq_SOBRE_KM, 1)));

        this.saveAll(Arrays.asList(tren, subte, colectivo)); // Transportes publicos
        this.saveAll(Arrays.asList(bicicleta, aPie, monopatin)); // No contaminantes
        this.saveAll(Arrays.asList(moto, auto, camioneta, camionDeCarga, utilitarioLiviano)); // Vehiculos particulares
    }
}
