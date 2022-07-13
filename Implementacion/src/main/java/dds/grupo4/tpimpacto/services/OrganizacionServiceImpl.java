package dds.grupo4.tpimpacto.services;

import dds.grupo4.tpimpacto.cargamediciones.RowMedicionActividad;
import dds.grupo4.tpimpacto.entities.medicion.Medicion;
import dds.grupo4.tpimpacto.entities.organizacion.Organizacion;
import dds.grupo4.tpimpacto.entities.organizacion.Sector;
import dds.grupo4.tpimpacto.entities.organizacion.Solicitud;
import dds.grupo4.tpimpacto.entities.medicion.TipoConsumo;
import dds.grupo4.tpimpacto.entities.medicion.Actividad;
import dds.grupo4.tpimpacto.repositories.OrganizacionRepository;
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
            organizacionRepository.update(organizacion);
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
                .map(this::rowToMedicion)
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

    private Medicion rowToMedicion(RowMedicionActividad row) {
        Actividad actividad = Actividad.from(row.getActividad());
        TipoConsumo tipoConsumo = tipoConsumoService.getByNombre(row.getTipoDeConsumo()).get();

        Medicion medicion = new Medicion(actividad, tipoConsumo, row.getValor(), row.getPeriodicidad(),
                row.getPeriodoImputacion());
        return medicion;
    }

}
