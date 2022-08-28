package dds.grupo4.tpimpacto.repositories;

import dds.grupo4.tpimpacto.entities.medioTransporte.MedioDeTransporte;

import java.util.Optional;

public interface MedioDeTransporteRepository extends BaseRepository<MedioDeTransporte>{


        Optional<MedioDeTransporte> getByNombre(String nombreTipo);
}
