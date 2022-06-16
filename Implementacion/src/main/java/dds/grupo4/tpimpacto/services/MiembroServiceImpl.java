package dds.grupo4.tpimpacto.services;

import dds.grupo4.tpimpacto.entities.Miembro;
import dds.grupo4.tpimpacto.repositories.MiembroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MiembroServiceImpl implements MiembroService {

    private final MiembroRepository miembroRepository;

    public MiembroServiceImpl(MiembroRepository miembroRepository) {
        this.miembroRepository = miembroRepository;
    }

    @Override
    public void save(Miembro miembro) {
        if (miembroRepository.getAll().contains(miembro)) {
            miembroRepository.update(miembro);
        } else {
            miembroRepository.save(miembro);
        }
    }

    @Override
    public List<Miembro> getAll() {
        return miembroRepository.getAll();
    }
}
