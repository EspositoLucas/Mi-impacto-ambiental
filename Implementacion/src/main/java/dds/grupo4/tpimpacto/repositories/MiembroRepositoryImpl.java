package dds.grupo4.tpimpacto.repositories;

import dds.grupo4.tpimpacto.entities.Miembro;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MiembroRepositoryImpl implements MiembroRepository {

    private final List<Miembro> miembros = new ArrayList<>();

    @Override
    public void save(Miembro obj) {
        miembros.add(obj);
    }

    @Override
    public List<Miembro> getAll() {
        return miembros;
    }
}
