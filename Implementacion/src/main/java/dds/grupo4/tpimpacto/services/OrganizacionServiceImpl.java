package dds.grupo4.tpimpacto.services;

import dds.grupo4.tpimpacto.cargamediciones.RowMedicionActividad;
import dds.grupo4.tpimpacto.entities.medicion.Actividad;
import dds.grupo4.tpimpacto.entities.medicion.Medicion;
import dds.grupo4.tpimpacto.entities.medicion.Periodicidad;
import dds.grupo4.tpimpacto.entities.medicion.TipoConsumo;
import dds.grupo4.tpimpacto.entities.organizacion.Organizacion;
import dds.grupo4.tpimpacto.entities.organizacion.Sector;
import dds.grupo4.tpimpacto.entities.organizacion.Solicitud;
import dds.grupo4.tpimpacto.repositories.OrganizacionRepository;
import dds.grupo4.tpimpacto.utils.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrganizacionServiceImpl implements OrganizacionService {

    private final OrganizacionRepository organizacionRepository;
    private final SectorService sectorService;
    private final TipoConsumoService tipoConsumoService;

    public OrganizacionServiceImpl(OrganizacionRepository organizacionRepository, SectorService sectorService,
                                   TipoConsumoService tipoConsumoService) {
        this.organizacionRepository = organizacionRepository;
        this.sectorService = sectorService;
        this.tipoConsumoService = tipoConsumoService;
    }

    @Override
    public void save(Organizacion organizacion) {
        if (organizacionRepository.getAll().contains(organizacion)) {
            organizacionRepository.merge(organizacion);
        } else {
            organizacionRepository.save(organizacion);
        }

        for (Sector sector : organizacion.getSectores()) {
            sectorService.save(sector);
        }
    }

    @Override
    public List<Organizacion> getAll() {
        return organizacionRepository.getAll();
    }

    @Override
    public Optional<Organizacion> getByRazonSocial(String razonSocial) {
        return organizacionRepository.getByRazonSocial(razonSocial);
    }

    @Override
    public void agregarSolicitud(Organizacion organizacion, Solicitud solicitud) {
        organizacion.addSolicitud(solicitud);
    }

    @Override
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
    public List<String> getMailsDeContactos() {
        return organizacionRepository.getMailsDeContactos();
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
