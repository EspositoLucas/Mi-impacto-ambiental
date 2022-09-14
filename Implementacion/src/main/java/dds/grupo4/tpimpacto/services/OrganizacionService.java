package dds.grupo4.tpimpacto.services;

import dds.grupo4.tpimpacto.cargamediciones.RowMedicionActividad;
import dds.grupo4.tpimpacto.dtos.*;
import dds.grupo4.tpimpacto.dtos.base.BaseResponse;
import dds.grupo4.tpimpacto.dtos.base.ResponseWithResults;
import dds.grupo4.tpimpacto.entities.medicion.*;
import dds.grupo4.tpimpacto.entities.organizacion.*;
import dds.grupo4.tpimpacto.entities.trayecto.Direccion;
import dds.grupo4.tpimpacto.entities.trayecto.Espacio;
import dds.grupo4.tpimpacto.entities.trayecto.TipoEspacio;
import dds.grupo4.tpimpacto.repositories.OrganizacionRepository;
import dds.grupo4.tpimpacto.repositories.SolicitudRepository;
import dds.grupo4.tpimpacto.services.calculohc.CalculadoraHC;
import dds.grupo4.tpimpacto.units.Cantidad;
import dds.grupo4.tpimpacto.units.Unidad;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrganizacionService extends BaseService<Organizacion, OrganizacionRepository> {

    private final SolicitudRepository solicitudRepository;
    private final TipoConsumoService tipoConsumoService;
    private final UnidadService unidadService;
    private final RegistroCalculoHCDatoActividadService registroCalculoHCDatoActividadService;
    private final CalculadoraHC calculadoraHC;
    private final ExcelService excelService;

    public OrganizacionService(OrganizacionRepository organizacionRepository, SolicitudRepository solicitudRepository,
                               TipoConsumoService tipoConsumoService, UnidadService unidadService, CalculadoraHC calculadoraHC,
                               RegistroCalculoHCDatoActividadService registroCalculoHCDatoActividadService, ExcelService excelService) {
        super(organizacionRepository);
        this.solicitudRepository = solicitudRepository;
        this.tipoConsumoService = tipoConsumoService;
        this.unidadService = unidadService;
        this.calculadoraHC = calculadoraHC;
        this.registroCalculoHCDatoActividadService = registroCalculoHCDatoActividadService;
        this.excelService = excelService;
    }

    @Transactional
    public BaseResponse crearOrganizacion(CrearOrganizacionRequest request) {
        if (repository.getByRazonSocial(request.getRazonSocial()).isPresent()) {
            return new BaseResponse(HttpStatus.BAD_REQUEST, "Ya existe una Organizacion con esa razon social");
        }

        TipoOrganizacion tipoOrganizacion = TipoOrganizacion.valueOf(request.getTipoOrganizacion());
        Clasificacion clasificacion = Clasificacion.valueOf(request.getClasificacion());
        Unidad unidadFactorK = request.getFactorK().getUnidad() != null
                ? unidadService.getById(request.getFactorK().getUnidad().getId())
                : null;
        Cantidad factorK = new Cantidad(unidadFactorK, request.getFactorK().getValor());
        Organizacion nuevaOrganizacion = new Organizacion(request.getRazonSocial(), tipoOrganizacion, clasificacion,
                factorK, request.getCantDiasHabilesPorSemana());
        this.save(nuevaOrganizacion);
        return new BaseResponse(HttpStatus.OK);
    }

    @Transactional
    public ResponseWithResults<OrganizacionDto> listarOrganizaciones() {
        List<Organizacion> organizaciones = this.getAll();
        List<OrganizacionDto> dtos = organizaciones.stream()
                .map(OrganizacionDto::from)
                .collect(Collectors.toList());
        return new ResponseWithResults<>(HttpStatus.OK, dtos);
    }

    @Transactional
    public ResponseWithResults<MiembroDto> listarMiembros(long idOrganizacion) {
        Organizacion organizacion = this.getById(idOrganizacion);
        List<MiembroDto> miembrosDtos = organizacion.getMiembros().stream()
                .map(MiembroDto::from)
                .collect(Collectors.toList());
        return new ResponseWithResults<>(HttpStatus.OK, miembrosDtos);
    }

    @Transactional
    public Optional<Organizacion> getByRazonSocial(String razonSocial) {
        return repository.getByRazonSocial(razonSocial);
    }

    @Transactional
    public BaseResponse aceptarSolicitud(AceptarSolicitudRequest request) {
        Solicitud solicitud = solicitudRepository.getById(request.getIdSolicitud());
        if (solicitud == null) {
            return new BaseResponse(
                    HttpStatus.BAD_REQUEST,
                    "No existe ninguna Solicitud con el ID ingresado"
            );
        }

        solicitud.getSector().aceptarSolicitud(solicitud);
        solicitud.getMiembro().setFechaIngreso(LocalDate.now());
        return new BaseResponse(HttpStatus.ACCEPTED, "Miembro asociado correctamente con la Organizacion");
    }

    @Transactional
    public void cargarMediciones(CargarMedicionesRequest request) throws IOException {
        Organizacion organizacion = this.getById(request.getIdOrganizacion());
        InputStream inputStream = request.getFile().getInputStream();
        List<RowMedicionActividad> mediciones = excelService.loadData(inputStream, "Hoja1", 2, RowMedicionActividad::fromRow);

        List<Medicion> medicionesParseadas = mediciones.stream()
                .map(this::rowToMedicion)
                .collect(Collectors.toList());
        organizacion.addMediciones(medicionesParseadas);

        calcularHCDatosActividadYGuardarRegistroCalculo(organizacion, medicionesParseadas);
    }

    @Transactional
    public List<String> getMailsDeContactos() {
        return repository.getMailsDeContactos();
    }

    @Transactional
    public void seedData() {
        if (this.hasData()) {
            log.debug("Seed: ya hay Organizaciones creadas");
            return;
        }

        log.debug("Seed: se crean las Organizaciones iniciales");

        Organizacion organizacion = new Organizacion(
                "Organizacion TEST",
                TipoOrganizacion.EMPRESA,
                Clasificacion.EMPRESA_SECTOR_PRIMARIO,
                new Cantidad(unidadService.getBySimbolo("1/kg").get(), 2),
                5
        );
        Direccion direccionEspacio = new Direccion("MEDRANO", "951");
        Espacio espacio = new Espacio(direccionEspacio, "Espacio TEST", TipoEspacio.TRABAJO);
        Sector sector = new Sector("Sector TEST", organizacion, espacio);
        organizacion.addSector(sector);
        this.saveAll(Arrays.asList(organizacion));
    }

    private Medicion rowToMedicion(RowMedicionActividad row) {
        Actividad actividad = Actividad.from(row.getActividad());
        TipoConsumo tipoConsumo = tipoConsumoService.getByNombre(row.getTipoDeConsumo())
                .orElseThrow(() -> new IllegalArgumentException("No existe un TipoConsumo con el nombre '" + row.getTipoDeConsumo() + "'"));
        Periodicidad periodicidad = Periodicidad.from(row.getPeriodicidad());
        return new Medicion(actividad, tipoConsumo, periodicidad, row.getPeriodoImputacion(), row.getValor());
    }

    private void calcularHCDatosActividadYGuardarRegistroCalculo(Organizacion organizacion, List<Medicion> mediciones) {
        Map<LocalDate, Cantidad> huellasDeCarbonoMensuales = new HashMap<>();
        Map<LocalDate, Cantidad> huellasDeCarbonoAnuales = new HashMap<>();
        Set<Medicion> medicionesYaProcesadas = new HashSet<>();

        for (Medicion medicion : mediciones) {
            if (medicionesYaProcesadas.contains(medicion))
                continue;

            medicionesYaProcesadas.add(medicion);
            Cantidad valorHuellaDeCarbono;
            if (medicion.getActividad() == Actividad.LOGISTICA_DE_PRODUCTOS_Y_RESIDUOS) {
                List<Medicion> medicionesCorrespondientes = getMedicionesCorrespondientesDeLogistica(medicion, mediciones);
                valorHuellaDeCarbono = calculadoraHC.calcularHCDatoActividadLogistica(medicionesCorrespondientes);
                // Para que no se vuelvan a procesar las Mediciones que corresponden con la Medicion actual (se procesan todas juntas)
                medicionesYaProcesadas.addAll(medicionesCorrespondientes);
            } else {
                valorHuellaDeCarbono = calculadoraHC.calcularHCDatoActividadNoLogistica(medicion);
            }

            if (medicion.getPeriodicidad() == Periodicidad.MENSUAL) {
                huellasDeCarbonoMensuales.merge(medicion.getPeriodoImputacion(), valorHuellaDeCarbono,
                        Cantidad::add);
            } else {
                huellasDeCarbonoAnuales.merge(medicion.getPeriodoImputacion(), valorHuellaDeCarbono,
                        Cantidad::add);
            }
        }

        guardarRegistrosCalculoHCMensuales(organizacion, huellasDeCarbonoMensuales);
        guardarRegistrosCalculoHCAnuales(organizacion, huellasDeCarbonoAnuales);
    }

    private void guardarRegistrosCalculoHCMensuales(Organizacion organizacion, Map<LocalDate, Cantidad> huellasDeCarbono) {
        huellasDeCarbono.forEach((periodoImputacion, valorHC) -> {
            RegistroCalculoHCDatoActividad registroCalculoHC = new RegistroCalculoHCDatoActividad(
                    Periodicidad.MENSUAL, periodoImputacion, valorHC);
            organizacion.addRegistroCalculoHC(registroCalculoHC);
            registroCalculoHCDatoActividadService.save(registroCalculoHC);
        });
    }

    private void guardarRegistrosCalculoHCAnuales(Organizacion organizacion, Map<LocalDate, Cantidad> huellasDeCarbono) {
        huellasDeCarbono.forEach((periodoImputacion, valorHC) -> {
            valorHC = calculadoraHC.calcularHCAnualProrrateadoDatoActividad(valorHC, periodoImputacion);
            Optional<RegistroCalculoHCDatoActividad> optionalRegistroCalculoHC = registroCalculoHCDatoActividadService.getRegistroCalculoHCParaPeriodo(
                    organizacion.getId(), Periodicidad.ANUAL, periodoImputacion
            );
            if (optionalRegistroCalculoHC.isPresent()) {
                RegistroCalculoHCDatoActividad registroExistenteCalculoHC = optionalRegistroCalculoHC.get();
                registroExistenteCalculoHC.setValor(valorHC);
            } else {
                RegistroCalculoHCDatoActividad registroCalculoHC = new RegistroCalculoHCDatoActividad(
                        Periodicidad.ANUAL, periodoImputacion, valorHC);
                organizacion.addRegistroCalculoHC(registroCalculoHC);
                registroCalculoHCDatoActividadService.save(registroCalculoHC);
            }
        });
    }

    /**
     * Retorna todas las Mediciones que se corresponden con la Medicion que recibe por parametro (coinciden la Periodicidad
     * y el PeriodoImputacion).
     */
    private List<Medicion> getMedicionesCorrespondientesDeLogistica(Medicion medicionAComparar, List<Medicion> mediciones) {
        return mediciones.stream()
                .filter(medicion -> medicion.getActividad() == Actividad.LOGISTICA_DE_PRODUCTOS_Y_RESIDUOS &&
                        medicion.getPeriodicidad() == medicionAComparar.getPeriodicidad() &&
                        medicion.getPeriodoImputacion().equals(medicionAComparar.getPeriodoImputacion()))
                .collect(Collectors.toList());
    }

}
