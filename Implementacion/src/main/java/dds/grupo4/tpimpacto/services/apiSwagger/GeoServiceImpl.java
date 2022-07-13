package dds.grupo4.tpimpacto.services.apiSwagger;

import dds.grupo4.tpimpacto.config.GeoApiConfig;
import dds.grupo4.tpimpacto.entities.trayecto.Direccion;
import dds.grupo4.tpimpacto.entities.trayecto.Lugar;
import dds.grupo4.tpimpacto.helpers.ConverterHelper;
import dds.grupo4.tpimpacto.services.apiSwagger.entities.*;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.stereotype.Service;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class GeoServiceImpl implements GeoService {

    private final String BASE_URL = "https://ddstpa.com.ar/api/";
    private final ApiSwaggerService apiSwaggerService;

    public GeoServiceImpl(GeoApiConfig apiConfig) {
        // Agrega el Token necesario para hacer las requests
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(chain -> {
            Request requestWithAuthentication = chain.request()
                    .newBuilder()
                    .addHeader("Authorization", "Bearer " + apiConfig.getToken())
                    .build();
            return chain.proceed(requestWithAuthentication);
        }).build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        this.apiSwaggerService = retrofit.create(ApiSwaggerService.class);
    }

    @Override
    public double distanciaRecorrida(Lugar lugarInicio, Lugar lugarFin) {
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

            DistanciaDto distanciaDto = getDistancia(localidadInicioId, direccionInicio.getCalle(), direccionInicio.getAltura(), localidadFinId, direccionFin.getCalle(), direccionFin.getAltura());
            return ConverterHelper.convertToUnit(distanciaDto.getValor(), distanciaDto.getUnidad(), "KM");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<PaisDto> getPaises(int offset) throws IOException {
        return this.apiSwaggerService.paises(offset).execute().body();
    }

    @Override
    public List<ProvinciaDto> getProvincias(int offset, int paisId) throws IOException {
        return this.apiSwaggerService.provincias(offset, paisId).execute().body();
    }

    @Override
    public List<MunicipioDto> getMunicipios(int offset, int provinciaId) throws IOException {
        return this.apiSwaggerService.municipios(offset, provinciaId).execute().body();
    }

    @Override
    public List<LocalidadDto> getLocalidades(int offset, int municipioId) throws IOException {
        return this.apiSwaggerService.localidades(offset, municipioId).execute().body();
    }

    @Override
    public DistanciaDto getDistancia(int localidadOrigenId, String calleOrigen, String alturaOrigen, int localidadDestinoId, String calleDestino, String alturaDestino) throws IOException {
        return this.apiSwaggerService.distancia(localidadOrigenId, calleOrigen, alturaOrigen, localidadDestinoId, calleDestino, alturaDestino).execute().body();
    }

    private int getPaisId(String pais) throws IOException {
        return getPaises(1).stream()
                .filter(paisDto -> paisDto.getNombre().equals(pais))
                .findFirst().get()
                .getId();
    }

    private int getProvinciaId(int paisId, String provincia) throws IOException {
        Set<ProvinciaDto> provincias = new HashSet<>();

        int offset = 1;
        while (true) {
            List<ProvinciaDto> resultados = getProvincias(offset, paisId);
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
            List<MunicipioDto> resultados = getMunicipios(offset, provinciaId);
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
            List<LocalidadDto> resultados = getLocalidades(offset, municipioId);
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
