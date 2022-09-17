package dds.grupo4.tpimpacto.services.calculodistancias;

import dds.grupo4.tpimpacto.entities.medioTransporte.Parada;
import dds.grupo4.tpimpacto.entities.trayecto.Direccion;
import dds.grupo4.tpimpacto.entities.trayecto.Lugar;
import dds.grupo4.tpimpacto.services.UnidadService;
import dds.grupo4.tpimpacto.services.calculodistancias.apidistancias.GeoService;
import dds.grupo4.tpimpacto.services.calculodistancias.apidistancias.dtos.DistanciaDto;
import dds.grupo4.tpimpacto.units.Cantidad;
import dds.grupo4.tpimpacto.units.Unidad;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CalculadoraDistancias {

    private final GeoService geoService;
    private final UnidadService unidadService;

    public CalculadoraDistancias(GeoService geoService, UnidadService unidadService) {
        this.geoService = geoService;
        this.unidadService = unidadService;
    }

    public Cantidad calcularDistanciaTransportePublico(Parada paradaInicial, Parada paradaFinal) {
        Cantidad distanciaRecorrida = new Cantidad(unidadService.getBySimbolo("km").get(), 0);
        Parada paradaActual = paradaInicial;
        while (!paradaActual.equals(paradaFinal)) {
            distanciaRecorrida = distanciaRecorrida.add(paradaActual.getDistanciaParadaSiguiente());
            paradaActual = paradaActual.getParadaSiguiente();
        }
        return distanciaRecorrida;
    }

    public Cantidad calcularDistanciaConGeoService(Lugar lugarInicio, Lugar lugarFin) {
        try {
            Direccion direccionInicio = lugarInicio.getDireccion();
            Direccion direccionFin = lugarFin.getDireccion();

            int localidadInicioId = direccionInicio.getLocalidad().getIdSegunApi();
            int localidadFinId = direccionFin.getLocalidad().getIdSegunApi();

            DistanciaDto distanciaDto = geoService.getDistancia(localidadInicioId, direccionInicio.getCalle(), direccionInicio.getAltura(), localidadFinId, direccionFin.getCalle(), direccionFin.getAltura());
            Unidad unidadDistancia = unidadService.getBySimbolo(distanciaDto.getUnidad().toLowerCase()).get();
            Unidad KM = unidadService.getBySimbolo("km").get();
            return new Cantidad(unidadDistancia, distanciaDto.getValor()).toUnidad(KM);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
