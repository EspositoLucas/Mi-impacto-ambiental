package dds.grupo4.tpimpacto.repositories;

import dds.grupo4.tpimpacto.entities.Trayecto;

import java.util.ArrayList;
import java.util.List;

public class TrayectoRepositoryImpl implements TrayectoRepository {

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
