package dds.grupo4.tpimpacto.services.calculodistancias.apidistancias;

import dds.grupo4.tpimpacto.config.GeoApiConfig;
import dds.grupo4.tpimpacto.entities.trayecto.Direccion;
import dds.grupo4.tpimpacto.entities.trayecto.Lugar;
import dds.grupo4.tpimpacto.services.calculodistancias.CalculadoraDistancias;
import dds.grupo4.tpimpacto.services.calculodistancias.apidistancias.dtos.*;
import dds.grupo4.tpimpacto.utils.UnitUtils;
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
    private final ApiDistanciasService apiDistanciasService;

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

        this.apiDistanciasService = retrofit.create(ApiDistanciasService.class);
    }

    @Override
    public List<PaisDto> getPaises(int offset) throws IOException {
        return this.apiDistanciasService.paises(offset).execute().body();
    }

    @Override
    public List<ProvinciaDto> getProvincias(int offset, int paisId) throws IOException {
        return this.apiDistanciasService.provincias(offset, paisId).execute().body();
    }

    @Override
    public List<MunicipioDto> getMunicipios(int offset, int provinciaId) throws IOException {
        return this.apiDistanciasService.municipios(offset, provinciaId).execute().body();
    }

    @Override
    public List<LocalidadDto> getLocalidades(int offset, int municipioId) throws IOException {
        return this.apiDistanciasService.localidades(offset, municipioId).execute().body();
    }

    @Override
    public DistanciaDto getDistancia(int localidadOrigenId, String calleOrigen, String alturaOrigen, int localidadDestinoId, String calleDestino, String alturaDestino) throws IOException {
        return this.apiDistanciasService.distancia(localidadOrigenId, calleOrigen, alturaOrigen, localidadDestinoId, calleDestino, alturaDestino).execute().body();
    }

}
