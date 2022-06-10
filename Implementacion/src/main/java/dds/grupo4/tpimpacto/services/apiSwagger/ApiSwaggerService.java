package dds.grupo4.tpimpacto.services.apiSwagger;

import dds.grupo4.tpimpacto.services.apiSwagger.entities.*;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

interface ApiSwaggerService {

    @GET("paises")
    Call<List<PaisDto>> paises(@Query("offset") int offset);

    @GET("provincias")
    Call<List<ProvinciaDto>> provincias(@Query("offset") int offset, @Query("paisId") int paisId);

    @GET("municipios")
    Call<List<MunicipioDto>> municipios(@Query("offset") int offset, @Query("provinciaId") int provinciaId);

    @GET("localidades")
    Call<List<LocalidadDto>> localidades(@Query("offset") int offset, @Query("municipioId") int municipioId);

    @GET("distancia")
    Call<DistanciaDto> distancia(@Query("localidadOrigenId") int localidadOrigenId, @Query("calleOrigen") String calleOrigen, @Query("alturaOrigen") String alturaOrigen, @Query("localidadDestinoId") int localidadDestinoId, @Query("calleDestino") String calleDestino, @Query("alturaDestino") String alturaDestino);

}
