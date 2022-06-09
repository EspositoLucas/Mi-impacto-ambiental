package dds.grupo4.tpimpacto.repositories;

import dds.grupo4.tpimpacto.entities.Miembro;
import dds.grupo4.tpimpacto.entities.Organizacion;
import dds.grupo4.tpimpacto.entities.Trayecto;
import dds.grupo4.tpimpacto.enums.Clasificacion;
import dds.grupo4.tpimpacto.enums.TipoOrganizacion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class TrayectoRepositoryImpl  implements TrayectoRepository{

    private final List<Trayecto> trayectos = new ArrayList<>();

    @Override
    public void save(Trayecto trayecto) {
        trayectos.add(trayecto);
    }

    @Override
    public List<Trayecto> getAll() {
        return trayectos;
    }



}
