package dds.grupo4.tpimpacto.services;

import dds.grupo4.tpimpacto.dtos.UnidadDto;
import dds.grupo4.tpimpacto.dtos.base.ResponseWithResults;
import dds.grupo4.tpimpacto.repositories.UnidadRepository;
import dds.grupo4.tpimpacto.units.RelacionUnidades;
import dds.grupo4.tpimpacto.units.RowRelacionUnidades;
import dds.grupo4.tpimpacto.units.TipoUnidad;
import dds.grupo4.tpimpacto.units.Unidad;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UnidadService extends BaseService<Unidad, UnidadRepository> {

    private final TipoUnidadService tipoUnidadService;
    private final ExcelService excelService;

    public UnidadService(UnidadRepository repository, TipoUnidadService tipoUnidadService, ExcelService excelService) {
        super(repository);
        this.tipoUnidadService = tipoUnidadService;
        this.excelService = excelService;
    }

    @Transactional
    public Optional<Unidad> getBySimbolo(String simbolo) {
        return repository.getBySimbolo(simbolo);
    }

    @Transactional
    public ResponseWithResults<UnidadDto> listarUnidades() {
        List<UnidadDto> dtos = this.getAll().stream()
                .map(UnidadDto::from)
                .collect(Collectors.toList());
        return new ResponseWithResults<>(HttpStatus.OK, dtos);
    }

    @Transactional
    public void seedData() {
        if (this.hasData()) {
            log.debug("Seed: ya hay Unidades creadas");
            return;
        }

        seedUnidades();
        seedRelacionesUnidades();
    }

    private void seedUnidades() {
        log.debug("Seed: se crean las Unidades iniciales");

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

        TipoUnidad unidadesCompuestas = new TipoUnidad("Unidades compuestas");
        Unidad GCO2eq_SOBRE_KM = new Unidad("gCO2eq/km", "Gramo equivalente carbono sobre Kilometro", true, 1);
        Unidad UNO_SOBRE_KG = new Unidad("1/kg", "1 sobre Kilogramo", true, 1);
        Unidad GCO2eq_SOBRE_M3 = new Unidad("gCO2eq/m3", "Gramo equivalente carbono sobre Metro cubico", true, 1);
        Unidad GCO2eq_SOBRE_LT = new Unidad("gCO2eq/lt", "Gramo equivalente carbono sobre Litro", true, 1);
        Unidad GCO2eq_SOBRE_KG = new Unidad("gCO2eq/kg", "Gramo equivalente carbono sobre Kilogramo", true, 1);
        Unidad GCO2eq_SOBRE_KWH = new Unidad("gCO2eq/kWh", "Gramo equivalente carbono sobre Kilowatt-Hora", true, 1);
        unidadesCompuestas.addUnidades(Arrays.asList(GCO2eq_SOBRE_KM, UNO_SOBRE_KG, GCO2eq_SOBRE_M3,
                GCO2eq_SOBRE_LT, GCO2eq_SOBRE_KG, GCO2eq_SOBRE_KWH));

        tipoUnidadService.saveAll(Arrays.asList(longitud, masa, volumen, energia, equivalenteCarbono, unidadesCompuestas));
    }

    private void seedRelacionesUnidades() {
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream("static/RelacionesUnidadesIniciales.xlsx");
            List<RowRelacionUnidades> rows = excelService.loadData(inputStream, "Hoja1", 1, RowRelacionUnidades::fromRow);
            seedRelacionesUnidades(rows);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void seedRelacionesUnidades(List<RowRelacionUnidades> rows) {
        for (RowRelacionUnidades row : rows) {
            String simboloUnidadIzquierda = row.getUnidadIzquierda();
            String simboloUnidadDerecha = row.getUnidadDerecha();
            String simboloUnidadProducto = row.getUnidadProducto();
            String simboloUnidadCociente = row.getUnidadCociente();

            Function<String, Unidad> functionGetUnidadBySimbolo = (String simbolo) -> {
                if (simbolo == null)
                    return null;
                return getBySimbolo(simbolo).orElseThrow(() -> new IllegalStateException("No existe la unidad " + simbolo));
            };

            Unidad unidadIzquierda = functionGetUnidadBySimbolo.apply(simboloUnidadIzquierda);
            Unidad unidadDerecha = functionGetUnidadBySimbolo.apply(simboloUnidadDerecha);
            Unidad unidadProducto = functionGetUnidadBySimbolo.apply(simboloUnidadProducto);
            Unidad unidadCociente = functionGetUnidadBySimbolo.apply(simboloUnidadCociente);

            RelacionUnidades relacion = new RelacionUnidades(unidadIzquierda, unidadDerecha, unidadProducto, unidadCociente);
            if (unidadIzquierda != null)
                unidadIzquierda.addRelacionEnIzquierda(relacion);
            if (unidadDerecha != null)
                unidadDerecha.addRelacionEnDerecha(relacion);
            if (unidadProducto != null)
                unidadProducto.addRelacionEnProducto(relacion);
            if (unidadCociente != null)
                unidadCociente.addRelacionEnCociente(relacion);

            this.saveAll(Arrays.asList(unidadIzquierda, unidadDerecha, unidadProducto, unidadCociente));
        }
    }
}
