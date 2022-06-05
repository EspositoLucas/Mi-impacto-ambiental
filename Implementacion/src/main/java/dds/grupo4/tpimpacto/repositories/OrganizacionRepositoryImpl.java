package dds.grupo4.tpimpacto.repositories;

import dds.grupo4.tpimpacto.entities.Organizacion;
import dds.grupo4.tpimpacto.enums.Clasificacion;
import dds.grupo4.tpimpacto.enums.TipoOrganizacion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrganizacionRepositoryImpl implements OrganizacionRepository {

    private final List<Organizacion> organizaciones = new ArrayList<>(Arrays.asList(
            new Organizacion("McDonalds", TipoOrganizacion.EMPRESA, Clasificacion.EMPRESA_SECTOR_PRIMARIO),
            new Organizacion("DIA", TipoOrganizacion.EMPRESA, Clasificacion.EMPRESA_SECTOR_PRIMARIO)
    ));

    @Override
    public void save(Organizacion organizacion) {
        organizaciones.add(organizacion);
    }

    @Override
    public List<Organizacion> getAll() {
        return organizaciones;
    }

}
