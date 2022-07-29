package dds.grupo4.tpimpacto.services.calculodistancias.apidistancias;

import dds.grupo4.tpimpacto.services.calculodistancias.apidistancias.dtos.*;

import java.io.IOException;
import java.util.List;

public interface GeoService {
    List<PaisDto> getPaises(int offset) throws IOException;

    List<ProvinciaDto> getProvincias(int offset, int paisId) throws IOException;

    List<MunicipioDto> getMunicipios(int offset, int provinciaId) throws IOException;

    List<LocalidadDto> getLocalidades(int offset, int municipioId) throws IOException;

    DistanciaDto getDistancia(int localidadOrigenId, String calleOrigen, String alturaOrigen, int localidadDestinoId, String calleDestino, String alturaDestino) throws IOException;
}
