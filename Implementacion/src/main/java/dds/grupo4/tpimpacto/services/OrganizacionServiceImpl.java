package dds.grupo4.tpimpacto.services;

import dds.grupo4.tpimpacto.cargamediciones.RowMedicionActividad;
import dds.grupo4.tpimpacto.dtos.AceptarSolicitud;
import dds.grupo4.tpimpacto.entities.medicion.Actividad;
import dds.grupo4.tpimpacto.entities.medicion.Medicion;
import dds.grupo4.tpimpacto.entities.medicion.Periodicidad;
import dds.grupo4.tpimpacto.entities.medicion.TipoConsumo;
import dds.grupo4.tpimpacto.entities.organizacion.Organizacion;
import dds.grupo4.tpimpacto.entities.organizacion.Solicitud;
import dds.grupo4.tpimpacto.repositories.OrganizacionRepository;
import dds.grupo4.tpimpacto.repositories.SolicitudRepository;
import dds.grupo4.tpimpacto.utils.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrganizacionServiceImpl extends BaseServiceImpl<Organizacion, OrganizacionRepository> implements OrganizacionService {

    private final SolicitudRepository solicitudRepository;
    private final TipoConsumoService tipoConsumoService;

    public OrganizacionServiceImpl(OrganizacionRepository organizacionRepository, SolicitudRepository solicitudRepository,
                                   TipoConsumoService tipoConsumoService) {
        super(organizacionRepository);
        this.solicitudRepository = solicitudRepository;
        this.tipoConsumoService = tipoConsumoService;
    }

    @Override
    @Transactional
    public Optional<Organizacion> getByRazonSocial(String razonSocial) {
        return repository.getByRazonSocial(razonSocial);
    }

    @Override
    @Transactional
    public AceptarSolicitud.Response aceptarSolicitud(AceptarSolicitud.Request request) {
        Solicitud solicitud = solicitudRepository.getById(request.getIdSolicitud());
        if (solicitud == null) {
            throw new IllegalArgumentException("No existe ninguna Solicitud con ID " + request.getIdSolicitud());
        }

        solicitud.getOrganizacion().aceptarSolicitud(solicitud);
        solicitud.getMiembro().setFechaIngreso(LocalDate.now());
        return new AceptarSolicitud.Response();
    }

    @Override
    @Transactional
    public void agregarSolicitud(Organizacion organizacion, Solicitud solicitud) {
        organizacion.addSolicitud(solicitud);
    }

    @Override
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

    @Override
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
