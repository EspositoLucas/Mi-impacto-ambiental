package dds.grupo4.tpimpacto.repositories;

import dds.grupo4.tpimpacto.entities.medioTransporte.TransportePublico;

import java.util.Optional;

public interface TransportePublicoRepository extends BaseRepository<TransportePublico> {
    Optional<TransportePublico> getByLinea(String linea);
}
