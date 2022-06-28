package dds.grupo4.tpimpacto.services;

import dds.grupo4.tpimpacto.cargamediciones.RowMedicionActividad;
import dds.grupo4.tpimpacto.entities.Medicion;
import dds.grupo4.tpimpacto.entities.Organizacion;
import dds.grupo4.tpimpacto.entities.Sector;
import dds.grupo4.tpimpacto.entities.Solicitud;
import dds.grupo4.tpimpacto.repositories.OrganizacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrganizacionServiceImpl implements OrganizacionService {

    private final OrganizacionRepository organizacionRepository;
    private final SectorService sectorService;

    public OrganizacionServiceImpl(OrganizacionRepository organizacionRepository, SectorService sectorService) {
        this.organizacionRepository = organizacionRepository;
        this.sectorService = sectorService;
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
                .map(RowMedicionActividad::toMedicion)
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

}
