package dds.grupo4.tpimpacto.services.calculodistancias.apidistancias;

import dds.grupo4.tpimpacto.config.GeoApiConfig;
import dds.grupo4.tpimpacto.entities.geo.Localidad;
import dds.grupo4.tpimpacto.entities.geo.Municipio;
import dds.grupo4.tpimpacto.entities.geo.Pais;
import dds.grupo4.tpimpacto.entities.geo.Provincia;
import dds.grupo4.tpimpacto.repositories.PaisRepository;
import dds.grupo4.tpimpacto.services.calculodistancias.apidistancias.dtos.*;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class GeoService {

    private final String BASE_URL = "https://ddstpa.com.ar/api/";
    private final ApiDistanciasService apiDistanciasService;
    private final PaisRepository paisRepository;

    public GeoService(GeoApiConfig apiConfig, PaisRepository paisRepository) {
        this.paisRepository = paisRepository;

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

    public List<PaisDto> getPaises(int offset) throws IOException {
        return this.apiDistanciasService.paises(offset).execute().body();
    }

    public List<ProvinciaDto> getProvincias(int offset, int paisId) throws IOException {
        return this.apiDistanciasService.provincias(offset, paisId).execute().body();
    }

    public List<MunicipioDto> getMunicipios(int offset, int provinciaId) throws IOException {
        return this.apiDistanciasService.municipios(offset, provinciaId).execute().body();
    }

    public List<LocalidadDto> getLocalidades(int offset, int municipioId) throws IOException {
        return this.apiDistanciasService.localidades(offset, municipioId).execute().body();
    }

    public DistanciaDto getDistancia(int localidadOrigenId, String calleOrigen, String alturaOrigen, int localidadDestinoId, String calleDestino, String alturaDestino) throws IOException {
        return this.apiDistanciasService.distancia(localidadOrigenId, calleOrigen, alturaOrigen, localidadDestinoId, calleDestino, alturaDestino).execute().body();
    }

    @Transactional
    @Async
    public void seedData() {
        if (paisRepository.hasData()) {
            log.debug("Seed: ya hay Paises creados");
            return;
        }

        List<PaisDto> paisesDtos = getAllPaisesDesdeApi();
        for (PaisDto paisDto : paisesDtos) {
            Pais pais = paisDto.toPais();

            List<ProvinciaDto> provinciasDtos = getAllProvinciasDesdeApi(pais.getIdSegunApi());
            for (ProvinciaDto provinciaDto : provinciasDtos) {
                Provincia provincia = provinciaDto.toProvincia();

                List<MunicipioDto> municipiosDtos = getAllMunicipiosDesdeApi(provincia.getIdSegunApi());
                for (MunicipioDto municipioDto : municipiosDtos) {
                    Municipio municipio = municipioDto.toMunicipio();

                    List<LocalidadDto> localidadesDtos = getAllLocalidadesDesdeApi(municipio.getIdSegunApi());
                    for (LocalidadDto localidadDto : localidadesDtos) {
                        Localidad localidad = localidadDto.toLocalidad();
                        municipio.addLocalidad(localidad);
                        log.debug("Localidad " + localidad.getNombre() + " agregada");
                    }
                    provincia.addMunicipio(municipio);
                    log.debug("Municipio " + municipio.getNombre() + " agregado");
                }
                pais.addProvincia(provincia);
                log.debug("Provincia " + provincia.getNombre() + " agregada");
            }
            log.debug("Pais " + pais.getNombre() + " agregado");
            paisRepository.save(pais);
        }
    }

    private List<PaisDto> getAllPaisesDesdeApi() {
        try {
            List<PaisDto> paises = new ArrayList<>();
            int offset = 1;
            while (true) {
                List<PaisDto> paisesEnOffset = getPaises(offset);
                if (paisesEnOffset.isEmpty())
                    return paises;
                paises.addAll(paisesEnOffset);
                offset++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<ProvinciaDto> getAllProvinciasDesdeApi(int paisId) {
        try {
            List<ProvinciaDto> provincias = new ArrayList<>();
            int offset = 1;
            while (true) {
                List<ProvinciaDto> provinciasEnOffset = getProvincias(offset, paisId);
                if (provinciasEnOffset.isEmpty())
                    return provincias;
                provincias.addAll(provinciasEnOffset);
                offset++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<MunicipioDto> getAllMunicipiosDesdeApi(int provinciaId) {
        try {
            List<MunicipioDto> municipios = new ArrayList<>();
            int offset = 1;
            while (true) {
                List<MunicipioDto> municipiosEnOffset = getMunicipios(offset, provinciaId);
                if (municipiosEnOffset.isEmpty())
                    return municipios;
                municipios.addAll(municipiosEnOffset);
                offset++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<LocalidadDto> getAllLocalidadesDesdeApi(int municipioId) {
        try {
            List<LocalidadDto> localidades = new ArrayList<>();
            int offset = 1;
            while (true) {
                List<LocalidadDto> localidadesEnOffset = getLocalidades(offset, municipioId);
                if (localidadesEnOffset.isEmpty())
                    return localidades;
                localidades.addAll(localidadesEnOffset);
                offset++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
