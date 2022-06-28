package dds.grupo4.tpimpacto.repositories;

import dds.grupo4.tpimpacto.entities.Contacto;
import dds.grupo4.tpimpacto.entities.Organizacion;
import dds.grupo4.tpimpacto.enums.Clasificacion;
import dds.grupo4.tpimpacto.enums.TipoOrganizacion;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class OrganizacionRepositoryImpl implements OrganizacionRepository {

    private final List<Organizacion> organizaciones = new ArrayList<>();

    public OrganizacionRepositoryImpl() {
        Organizacion mcDonalds = new Organizacion("McDonalds", TipoOrganizacion.EMPRESA, Clasificacion.EMPRESA_SECTOR_PRIMARIO);
        mcDonalds.setContactos(new ArrayList<>(Arrays.asList(
                new Contacto("McEchi", "McSaidman", "esaidman@frba.utn.edu.ar", "1122334455")
        )));

        Organizacion carrefour = new Organizacion("Carrefour", TipoOrganizacion.EMPRESA, Clasificacion.EMPRESA_SECTOR_PRIMARIO);
        carrefour.setContactos(new ArrayList<>(Arrays.asList(
                new Contacto("CarrEchi", "CarrSaidman", "grupo4dds2022@hotmail.com", "1122334455")
        )));

        organizaciones.add(mcDonalds);
        organizaciones.add(carrefour);
    }

    @Override
    public void save(Organizacion organizacion) {
        organizaciones.add(organizacion);
    }

    @Override
    public void update(Organizacion organizacion) {
        int index = organizaciones.indexOf(organizacion);
        organizaciones.set(index, organizacion);
    }

    @Override
    public List<Organizacion> getAll() {
        return organizaciones;
    }

    @Override
    public Optional<Organizacion> getByRazonSocial(String razonSocial) {
        return organizaciones.stream()
                .filter(organizacion -> organizacion.getRazonSocial().equals(razonSocial))
                .findFirst();
    }

    @Override
    public List<String> getMailsDeContactos() {
        return organizaciones.stream()
                .flatMap(organizacion -> organizacion.getContactos().stream())
                .map(contacto -> contacto.getEmail())
                .distinct()
                .collect(Collectors.toList());
    }
}
