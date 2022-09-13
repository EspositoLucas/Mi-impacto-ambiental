package dds.grupo4.tpimpacto.services;

import dds.grupo4.tpimpacto.entities.medicion.Actividad;
import dds.grupo4.tpimpacto.entities.medicion.FactorDeEmision;
import dds.grupo4.tpimpacto.entities.medicion.TipoConsumo;
import dds.grupo4.tpimpacto.repositories.TipoConsumoRepository;
import dds.grupo4.tpimpacto.units.Cantidad;
import dds.grupo4.tpimpacto.units.Unidad;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Optional;

@Service
@Slf4j
public class TipoConsumoService extends BaseService<TipoConsumo, TipoConsumoRepository> {

    private final UnidadService unidadService;

    public TipoConsumoService(TipoConsumoRepository tipoConsumoRepository, UnidadService unidadService) {
        super(tipoConsumoRepository);
        this.unidadService = unidadService;
    }

    @Transactional
    public Optional<TipoConsumo> getByNombre(String nombreTipo) {
        return repository.getByNombre(nombreTipo);
    }

    @Transactional
    public void seedData() {
        if (this.hasData()) {
            log.debug("Seed: ya hay TiposConsumo creados");
            return;
        }

        log.debug("Seed: se crean los TiposConsumo iniciales");
        seedTiposDeConsumoParaAlcanceEmisionesDirectas();
        seedTiposDeConsumoParaAlcanceEmisionesIndirectas();
        seedTiposDeConsumoParaAlcanceOtrasEmisionesIndirectas();
    }

    private void seedTiposDeConsumoParaAlcanceEmisionesDirectas() {
        Unidad M3 = unidadService.getBySimbolo("m3").get();
        Unidad GCO2eq_SOBRE_M3 = unidadService.getBySimbolo("gCO2eq/m3").get();
        Unidad LT = unidadService.getBySimbolo("lt").get();
        Unidad GCO2eq_SOBRE_LT = unidadService.getBySimbolo("gCO2eq/lt").get();
        Unidad KG = unidadService.getBySimbolo("kg").get();
        Unidad GCO2eq_SOBRE_KG = unidadService.getBySimbolo("gCO2eq/kg").get();

        String alcanceEmisionesDirectas = "1: Emisiones directas";

        TipoConsumo gasNatural = new TipoConsumo("Gas Natural", Actividad.COMBUSTION_FIJA, alcanceEmisionesDirectas, M3);
        gasNatural.setFactorDeEmision(new FactorDeEmision(new Cantidad(GCO2eq_SOBRE_M3, 1)));

        TipoConsumo dieselGasoil = new TipoConsumo("Diesel/Gasoil", Actividad.COMBUSTION_FIJA, alcanceEmisionesDirectas, LT);
        dieselGasoil.setFactorDeEmision(new FactorDeEmision(new Cantidad(GCO2eq_SOBRE_LT, 1)));

        TipoConsumo kerosene = new TipoConsumo("Kerosene", Actividad.COMBUSTION_FIJA, alcanceEmisionesDirectas, LT);
        kerosene.setFactorDeEmision(new FactorDeEmision(new Cantidad(GCO2eq_SOBRE_LT, 1)));

        TipoConsumo fuelOil = new TipoConsumo("Fuel Oil", Actividad.COMBUSTION_FIJA, alcanceEmisionesDirectas, LT);
        fuelOil.setFactorDeEmision(new FactorDeEmision(new Cantidad(GCO2eq_SOBRE_LT, 1)));

        TipoConsumo nafta = new TipoConsumo("Nafta", Actividad.COMBUSTION_FIJA, alcanceEmisionesDirectas, LT);
        nafta.setFactorDeEmision(new FactorDeEmision(new Cantidad(GCO2eq_SOBRE_LT, 1)));

        TipoConsumo carbon = new TipoConsumo("Carbón", Actividad.COMBUSTION_FIJA, alcanceEmisionesDirectas, KG);
        carbon.setFactorDeEmision(new FactorDeEmision(new Cantidad(GCO2eq_SOBRE_KG, 1)));

        TipoConsumo carbonDeLenia = new TipoConsumo("Carbón de leña", Actividad.COMBUSTION_FIJA, alcanceEmisionesDirectas, KG);
        carbonDeLenia.setFactorDeEmision(new FactorDeEmision(new Cantidad(GCO2eq_SOBRE_KG, 1)));

        TipoConsumo lenia = new TipoConsumo("Leña", Actividad.COMBUSTION_FIJA, alcanceEmisionesDirectas, KG);
        lenia.setFactorDeEmision(new FactorDeEmision(new Cantidad(GCO2eq_SOBRE_KG, 1)));

        TipoConsumo combustibleConsumidoGasoil = new TipoConsumo("Combustible consumido - Gasoil", Actividad.COMBUSTION_MOVIL, alcanceEmisionesDirectas, LT);
        combustibleConsumidoGasoil.setFactorDeEmision(new FactorDeEmision(new Cantidad(GCO2eq_SOBRE_LT, 1)));

        TipoConsumo combustibleConsumidoGNC = new TipoConsumo("Combustible consumido - GNC", Actividad.COMBUSTION_MOVIL, alcanceEmisionesDirectas, LT);
        combustibleConsumidoGNC.setFactorDeEmision(new FactorDeEmision(new Cantidad(GCO2eq_SOBRE_LT, 1)));

        TipoConsumo combustibleConsumidoNafta = new TipoConsumo("Combustible consumido - Nafta", Actividad.COMBUSTION_MOVIL, alcanceEmisionesDirectas, LT);
        combustibleConsumidoNafta.setFactorDeEmision(new FactorDeEmision(new Cantidad(GCO2eq_SOBRE_LT, 1)));

        this.saveAll(Arrays.asList(gasNatural, dieselGasoil, kerosene, fuelOil, nafta, carbon, carbonDeLenia, lenia, combustibleConsumidoGasoil, combustibleConsumidoGNC, combustibleConsumidoNafta));
    }

    private void seedTiposDeConsumoParaAlcanceEmisionesIndirectas() {
        Unidad KWH = unidadService.getBySimbolo("kWh").get();
        Unidad GCO2eq_SOBRE_KWH = unidadService.getBySimbolo("gCO2eq/kWh").get();

        String alcanceEmisionesIndirectas = "2: Emisiones indirectas asociadas a la electricidad";

        TipoConsumo electricidad = new TipoConsumo("Electricidad", Actividad.ELECTRICIDAD_ADQUIRIDA_Y_CONSUMIDA, alcanceEmisionesIndirectas, KWH);
        electricidad.setFactorDeEmision(new FactorDeEmision(new Cantidad(GCO2eq_SOBRE_KWH, 1)));

        this.saveAll(Arrays.asList(electricidad));
    }

    private void seedTiposDeConsumoParaAlcanceOtrasEmisionesIndirectas() {
        Unidad KM = unidadService.getBySimbolo("km").get();
        Unidad KG = unidadService.getBySimbolo("kg").get();

        String alcanceOtrasEmisionesIndirectas = "3: Otras emisiones indirectas que ocurren en fuentes no controladas por la Organización";

        TipoConsumo categoriaDeProductoTransportado = new TipoConsumo("Categoría de producto transportado", Actividad.LOGISTICA_DE_PRODUCTOS_Y_RESIDUOS, alcanceOtrasEmisionesIndirectas, null);
        TipoConsumo medioDeTransporte = new TipoConsumo("Medio de transporte", Actividad.LOGISTICA_DE_PRODUCTOS_Y_RESIDUOS, alcanceOtrasEmisionesIndirectas, null);
        TipoConsumo distanciaMediaRecorrida = new TipoConsumo("Distancia media recorrida", Actividad.LOGISTICA_DE_PRODUCTOS_Y_RESIDUOS, alcanceOtrasEmisionesIndirectas, KM);
        TipoConsumo pesoTotalTransportado = new TipoConsumo("Peso total transportado", Actividad.LOGISTICA_DE_PRODUCTOS_Y_RESIDUOS, alcanceOtrasEmisionesIndirectas, KG);

        this.saveAll(Arrays.asList(categoriaDeProductoTransportado, medioDeTransporte, distanciaMediaRecorrida, pesoTotalTransportado));
    }

}
