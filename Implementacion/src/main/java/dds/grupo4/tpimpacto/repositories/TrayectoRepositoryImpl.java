package dds.grupo4.tpimpacto.repositories;

import dds.grupo4.tpimpacto.entities.Trayecto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TrayectoRepositoryImpl implements TrayectoRepository {

    private final List<Trayecto> trayectos = new ArrayList<>();

    @Override
    public void save(Trayecto trayecto) {
        trayectos.add(trayecto);
    }

    @Override
    public void update(Trayecto trayecto) {
        int index = trayectos.indexOf(trayecto);
        trayectos.set(index, trayecto);
    }

    @Override
    public List<Trayecto> getAll() {
        return trayectos;
    }

}
