package dds.grupo4.tpimpacto.services.apiSwagger;

import dds.grupo4.tpimpacto.entities.Lugar;
import dds.grupo4.tpimpacto.services.apiSwagger.entities.*;

import java.io.IOException;
import java.util.List;

public interface GeoService {
    double distanciaRecorrida(Lugar lugarInicio, Lugar lugarFin);

    List<PaisDto> getPaises(int offset) throws IOException;

    List<ProvinciaDto> getProvincias(int offset, int paisId) throws IOException;

    List<MunicipioDto> getMunicipios(int offset, int provinciaId) throws IOException;

    List<LocalidadDto> getLocalidades(int offset, int municipioId) throws IOException;

    DistanciaDto getDistancia(int localidadOrigenId, String calleOrigen, String alturaOrigen, int localidadDestinoId, String calleDestino, String alturaDestino) throws IOException;
}
