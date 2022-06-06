package dds.grupo4.tpimpacto.services;

import dds.grupo4.tpimpacto.cargamediciones.RowMedicionActividad;
import dds.grupo4.tpimpacto.entities.Organizacion;
import dds.grupo4.tpimpacto.entities.Sector;
import dds.grupo4.tpimpacto.entities.Solicitud;
import dds.grupo4.tpimpacto.repositories.OrganizacionRepository;

import java.util.List;
import java.util.Optional;

public class OrganizacionServiceImpl implements OrganizacionService {

    private final OrganizacionRepository organizacionRepository;
    private final SectorService sectorService;

    public OrganizacionServiceImpl(OrganizacionRepository organizacionRepository, SectorService sectorService) {
        this.organizacionRepository = organizacionRepository;
        this.sectorService = sectorService;
    }

    @Override
    public void save(Organizacion organizacion) {
        organizacionRepository.save(organizacion);
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
    public void cargarMediciones(List<RowMedicionActividad> mediciones) {
        for (RowMedicionActividad rowMedicionActividad : mediciones) {
            System.out.println(rowMedicionActividad.toString());
        }
    }

}
