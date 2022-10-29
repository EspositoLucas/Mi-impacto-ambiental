package dds.grupo4.tpimpacto.services;

import dds.grupo4.tpimpacto.dtos.MedioDeTransporteDto;
import dds.grupo4.tpimpacto.dtos.base.ResponseWithResults;
import dds.grupo4.tpimpacto.entities.medioTransporte.MedioDeTransporte;
import dds.grupo4.tpimpacto.repositories.MedioDeTransporteRepository;
import dds.grupo4.tpimpacto.services.base.BaseService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedioDeTransporteService extends BaseService<MedioDeTransporte, MedioDeTransporteRepository> {
    public MedioDeTransporteService(MedioDeTransporteRepository repository) {
        super(repository);
    }

    @Transactional
    public ResponseWithResults<MedioDeTransporteDto> listarMediosDeTransporte() {
        List<MedioDeTransporteDto> dtos = this.getAll().stream()
                .map(MedioDeTransporteDto::from)
                .collect(Collectors.toList());
        return new ResponseWithResults<>(HttpStatus.OK, dtos);
    }
}
