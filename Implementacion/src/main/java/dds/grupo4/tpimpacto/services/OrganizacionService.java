package dds.grupo4.tpimpacto.services;

import dds.grupo4.tpimpacto.cargamediciones.RowMedicionActividad;
import dds.grupo4.tpimpacto.dtos.AceptarSolicitudRequest;
import dds.grupo4.tpimpacto.dtos.CrearOrganizacionRequest;
import dds.grupo4.tpimpacto.dtos.MiembroDto;
import dds.grupo4.tpimpacto.dtos.OrganizacionDto;
import dds.grupo4.tpimpacto.dtos.base.BaseResponse;
import dds.grupo4.tpimpacto.dtos.base.ResponseWithResults;
import dds.grupo4.tpimpacto.entities.medicion.Actividad;
import dds.grupo4.tpimpacto.entities.medicion.Medicion;
import dds.grupo4.tpimpacto.entities.medicion.Periodicidad;
import dds.grupo4.tpimpacto.entities.medicion.TipoConsumo;
import dds.grupo4.tpimpacto.entities.organizacion.Clasificacion;
import dds.grupo4.tpimpacto.entities.organizacion.Organizacion;
import dds.grupo4.tpimpacto.entities.organizacion.Solicitud;
import dds.grupo4.tpimpacto.entities.organizacion.TipoOrganizacion;
import dds.grupo4.tpimpacto.repositories.OrganizacionRepository;
import dds.grupo4.tpimpacto.repositories.SolicitudRepository;
import dds.grupo4.tpimpacto.units.Cantidad;
import dds.grupo4.tpimpacto.units.Unidad;
import dds.grupo4.tpimpacto.utils.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrganizacionService extends BaseService<Organizacion, OrganizacionRepository> {

    private final SolicitudRepository solicitudRepository;
    private final TipoConsumoService tipoConsumoService;
    private final UnidadService unidadService;

    public OrganizacionService(OrganizacionRepository organizacionRepository, SolicitudRepository solicitudRepository,
                               TipoConsumoService tipoConsumoService, UnidadService unidadService) {
        super(organizacionRepository);
        this.solicitudRepository = solicitudRepository;
        this.tipoConsumoService = tipoConsumoService;
        this.unidadService = unidadService;
    }

    @Transactional
    public BaseResponse crearOrganizacion(CrearOrganizacionRequest request) {
        if (repository.getByRazonSocial(request.getRazonSocial()).isPresent()) {
            return new BaseResponse(HttpStatus.BAD_REQUEST, "Ya existe una Organizacion con esa razon social");
        }

        TipoOrganizacion tipoOrganizacion = TipoOrganizacion.valueOf(request.getTipoOrganizacion());
        Clasificacion clasificacion = Clasificacion.valueOf(request.getClasificacion());
        Unidad unidadFactorK = request.getCantidad().getIdUnidad() != null
                ? unidadService.getById(request.getCantidad().getIdUnidad())
                : null;
        Cantidad factorK = new Cantidad(unidadFactorK, request.getCantidad().getValor());
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
    public void cargarMediciones(Organizacion organizacion, List<RowMedicionActividad> mediciones) {
        List<Medicion> medicionesParseadas = mediciones.stream()
                .map(rowMedicionActividad -> rowToMedicion(organizacion, rowMedicionActividad))
                .collect(Collectors.toList());

        for (Medicion medicion : medicionesParseadas) {
            organizacion.addMedicion(medicion);
        }

        save(organizacion);
    }

    @Transactional
    public List<String> getMailsDeContactos() {
        return repository.getMailsDeContactos();
    }

    private Medicion rowToMedicion(Organizacion organizacion, RowMedicionActividad row) {
        Actividad actividad = Actividad.from(StringUtils.sacarAcentos(row.getActividad()));
        TipoConsumo tipoConsumo = tipoConsumoService.getByNombre(StringUtils.sacarAcentos(row.getTipoDeConsumo())).get();
        Periodicidad periodicidad = Periodicidad.from(row.getPeriodicidad());

        Integer mesImputacion, anioImputacion;
        String[] periodoImputacionSeparado = row.getPeriodoImputacion().split("/");
        if (periodicidad == Periodicidad.MENSUAL) {
            // MM/YYYY
            mesImputacion = Integer.parseInt(periodoImputacionSeparado[0]);
            anioImputacion = Integer.parseInt(periodoImputacionSeparado[1]);
        } else {
            // YYYY
            mesImputacion = null;
            anioImputacion = Integer.parseInt(periodoImputacionSeparado[0]);
        }

        return new Medicion(organizacion, actividad, tipoConsumo, row.getValor(),
                periodicidad, mesImputacion, anioImputacion);
    }

}
