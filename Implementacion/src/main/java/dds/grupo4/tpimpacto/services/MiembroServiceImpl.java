package dds.grupo4.tpimpacto.services;

import dds.grupo4.tpimpacto.entities.Miembro;
import dds.grupo4.tpimpacto.repositories.MiembroRepository;

import java.util.List;

public class MiembroServiceImpl implements MiembroService {

    private final MiembroRepository miembroRepository;

    public MiembroServiceImpl(MiembroRepository miembroRepository) {
        this.miembroRepository = miembroRepository;
    }

    @Override
    public void save(Miembro miembro) {
        miembroRepository.save(miembro);
    }

    @Override
    public List<Miembro> getAll() {
        return miembroRepository.getAll();
    }
}
