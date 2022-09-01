package dds.grupo4.tpimpacto.services;

import dds.grupo4.tpimpacto.dtos.EspacioDto;
import dds.grupo4.tpimpacto.dtos.base.ResponseWithResults;
import dds.grupo4.tpimpacto.entities.trayecto.Espacio;
import dds.grupo4.tpimpacto.repositories.EspacioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EspacioService extends BaseService<Espacio, EspacioRepository> {

    public EspacioService(EspacioRepository repository) {
        super(repository);
    }

    @Transactional
    public ResponseWithResults<EspacioDto> listarEspacios() {
        List<EspacioDto> dtos = this.getAll().stream()
                .map(EspacioDto::from)
                .collect(Collectors.toList());
        return new ResponseWithResults<>(HttpStatus.OK, dtos);
    }
}
