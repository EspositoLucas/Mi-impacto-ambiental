package dds.grupo4.tpimpacto.services;

import dds.grupo4.tpimpacto.dtos.CrearTransportePublicoRequest;
import dds.grupo4.tpimpacto.dtos.ParadaDto;
import dds.grupo4.tpimpacto.dtos.base.BaseResponse;
import dds.grupo4.tpimpacto.entities.medioTransporte.Combustible;
import dds.grupo4.tpimpacto.entities.medioTransporte.Parada;
import dds.grupo4.tpimpacto.entities.medioTransporte.TipoTransportePublico;
import dds.grupo4.tpimpacto.entities.medioTransporte.TransportePublico;
import dds.grupo4.tpimpacto.entities.trayecto.Direccion;
import dds.grupo4.tpimpacto.repositories.CombustibleRepository;
import dds.grupo4.tpimpacto.repositories.ParadaRepository;
import dds.grupo4.tpimpacto.repositories.TransportePublicoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransportePublicoService extends BaseService<TransportePublico, TransportePublicoRepository> {

    private final DireccionService direccionService;
    private final CombustibleRepository combustibleRepository;
    private final ParadaRepository paradaRepository;

    public TransportePublicoService(TransportePublicoRepository repository, DireccionService direccionService, CombustibleRepository combustibleRepository, ParadaRepository paradaRepository) {
        super(repository);
        this.direccionService = direccionService;
        this.combustibleRepository = combustibleRepository;
        this.paradaRepository = paradaRepository;
    }

    @Transactional
    public BaseResponse crearTransportePublico(CrearTransportePublicoRequest request) {
        if (repository.getByLinea(request.getLinea()).isPresent()) {
            return new BaseResponse(HttpStatus.BAD_REQUEST, "Ya existe un TransportePublico con la linea especificada");
        }

        TipoTransportePublico tipoTransportePublico = TipoTransportePublico.valueOf(request.getTipoTransportePublico());
        Combustible combustible = combustibleRepository.getById(request.getIdCombustible());

        List<Parada> paradas = new ArrayList<>();
        for (ParadaDto paradaDto : request.getParadas()) {
            Direccion direccion = direccionService.getOrCreateDireccion(paradaDto.getDireccion());
            Parada parada = new Parada(direccion, paradaDto.getDistanciaParadaSiguiente());
            paradaRepository.save(parada);
            paradas.add(parada);
        }

        TransportePublico transportePublico = new TransportePublico(
                tipoTransportePublico,
                request.getLinea(),
                combustible,
                request.getCombustibleConsumidoPorKm()
        );

        // Le seteo la Parada siguiente a cada Parada y la agrego al transportePublico
        for (int i = 0; i < paradas.size(); i++) {
            Parada parada = paradas.get(i);
            if (i < paradas.size() - 1) {
                parada.setParadaSiguiente(paradas.get(i + 1));
            }
            transportePublico.addParada(parada);
        }

        this.save(transportePublico);
        return new BaseResponse(HttpStatus.CREATED);
    }
}
