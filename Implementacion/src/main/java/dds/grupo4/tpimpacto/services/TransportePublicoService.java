package dds.grupo4.tpimpacto.services;

import dds.grupo4.tpimpacto.dtos.CrearTransportePublicoRequest;
import dds.grupo4.tpimpacto.dtos.base.BaseResponse;
import dds.grupo4.tpimpacto.entities.medioTransporte.TransportePublico;

public interface TransportePublicoService extends BaseService<TransportePublico> {
    BaseResponse crearTransportePublico(CrearTransportePublicoRequest request);
}
