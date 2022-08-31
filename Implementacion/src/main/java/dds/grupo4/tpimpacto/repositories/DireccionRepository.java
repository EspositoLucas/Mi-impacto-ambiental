package dds.grupo4.tpimpacto.repositories;

import dds.grupo4.tpimpacto.entities.trayecto.Direccion;

import java.util.Optional;

public interface DireccionRepository extends BaseRepository<Direccion> {
    Optional<Direccion> getByDireccionEntera(
            String calle,
            String altura,
            String pais,
            String provincia,
            String municipio,
            String localidad,
            String barrio,
            int codigoPostal
    );
}
