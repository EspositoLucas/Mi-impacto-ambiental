package dds.grupo4.tpimpacto.repositories;

import dds.grupo4.tpimpacto.entities.Sector;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class SectorRepositoryImpl implements SectorRepository {

    private final List<Sector> sectores = new ArrayList<>();

    @Override
    public void save(Sector sector) {
        sectores.add(sector);
    }

    @Override
    public List<Sector> getAll() {
        return sectores;
    }

    @Override
    public Optional<Sector> getByNombreYOrganizacion(String nombreSector, String razonSocialOrganizacion) {
        return sectores.stream()
                .filter(sector -> sector.getNombre().equals(nombreSector)
                               && sector.getOrganizacion().getRazonSocial().equals(razonSocialOrganizacion))
                .findFirst();
    }
}
