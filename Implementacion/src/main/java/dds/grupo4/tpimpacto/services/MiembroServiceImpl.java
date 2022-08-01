package dds.grupo4.tpimpacto.services;

import dds.grupo4.tpimpacto.entities.organizacion.Miembro;
import dds.grupo4.tpimpacto.repositories.MiembroRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MiembroServiceImpl implements MiembroService {

    private final MiembroRepository miembroRepository;

    public MiembroServiceImpl(MiembroRepository miembroRepository) {
        this.miembroRepository = miembroRepository;
    }

    @Override
    @Transactional
    public void save(Miembro miembro) {
        if (miembroRepository.getAll().contains(miembro)) {
            miembroRepository.merge(miembro);
        } else {
            miembroRepository.save(miembro);
        }
    }

    @Override
    @Transactional
    public List<Miembro> getAll() {
        return miembroRepository.getAll();
    }
}
