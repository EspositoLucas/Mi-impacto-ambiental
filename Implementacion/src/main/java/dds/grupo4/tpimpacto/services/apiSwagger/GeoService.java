package dds.grupo4.tpimpacto.services.apiSwagger;

import dds.grupo4.tpimpacto.config.GeoApiConfig;
import dds.grupo4.tpimpacto.services.apiSwagger.entities.*;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.stereotype.Service;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.util.List;

@Service
public class GeoService {

    private final String BASE_URL = "https://ddstpa.com.ar/api/";
    private final ApiSwaggerService apiSwaggerService;

    public GeoService(GeoApiConfig apiConfig) {
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

    public List<PaisDto> getPaises(int offset) throws IOException {
        return this.apiSwaggerService.paises(offset).execute().body();
    }

    public List<ProvinciaDto> getProvincias(int offset, int paisId) throws IOException {
        return this.apiSwaggerService.provincias(offset, paisId).execute().body();
    }

    public List<MunicipioDto> getMunicipios(int offset, int provinciaId) throws IOException {
        return this.apiSwaggerService.municipios(offset, provinciaId).execute().body();
    }

    public List<LocalidadDto> getLocalidades(int offset, int municipioId) throws IOException {
        return this.apiSwaggerService.localidades(offset, municipioId).execute().body();
    }

    public DistanciaDto getDistancia(int localidadOrigenId, String calleOrigen, String alturaOrigen, int localidadDestinoId, String calleDestino, String alturaDestino) throws IOException {
        return this.apiSwaggerService.distancia(localidadOrigenId, calleOrigen, alturaOrigen, localidadDestinoId, calleDestino, alturaDestino).execute().body();
    }

}
