package dds.grupo4.tpimpacto.services;

import dds.grupo4.tpimpacto.dtos.LugarDto;
import dds.grupo4.tpimpacto.dtos.base.ResponseWithResults;
import dds.grupo4.tpimpacto.entities.trayecto.Lugar;
import dds.grupo4.tpimpacto.repositories.LugarRepository;
import dds.grupo4.tpimpacto.services.base.BaseService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LugarService extends BaseService<Lugar, LugarRepository> {
    public LugarService(LugarRepository repository) {
        super(repository);
    }

    @Transactional
    public ResponseWithResults<LugarDto> listarLugares() {
        List<LugarDto> dtos = this.getAll().stream()
                .map(LugarDto::from)
                .collect(Collectors.toList());
        return new ResponseWithResults<>(HttpStatus.OK, dtos);
    }
}
