package dds.grupo4.tpimpacto.services;

import dds.grupo4.tpimpacto.cargamediciones.RowMedicionActividad;
import dds.grupo4.tpimpacto.entities.Organizacion;
import dds.grupo4.tpimpacto.repositories.OrganizacionRepository;

import java.util.List;

public class OrganizacionServiceImpl implements OrganizacionService {

    private final OrganizacionRepository organizacionRepository;

    public OrganizacionServiceImpl(OrganizacionRepository organizacionRepository) {
        this.organizacionRepository = organizacionRepository;
    }

    @Override
    public void save(Organizacion organizacion) {
        organizacionRepository.save(organizacion);
    }

    @Override
    public List<Organizacion> getAll() {
        return organizacionRepository.getAll();
    }

    @Override
    public void cargarMediciones(List<RowMedicionActividad> mediciones) {
        for (RowMedicionActividad rowMedicionActividad : mediciones) {
            System.out.println(rowMedicionActividad.toString());
        }
    }

}
