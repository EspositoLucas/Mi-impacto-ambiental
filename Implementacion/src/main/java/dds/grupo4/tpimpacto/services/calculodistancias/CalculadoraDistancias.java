package dds.grupo4.tpimpacto.services.calculodistancias;

import dds.grupo4.tpimpacto.entities.medioTransporte.Parada;
import dds.grupo4.tpimpacto.entities.trayecto.Direccion;
import dds.grupo4.tpimpacto.entities.trayecto.Lugar;
import dds.grupo4.tpimpacto.services.calculodistancias.apidistancias.GeoService;
import dds.grupo4.tpimpacto.services.calculodistancias.apidistancias.dtos.DistanciaDto;
import dds.grupo4.tpimpacto.services.calculodistancias.apidistancias.dtos.LocalidadDto;
import dds.grupo4.tpimpacto.services.calculodistancias.apidistancias.dtos.MunicipioDto;
import dds.grupo4.tpimpacto.services.calculodistancias.apidistancias.dtos.ProvinciaDto;
import dds.grupo4.tpimpacto.utils.UnitUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CalculadoraDistancias {

    private final GeoService geoService;

    public CalculadoraDistancias(GeoService geoService) {
        this.geoService = geoService;
    }

    public double calcularDistanciaTransportePublico(Parada paradaInicial, Parada paradaFinal) {
        double distanciaRecorrida = 0;
        Parada paradaActual = paradaInicial;
        while (!paradaActual.equals(paradaFinal)) {
            distanciaRecorrida += paradaActual.getDistanciaParadaSiguiente();
            paradaActual = paradaActual.getParadaSiguiente();
        }

        return distanciaRecorrida;
    }

    public double calcularDistanciaConGeoService(Lugar lugarInicio, Lugar lugarFin) {
        try {
            Direccion direccionInicio = lugarInicio.getDireccion();
            Direccion direccionFin = lugarFin.getDireccion();

            int paisInicioId = getPaisId(direccionInicio.getPais());
            int paisFinId = getPaisId(direccionFin.getPais());

            int provinciaInicioId = getProvinciaId(paisInicioId, direccionInicio.getProvincia());
            int provinciaFinId = getProvinciaId(paisFinId, direccionFin.getProvincia());

            int municipioInicioId = getMunicipioId(provinciaInicioId, direccionInicio.getMunicipio());
            int municipioFinId = getMunicipioId(provinciaFinId, direccionFin.getMunicipio());

            int localidadInicioId = getLocalidadId(municipioInicioId, direccionInicio.getLocalidad());
            int localidadFinId = getLocalidadId(municipioFinId, direccionFin.getLocalidad());

            DistanciaDto distanciaDto = geoService.getDistancia(localidadInicioId, direccionInicio.getCalle(), direccionInicio.getAltura(), localidadFinId, direccionFin.getCalle(), direccionFin.getAltura());
            return UnitUtils.convertToUnit(distanciaDto.getValor(), distanciaDto.getUnidad(), "KM");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private int getPaisId(String pais) throws IOException {
        return geoService.getPaises(1).stream()
                .filter(paisDto -> paisDto.getNombre().equals(pais))
                .findFirst().get()
                .getId();
    }

    private int getProvinciaId(int paisId, String provincia) throws IOException {
        Set<ProvinciaDto> provincias = new HashSet<>();

        int offset = 1;
        while (true) {
            List<ProvinciaDto> resultados = geoService.getProvincias(offset, paisId);
            if (resultados.isEmpty()) {
                break;
            }
            provincias.addAll(resultados);
            offset++;
        }

        return provincias.stream()
                .filter(provinciaDto -> provinciaDto.getNombre().equals(provincia))
                .findFirst().get()
                .getId();
    }

    private int getMunicipioId(int provinciaId, String municipio) throws IOException {
        Set<MunicipioDto> municipios = new HashSet<>();

        int offset = 1;
        while (true) {
            List<MunicipioDto> resultados = geoService.getMunicipios(offset, provinciaId);
            if (resultados.isEmpty()) {
                break;
            }
            municipios.addAll(resultados);
            offset++;
        }

        return municipios.stream()
                .filter(municipioDto -> municipioDto.getNombre().equals(municipio))
                .findFirst().get()
                .getId();
    }

    private int getLocalidadId(int municipioId, String localidad) throws IOException {
        Set<LocalidadDto> localidades = new HashSet<>();

        int offset = 1;
        while (true) {
            List<LocalidadDto> resultados = geoService.getLocalidades(offset, municipioId);
            if (resultados.isEmpty()) {
                break;
            }
            localidades.addAll(resultados);
            offset++;
        }

        return localidades.stream()
                .filter(localidadDto -> localidadDto.getNombre().equals(localidad))
                .findFirst().get()
                .getId();
    }
}
